package team.cfc.lostandfound.dto;

import javax.validation.constraints.NotNull;

public class PickerDto {
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
    @NotNull
    private String openId;
}
