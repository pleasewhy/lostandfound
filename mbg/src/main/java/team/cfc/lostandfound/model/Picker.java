package team.cfc.lostandfound.model;

import java.io.Serializable;
import lombok.Data;

/**
 * picker
 * @author 
 */
@Data
public class Picker implements Serializable {
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * qq号
     */
    private String qq;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 微信用户的唯一标识符
     */
    private String openId;

    private static final long serialVersionUID = 1L;
}