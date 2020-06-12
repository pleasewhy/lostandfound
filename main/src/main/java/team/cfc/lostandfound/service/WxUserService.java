package team.cfc.lostandfound.service;

import org.springframework.security.core.userdetails.UserDetails;
import team.cfc.lostandfound.dto.ApplyLostMsgDto;
import team.cfc.lostandfound.dto.LostItemDto;
import team.cfc.lostandfound.dto.WxUserDto;
import team.cfc.lostandfound.model.LostItem;
import team.cfc.lostandfound.model.Region;
import team.cfc.lostandfound.model.WxUser;
import team.cfc.lostandfound.model.WxUserInfo;

import java.util.List;
import java.util.Map;

public interface WxUserService {
    /**
     * 登录
     *
     * @param code 微信登录code
     * @return 成功返回token, 失败返回信息
     */
    public WxUser login(String code) throws Exception;

    /**
     * 通过username加载UserDetails
     *
     * @param username open_id
     * @return 对应的userDetails
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 通过openId加载WxUser
     *
     * @param openId 微信用户标识符
     * @return WxUser
     */
    WxUser getWxUserByOpenId(String openId);

    WxUser getWxUserByPrimaryKey(int id);

    /**
     * 将wxUser转化为wxUserDto
     */
    public WxUserDto getWxUserDto(WxUser wxUser);


    /**
     * 得到微信用户管理的区域
     *
     * @param wxUser 微信用户
     * @return
     */
    List<Region> getManageRegion(WxUser wxUser);

    /**
     * 得到微信用户创建的区域
     *
     * @param wxUser 得微信用户
     */
    Region getCreateRegion(WxUser wxUser);


    /**
     * 得到用户提交的丢失物
     *
     * @param wxUser 用户
     * @return
     */
    List<LostItem> getPickLost(WxUser wxUser);

    /**
     * 得到用户选择的区域
     *
     * @param wxUser 用户
     * @return
     */
    Region getSelectRegion(WxUser wxUser) throws Exception;

    /**
     * 设置用户选择的区域
     *
     * @param wxUser 用户
     */
    void setSelectRegion(WxUser wxUser, int regionId) throws Exception;

    /**
     * 得到自己管理的全部区域
     *
     * @param wxUser
     * @return
     */
    List<Region> getMyAllRegion(WxUser wxUser);

    /**
     * 得到用户的个人信息
     *
     * @param wxUser
     * @return
     */
    WxUserInfo getWxUserInfo(WxUser wxUser);


    /**
     * 得到个人消息的数量
     *
     * @param wxUser
     * @return
     */
    public int getWxUserMsgNum(WxUser wxUser);

    /**
     * 得到个人消息
     */
    public Map<String, List> getWxUserMsg(WxUser wxUser);

    /**
     * 得到用户申请的已审核丢失物(成功或者失败)
     *
     * @return
     */
    public List<ApplyLostMsgDto> getCheckedApplyLost(WxUser wxUser);

}