package team.cfc.lostandfound.service;

import team.cfc.lostandfound.dto.ApplyManagerDto;
import team.cfc.lostandfound.model.ApplyManagerMsg;
import team.cfc.lostandfound.model.WxUser;

import java.util.List;

public interface ApplyManagerMsgService {

    public static final int CHECKING = 0;
    public static final int SUCCESS = 1;
    public static final int FAILED = 2;
    public static final int OUTDATE = 3;

    /**
     * 通过id查询管理员申请
     *
     * @return
     */
    ApplyManagerMsg selectByPrimaryKey(int id);

    /**
     * 插入申请信息
     *
     * @param managerMsg
     * @return
     */
    int insertApplyMsg(ApplyManagerMsg managerMsg);

    /**
     * 将申请信息转化为前端可用的信息
     *
     * @param applyManagerMsg
     * @return
     */
    ApplyManagerDto convert(ApplyManagerMsg applyManagerMsg);

    int updateApplyManagerMsg(ApplyManagerMsg msg);

    /**
     * 得到我申请管理员的信息
     */
    List<ApplyManagerDto> getMyApplyManager(WxUser wxUser);

    /**
     * 得到我创建区域的管理员申请信息
     * @param wxUser
     * @return
     */
    List<ApplyManagerDto> getApplyManagerMsg(WxUser wxUser);

    List<ApplyManagerDto> convert(List<ApplyManagerMsg> applyManagerMsgs);

}
