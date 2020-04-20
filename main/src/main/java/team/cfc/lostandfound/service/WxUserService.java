package team.cfc.lostandfound.service;

import org.springframework.security.core.userdetails.UserDetails;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.model.WxUser;

import java.util.Map;

public interface WxUserService {
    /**
     * 登录
     *
     * @param code 微信登录code
     * @return 成功返回token, 失败返回信息
     */
    public Map<String, Object> login(String code);

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


}
