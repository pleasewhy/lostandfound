package team.cfc.lostandfound.dto;

import lombok.Data;
import team.cfc.lostandfound.model.WxUser;

import java.io.Serializable;

@Data
public class WxUserDto implements Serializable {
    private int id;
    private String nickName;
    private String avatarUrl;
    private static final long serialVersionUID = 1L;

}
