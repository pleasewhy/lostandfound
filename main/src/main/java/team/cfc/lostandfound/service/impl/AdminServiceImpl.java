package team.cfc.lostandfound.service.impl;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.cfc.lostandfound.bo.AdminDetails;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.dao.*;
import team.cfc.lostandfound.dto.ApplyRegionDto;
import team.cfc.lostandfound.model.*;
import team.cfc.lostandfound.security.util.JwtTokenUtil;
import team.cfc.lostandfound.service.AdminService;
import team.cfc.lostandfound.service.RegionService;
import team.cfc.lostandfound.service.WxUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    AdminDao adminDao;

    @Autowired
    RegionService regionService;

    @Autowired
    ApplyRegionMsgDao applyRegionDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    WxUserService wxUserService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;



    @Value("${jwt.tokenHead}")
    String tokenHead;

    public Admin getAdminByUsername(String username) {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Admin> admins = adminDao.selectByExample(example);
        if (admins != null && admins.size() > 0) {
            return admins.get(0);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin = getAdminByUsername(username);
        if (admin != null) {
            return new AdminDetails(admin);
        }
        return null;
    }

    @Override
    public CommonResult login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = this.loadUserByUsername(username);
            if (userDetails==null||!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("用户名或者密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
            return CommonResult.failed("密码错误");
        }
        token = tokenHead + " " + token;
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return CommonResult.success(map);
    }

    @Override
    public CommonResult acceptApplyRegion(String username, int regionId) {
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        return null;
    }


    ApplyRegionDto getApplyRegionDto(ApplyRegionMsg applyRegionMsg){
        ApplyRegionDto applyRegionDto = new ApplyRegionDto();
        applyRegionDto.setApplyTime(applyRegionMsg.getApplyTime());
        applyRegionDto.setId(applyRegionMsg.getId());
        Region region = regionService.getRegionByPrimaryKey(applyRegionMsg.getRegionId());
        applyRegionDto.setRegionName(region.getName());
        WxUser wxUser = wxUserService.getWxUserByPrimaryKey(applyRegionMsg.getWxUserId());
        applyRegionDto.setApplyWxUser(wxUserService.getWxUserDto(wxUser));
        return applyRegionDto;
    }


    @Override
    public List<ApplyRegionDto> getApplyRegionMsg(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ApplyRegionMsgExample example = new ApplyRegionMsgExample();
        example.createCriteria().andStatusEqualTo(0);
        List<ApplyRegionMsg> messages = applyRegionDao.selectByExample(example);
        List<ApplyRegionDto> applyRegionDtos = new ArrayList<>();
        for (ApplyRegionMsg m : messages) {
            applyRegionDtos.add(getApplyRegionDto(m));
        }
        return applyRegionDtos;
    }

}
