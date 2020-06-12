package team.cfc.lostandfound.model;

import java.io.Serializable;
import lombok.Data;

/**
 * wx_user_info
 * @author 
 */
@Data
public class WxUserInfo implements Serializable {
    private Integer id;

    private Integer wxUserId;

    private String avatarUrl;

    private String nickName;

    private static final long serialVersionUID = 1L;
}