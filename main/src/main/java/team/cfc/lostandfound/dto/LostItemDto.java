package team.cfc.lostandfound.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import team.cfc.lostandfound.model.Picker;

import javax.validation.constraints.NotNull;
import java.util.Date;


@ApiModel(value = "lostItemDto",description = "上传丢失物品的对象")
@Data
public class LostItemDto {
    private Integer id;
    /**
     * 丢失物名称(非空)
     */
    @NotNull
    private String name;

    /**
     * 丢失物描述
     */
    private String description;

    /**
     * 拾取人信息
     */
    private Picker picker;

    /**
     * 拾取日期
     */
    private Date pickDate;

    /**
     * 拾取地点
     */
    private String pickAddress;

    /**
     * 物品图片url
     */
    private String image;

    /**
     * 0->未归还, 1->归还
     */
    private Integer status;

    /**
     * 失主姓名
     */
    private String ownerName;

    /**
     * 失主电话
     */
    private String ownerTelephone;

    /**
     * 归还时间
     */
    private Date returnTime;

}
