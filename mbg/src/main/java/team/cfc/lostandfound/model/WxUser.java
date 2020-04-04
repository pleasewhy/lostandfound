package team.cfc.lostandfound.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * wx_user
 * @author 
 */
@Data
public class WxUser implements Serializable {
    private Integer id;

    /**
     * 微信用户唯一标识
     */
    private String openId;

    /**
     * 会话密匙，解密隐私数据
     */
    private String sessionKey;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}