package team.cfc.lostandfound.service;

import team.cfc.lostandfound.dto.ApplyLostMsgDto;
import team.cfc.lostandfound.model.ApplyLostMsg;
import team.cfc.lostandfound.model.WxUser;

import java.util.List;

/**
 * 各种申请信息的service层
 */
public interface ApplyLostMsgService {
    public static final int CHECKING = 0;
    public static final int SUCCESS = 1;
    public static final int FAILED = 2;
    public static final int OUTDATE = 3;


    int insertApplyMsg(ApplyLostMsg applyLostMsg);

    ApplyLostMsg selectByPrimaryKey(int id);

    /**
     * 得到我创建区域得管理员申请信息
     *
     * @param wxUser
     * @return
     */
//    List<ApplyManagerDto> getApplyManageMsg(WxUser wxUser);

    /**
     * 得到我管理区域的丢失物申请信息
     */
    List<ApplyLostMsg> getApplyLostInMyRegion(WxUser wxUser);


    /**
     * 得到申请我提交的物品的申请信息
     */
    List<ApplyLostMsg> getApplyLostMyLostItem(WxUser wxUser);

    /**
     * 得到我的丢失物申请信息
     */
    List<ApplyLostMsgDto> getMyApplyLost(WxUser wxUser, int status);


    /**
     * 得到丢失物的全部申请信息
     */
    List<ApplyLostMsg> getAllApplyLostMsg();

    /**
     * 更新申请
     */
    int updateApplyLostMsg(ApplyLostMsg msg);



}
