package team.cfc.lostandfound.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.cfc.lostandfound.model.ApplyLostMsg;
import team.cfc.lostandfound.model.LostItem;
import team.cfc.lostandfound.service.ApplyLostMsgService;
import team.cfc.lostandfound.service.LostItemService;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ApplyLostItemSchedule {

    private static Logger logger = LoggerFactory.getLogger(ApplyLostItemSchedule.class);

    @Resource
    ApplyLostMsgService applyLostMsgService;

    @Resource
    LostItemService lostItemService;


    long oneDay = 1000 * 60 * 60 * 24;

    @Scheduled(fixedRate = 1000 * 60 * 5)
    public void configTasks() {
        logger.info("丢失物申请过期检测");
        List<ApplyLostMsg> msgs = applyLostMsgService.getAllApplyLostMsg();
        long now = System.currentTimeMillis();
        for (ApplyLostMsg msg : msgs) {
            if (msg.getStatus() == ApplyLostMsgService.CHECKING
                    && now - msg.getApplyTime().getTime() >= oneDay) {
                logger.info("丢失物申请{}过期", msg.getId());
                int lostLostId = msg.getLostItemId();
                LostItem lostItem = lostItemService.getLostItemById(lostLostId);
                lostItem.setStatus(LostItemService.DETERMINE);
                msg.setStatus(ApplyLostMsgService.OUTDATE);
                applyLostMsgService.updateApplyLostMsg(msg);
            }
        }
        List<LostItem> lostItems = lostItemService.getAllLostItem();
        for (LostItem lostItem : lostItems) {
            if (lostItem.getStatus() == LostItemService.CHECKING
                    && lostItem.getRecoverMethod() == 0
                    && now - lostItem.getSubmitTime().getTime() >= oneDay) {
                logger.info("物品{}过期", lostItem.getId());
                lostItem.setStatus(LostItemService.OUTDATE);
                lostItemService.updateByPrimaryKeySelective(lostItem);
            }
        }
    }
}
