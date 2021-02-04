package team.cfc.lostandfound.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.cfc.lostandfound.dao.*;
import team.cfc.lostandfound.dto.RegionDto;
import team.cfc.lostandfound.dto.WxUserDto;
import team.cfc.lostandfound.model.*;
import team.cfc.lostandfound.service.LostItemService;
import team.cfc.lostandfound.service.RedisService;
import team.cfc.lostandfound.service.RegionService;
import team.cfc.lostandfound.service.WxUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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

    @Autowired
    RedisService redisService;

    @Autowired
    WxUserInfoDao wxUserInfoDao;

    @Autowired
    ApplyLostMsgDao applyLostMsgDao;

    @Value("${redis.key.prefix.inviteCode}")
    String REDIS_KEY_PREFIX_INVITE_CODE;

    @Value("${redis.key.expire.inviteCode}")
    Long INVITE_CODE_EXPIRE_SECONDS;


    @Override
    public List<RegionDto> getAllRegion(int status) {
        RegionExample example = new RegionExample();
        if (status != 2) {
            example.createCriteria().andStatusEqualTo(status);
        }
        List<Region> regionList = regionDao.selectByExample(example);
        List<RegionDto> regionDtoList = new ArrayList<>();
        for (Region region : regionList) {
            regionDtoList.add(convert(region));
        }
        return regionDtoList;
    }


    @Override
    public Region getRegionByPrimaryKey(int id) {
        return regionDao.selectByPrimaryKey(id);
    }

    @Override
    public List<WxUser> getRegionManager(int regionId) {
        RegionManagerRelationExample example = new RegionManagerRelationExample();
        example.createCriteria().andRegionIdEqualTo(regionId);
        List<RegionManagerRelation> regionManagerRelations = managerRelationDao.selectByExample(example);
        List<WxUser> wxUsers = new ArrayList<>();
        for (RegionManagerRelation relation : regionManagerRelations) {
            wxUsers.add(wxUserService.getWxUserByPrimaryKey(relation.getWxUserId()));
        }
        return wxUsers;
    }

    @Override
    public WxUser getRegionCreator(int regionId) {
        RegionCreateRelationExample example = new RegionCreateRelationExample();
        example.createCriteria().andRegionIdEqualTo(regionId);
        List<RegionCreateRelation> regionCreateRelations = createRelationDao.selectByExample(example);
        return wxUserService.getWxUserByPrimaryKey(regionCreateRelations.get(0).getWxUserId());
    }


    @Override
    public RegionDto convert(Region region) {
        int regionId = region.getId();
        RegionDto regionDto = new RegionDto();
        regionDto.setName(region.getName());
        regionDto.setId(regionId);
        if (region.getStatus() != 0) {
            return regionDto;
        }
        regionDto.setReceiveLocation(region.getReceiveLocation());
        WxUser creator = getRegionCreator(regionId);

        WxUserDto creatorDto = wxUserService.getWxUserDto(creator);

        regionDto.setCreator(creatorDto);
        List<WxUser> mangers = getRegionManager(regionId);
        regionDto.setManager(new ArrayList<>());
        for (WxUser wxUser : mangers) {
            regionDto.getManager().add(wxUserService.getWxUserDto(wxUser));
        }
        return regionDto;
    }

    @Override
    public List<RegionDto> convert(List<Region> regions) {
        List<RegionDto> res = new ArrayList<>();
        for (Region region : regions) {
            res.add(convert(region));
        }
        return res;
    }

    @Override
    public String generateCreateInviteCode(Region region) throws RuntimeException {
        if (region.getStatus() == 0) {
            throw new RuntimeException("该区域已被创建");
        }
        String lastCode = redisService.get(REDIS_KEY_PREFIX_INVITE_CODE + region.toString());
        if (lastCode != null) {
            return lastCode;
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        while (true) {
            for (int i = 0; i < 6; ++i) {
                int randNum = random.nextInt(10);
                sb.append(randNum);
            }
            String res = redisService.get(REDIS_KEY_PREFIX_INVITE_CODE + sb.toString());
            if (res == null) {
                break;
            }
        }
        String code = sb.toString();
        redisService.set(REDIS_KEY_PREFIX_INVITE_CODE + code, region.getId().toString());
        redisService.set(REDIS_KEY_PREFIX_INVITE_CODE + region.toString(), code);
        redisService.expire(REDIS_KEY_PREFIX_INVITE_CODE + code, INVITE_CODE_EXPIRE_SECONDS);
        redisService.expire(REDIS_KEY_PREFIX_INVITE_CODE + region.toString(), INVITE_CODE_EXPIRE_SECONDS);
        return code;
    }

    @Override
    public Region verifyInviteCode(String code) throws Exception {
        try {
            int regionId = Integer.parseInt(redisService.get(REDIS_KEY_PREFIX_INVITE_CODE + code));
            removeInviteCode(code, getRegionByPrimaryKey(regionId));
            return getRegionByPrimaryKey(regionId);
        } catch (Exception e) {
            throw new Exception("邀请码错误");
        }
    }

    @Override
    public void removeInviteCode(String code, Region region) {
        redisService.remove(REDIS_KEY_PREFIX_INVITE_CODE + code);
        redisService.remove(REDIS_KEY_PREFIX_INVITE_CODE + region.toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setRegionCreator(Region region, WxUser wxUser) throws Exception {
        if (region.getStatus() == 0) {
            throw new Exception("该区域已被创建");
        }
        RegionCreateRelationExample example = new RegionCreateRelationExample();
        example.createCriteria().andRegionIdEqualTo(region.getId());
        long count = createRelationDao.countByExample(example);
        if (count > 0) {
            throw new Exception("该区域已被创建");
        }
        Region createRegion = wxUserService.getCreateRegion(wxUser);
        if (createRegion != null) {
            throw new Exception("每个用户只能创建一个区域");
        }
        region.setStatus(LostItemService.CHECKING);
        regionDao.updateByPrimaryKeySelective(region);
        RegionCreateRelation relation = new RegionCreateRelation();
        relation.setWxUserId(wxUser.getId());
        relation.setRegionId(region.getId());
        createRelationDao.insertSelective(relation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addRegionManager(int regionId, int wxUserId) {
        RegionManagerRelation relation = new RegionManagerRelation();
        relation.setWxUserId(wxUserId);
        relation.setRegionId(regionId);
        return managerRelationDao.insertSelective(relation);
    }


    public List<ApplyLostMsg> applyLostMsg(Region region) {
        ApplyLostMsgExample example = new ApplyLostMsgExample();
        example.createCriteria().andRegionIdEqualTo(region.getId());
        List<ApplyLostMsg> msgs = applyLostMsgDao.selectByExample(example);
        return msgs;
    }

    @Override
    public int addRegion(String regionName) {
        Region region = new Region();
        region.setName(regionName);
        return regionDao.insertSelective(region);
    }

    @Override
    public int updateReionByPrimaryKey(Region region) {
        return regionDao.updateByPrimaryKeySelective(region);
    }
}
