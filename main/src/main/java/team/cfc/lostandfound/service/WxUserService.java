package team.cfc.lostandfound.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface WxUserService {
    /**
     * 登录
     * @param code 微信登录code
     * @return 成功返回token,失败返回信息
     */
    public Map<String,Object> login(String code);

    /**
     * 通过username加载UserDetails
     * @param username
     * @return
     */
    UserDetails loadUserByOpenId(String username);
}
