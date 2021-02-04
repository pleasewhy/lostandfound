package team.cfc.lostandfound.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import team.cfc.lostandfound.dao.ApplyLostMsgDao;
import team.cfc.lostandfound.dao.ApplyManagerMsgDao;
import team.cfc.lostandfound.dto.ApplyLostMsgDto;
import team.cfc.lostandfound.dto.LostItemDto;
import team.cfc.lostandfound.model.*;
import team.cfc.lostandfound.service.ApplyLostMsgService;
import team.cfc.lostandfound.service.LostItemService;
import team.cfc.lostandfound.service.RegionService;
import team.cfc.lostandfound.service.WxUserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplyLostMsgServiceImpl implements ApplyLostMsgService {

    @Autowired
    ApplyManagerMsgDao applyManagerMsgDao;

    @Autowired
    ApplyLostMsgDao applyLostMsgDao;

    @Autowired
    WxUserService wxUserService;

    @Autowired
    RegionService regionService;

    @Autowired
    LostItemService lostItemService;


    @Override
    public int insertApplyMsg(ApplyLostMsg applyLostMsg) {
        return applyLostMsgDao.insertSelective(applyLostMsg);
    }

    @Override
    public ApplyLostMsg selectByPrimaryKey(int id) {
        return applyLostMsgDao.selectByPrimaryKey(id);
    }


    @Override
    public List<ApplyLostMsg> getApplyLostInMyRegion(WxUser wxUser) {
        List<Region> regionList = wxUserService.getMyAllRegion(wxUser);
        List<ApplyLostMsg> msgs = new ArrayList<>();
        ApplyLostMsgExample example = new ApplyLostMsgExample();
        for (Region region : regionList) {
            example.createCriteria()
                    .andRegionIdEqualTo(region.getId())
                    .andStatusEqualTo(0);
            List<ApplyLostMsg> applyLostMsgs = applyLostMsgDao.selectByExample(example);
            if (!CollectionUtils.isEmpty(applyLostMsgs)) {
                for (ApplyLostMsg msg : applyLostMsgs) {
                    LostItem item = lostItemService.getLostItemById(msg.getLostItemId());
                    if (item.getRecoverMethod() == 0) {
                        msgs.add(msg);
                    }
                }
            }
            example.clear();
        }
        return msgs;
    }

    @Override
    public List<ApplyLostMsg> getApplyLostMyLostItem(WxUser wxUser) {
        List<LostItemDto> lostItem = lostItemService.getMySubmitLost(wxUser, LostItemService.DETERMINE);
        List<ApplyLostMsg> msgs = new ArrayList<>();
        ApplyLostMsgExample example = new ApplyLostMsgExample();
        for (LostItemDto dto : lostItem) {
            example.createCriteria().andLostItemIdEqualTo(dto.getArticleId())
                    .andStatusEqualTo(ApplyLostMsgService.CHECKING);
            List<ApplyLostMsg> applyLostMsgs = applyLostMsgDao.selectByExample(example);
            if (!CollectionUtils.isEmpty(applyLostMsgs)) {
                msgs.addAll(applyLostMsgs);
            }
            example.clear();
        }
        return msgs;
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd hh:mm");
    @Override
    public List<ApplyLostMsgDto> getMyApplyLost(WxUser wxUser, int status) {
        List<ApplyLostMsg> msgs = new ArrayList<>();
        ApplyLostMsgExample example = new ApplyLostMsgExample();
        example.createCriteria().andWxUserIdEqualTo(wxUser.getId()).andStatusEqualTo(status);
        List<ApplyLostMsg> applyLostMsgs = applyLostMsgDao.selectByExample(example);
        if (!CollectionUtils.isEmpty(applyLostMsgs)) {
            msgs.addAll(applyLostMsgs);
        }
        List<LostItem> lostItems = new ArrayList<>();
        List<ApplyLostMsgDto> res = new ArrayList<>();
        for (ApplyLostMsg msg : msgs) {
            LostItem lostItem = lostItemService.getLostItemById(msg.getLostItemId());
            ApplyLostMsgDto dto = new ApplyLostMsgDto();
            dto.setApplyRecordId(msg.getId());
            dto.setApplyTime(simpleDateFormat.format(msg.getApplyTime()));
            dto.setArticleDetail(lostItemService.convert(lostItem));
            dto.setStatusCode(status);
            res.add(dto);
        }
        return res;
    }


    @Override
    public List<ApplyLostMsg> getAllApplyLostMsg() {
        ApplyLostMsgExample example = new ApplyLostMsgExample();
        example.createCriteria().andApplyTimeIsNotNull();
        return applyLostMsgDao.selectByExample(example);
    }

    @Override
    public int updateApplyLostMsg(ApplyLostMsg msg) {
        return applyLostMsgDao.updateByPrimaryKeySelective(msg);
    }
}
