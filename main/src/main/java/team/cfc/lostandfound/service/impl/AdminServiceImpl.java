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

import java.lang.reflect.Array;
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
    RegionDao regionDao;

    @Autowired
    WxUserDao wxUserDao;

    @Autowired
    ApplyRegionMsgDao applyDao;

    @Autowired
    PasswordEncoder passwordEncoder;

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
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("\n\n" + authentication + "\n\n");
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
            return CommonResult.failed("密码错误");
        }
        token = tokenHead + " " + token;
        Map<String, String> map = new HashMap<>();
        map.put("toekn", token);
        return CommonResult.success(map);
    }

    public ApplyRegionDto getApplyRegionDto(ApplyRegionMsg msg) {
        ApplyRegionDto applyRegionDto = new ApplyRegionDto();
        int regionId = msg.getRegionId();
        RegionExample regionExample = new RegionExample();
        regionExample.createCriteria().andIdEqualTo(regionId);
        List<Region> regions = regionDao.selectByExample(regionExample);
        applyRegionDto.setRegionName(regions.get(0).getName());
        WxUser wxUser = wxUserDao.selectByPrimaryKey(msg.getWxUserId());
        applyRegionDto.setApplyWxUser(wxUser);
        return applyRegionDto;
    }

    public List<ApplyRegionDto> getApplyRegionMsg(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ApplyRegionMsgExample example = new ApplyRegionMsgExample();
        example.createCriteria().andStatusEqualTo(0);
        List<ApplyRegionMsg> messages = applyDao.selectByExample(example);
        List<ApplyRegionDto> applyRegionDtos = new ArrayList<>();
        for (ApplyRegionMsg m : messages) {
            applyRegionDtos.add(getApplyRegionDto(m));
        }

        return applyRegionDtos;
    }
}
