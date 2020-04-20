package team.cfc.lostandfound.service.impl;

import cn.hutool.core.date.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.dao.*;
import team.cfc.lostandfound.dto.RegionDto;
import team.cfc.lostandfound.model.*;
import team.cfc.lostandfound.service.RegionService;
import team.cfc.lostandfound.service.WxUserService;

import java.util.ArrayList;
import java.util.List;


/**
 * @author hy
 */
@Service
public class RegionServiceImpl implements RegionService {
    public static final Logger LOGGER = LoggerFactory.getLogger(RegionServiceImpl.class);

    @Autowired
    RegionDao regionDao;

    @Autowired
    RegionCreateRelationDao createRelationDao;

    @Autowired
    RegionManagerRelationDao managerRelationDao;

    @Autowired
    ApplyManagerMsgDao applyManagerMsgDao;

    @Autowired
    ApplyRegionMsgDao applyRegionMsgDao;

    @Autowired
    WxUserService wxUserService;


    private RegionDto getRegionDto(Region region) {
        int regionId = region.getId();
        RegionDto regionDto = new RegionDto();
        regionDto.setName(region.getName());
        RegionCreateRelationExample createExample = new RegionCreateRelationExample();
        createExample.createCriteria().andRegionIdEqualTo(regionId);
        List<RegionCreateRelation> createRelations = createRelationDao.selectByExample(createExample);
        regionDto.setCreateUserId(createRelations.get(0).getWxUserId());
        regionDto.setManagerId(new ArrayList<>());
        RegionManagerRelationExample example = new RegionManagerRelationExample();
        example.createCriteria().andRegionIdEqualTo(regionId);
        List<RegionManagerRelation> relations = managerRelationDao.selectByExample(example);
        if (relations == null || relations.size() < 1) {
            return regionDto;
        }
        List<Integer> mangers = regionDto.getManagerId();
        for (RegionManagerRelation relation : relations) {
            int wxUserId = relation.getWxUserId();
            mangers.add(wxUserId);
        }
        return regionDto;
    }

    @Override
    public List<RegionDto> getAllRegion() {
        RegionExample example = new RegionExample();
        List<Region> regionList = regionDao.selectByExample(example);
        List<RegionDto> regionDtoList = new ArrayList<>();
        for (Region region : regionList) {
            regionDtoList.add(getRegionDto(region));
        }
        return regionDtoList;
    }

    @Override
    public CommonResult createRegion(int regionId, String username) {
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        int wxUserId = wxUser.getId();
        long timestamp = System.currentTimeMillis();
        timestamp = timestamp - timestamp % (24 * 60 * 60 * 1000) - 8 * 60 * 60 * 1000;
        DateTime today = new DateTime(timestamp);
        ApplyRegionMsgExample msgExample = new ApplyRegionMsgExample();
        msgExample.createCriteria().andApplyTimeGreaterThanOrEqualTo(new DateTime(timestamp))
                .andWxUserIdEqualTo(wxUserId);
        long todayCount = applyRegionMsgDao.countByExample(msgExample);
        if (todayCount > 1) {
            return CommonResult.failed("每日仅可申请一次");
        }
        ApplyRegionMsg msg = new ApplyRegionMsg();
        msg.setApplyTime(new DateTime());
        msg.setRegionId(regionId);
        msg.setWxUserId(wxUserId);
        applyRegionMsgDao.insertSelective(msg);
        return CommonResult.success("申请成功");
    }

    @Override
    public CommonResult applyRegionManager(int regionId, String username) {
        Region region = regionDao.selectByPrimaryKey(regionId);
        if (region.getStatus() == 1) {
            return CommonResult.failed("该区域未被创建");
        }
        RegionManagerRelationExample example = new RegionManagerRelationExample();
        example.createCriteria().andRegionIdEqualTo(regionId);
        long count = managerRelationDao.countByExample(example);
        if (count >= 2) {
            return CommonResult.failed("该区域管理员已达上限");
        }
        int wxUserId = wxUserService.getWxUserByOpenId(username).getId();
        ApplyManagerMsg applyManagerMsg = new ApplyManagerMsg();
        applyManagerMsg.setRegionId(regionId);
        applyManagerMsg.setRegionId(wxUserId);
        applyManagerMsgDao.insertSelective(applyManagerMsg);
        return CommonResult.success("等待管理员审核");
    }

}
