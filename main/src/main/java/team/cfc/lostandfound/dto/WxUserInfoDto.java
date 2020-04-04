package team.cfc.lostandfound.dto;

public class WxUserInfoDto {
    /**
     * OpenID：是此网站上或应用中唯一对应用户身份的标识，
     * 网站或应用可将此ID进行存储，便于用户下次登录时辨识
     * 其身份，或将其与用户在网站上或应用中的原有账号进行绑定。
     */
    private String openId;
    /**
     * name：用户昵称
     */
    private String name;

    /**
     * authToken：access_token
     */
    private String authToken;
    /**
     * authRefreshToken：是专用于刷新access token的token。
     */
    private String authRefreshToken;
    /**
     * scope：权限
     */
    private String scope;
    /**
     * expiresIn：access_token的过期时间
     */
    private Integer expiresIn;
    /**
     * icon：用户头像
     */
    private String icon;
    /**
     * gender：用户性别
     */
    private String gender;
    /**
     * loginId：全局唯一标识
     */
    private String loginId;
}
