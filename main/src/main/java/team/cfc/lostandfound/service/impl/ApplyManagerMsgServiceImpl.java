package team.cfc.lostandfound.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import team.cfc.lostandfound.dao.ApplyManagerMsgDao;
import team.cfc.lostandfound.dto.ApplyManagerDto;
import team.cfc.lostandfound.model.*;
import team.cfc.lostandfound.service.ApplyManagerMsgService;
import team.cfc.lostandfound.service.RegionService;
import team.cfc.lostandfound.service.WxUserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplyManagerMsgServiceImpl implements ApplyManagerMsgService {

    @Resource
    ApplyManagerMsgDao applyManagerMsgDao;

    @Resource
    WxUserService wxUserService;

    @Resource
    RegionService regionService;

    @Override
    public ApplyManagerMsg selectByPrimaryKey(int id) {
        return applyManagerMsgDao.selectByPrimaryKey(id);
    }

    @Override
    public int insertApplyMsg(ApplyManagerMsg managerMsg) {
        return applyManagerMsgDao.insertSelective(managerMsg);
    }

    @Override
    public ApplyManagerDto convert(ApplyManagerMsg applyManagerMsg) {
        ApplyManagerDto applyManagerDto = new ApplyManagerDto();
        applyManagerDto.setId(applyManagerMsg.getId());
        Region region = regionService.getRegionByPrimaryKey(applyManagerMsg.getRegionId());
        applyManagerDto.setRegionName(region.getName());
        WxUser wxUser = wxUserService.getWxUserByPrimaryKey(applyManagerMsg.getWxUserId());
        WxUserInfo wxUserInfo = wxUserService.getWxUserInfo(wxUser);
        applyManagerDto.setApplyWxUserName(wxUserInfo.getNickName());
        applyManagerDto.setApplyWxUserAvatarUrl(wxUserInfo.getAvatarUrl());
        return applyManagerDto;
    }

    @Override
    public int updateApplyManagerMsg(ApplyManagerMsg msg) {
        return applyManagerMsgDao.updateByPrimaryKeySelective(msg);
    }

    @Override
    public List<ApplyManagerDto> getApplyManagerMsg(WxUser wxUser) {
        Region region = wxUserService.getCreateRegion(wxUser);
        if (region == null) {
            return new ArrayList<>();
        }
        ApplyManagerMsgExample example = new ApplyManagerMsgExample();

        example.createCriteria().andRegionIdEqualTo(region.getId()).andStatusEqualTo(ApplyManagerMsgService.CHECKING);
        List<ApplyManagerMsg> msgs = applyManagerMsgDao.selectByExample(example);
        return convert(msgs);
    }

    @Override
    public List<ApplyManagerDto> getMyApplyManager(WxUser wxUser) {
        ApplyManagerMsgExample example = new ApplyManagerMsgExample();
        example.createCriteria().andWxUserIdEqualTo(wxUser.getId());
        List<ApplyManagerMsg> applyManagerMsgs = applyManagerMsgDao.selectByExample(example);
        return convert(applyManagerMsgs);
    }

    @Override
    public List<ApplyManagerDto> convert(List<ApplyManagerMsg> applyManagerMsgs) {
        List<ApplyManagerDto> res = new ArrayList<>();
        if (CollectionUtils.isEmpty(applyManagerMsgs)) {
            return res;
        }
        for (ApplyManagerMsg managerMsg : applyManagerMsgs) {
            res.add(convert(managerMsg));
        }
        return res;
    }
}
