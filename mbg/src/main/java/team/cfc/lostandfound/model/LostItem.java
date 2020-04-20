package team.cfc.lostandfound.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * lost_item
 * @author 
 */
@Data
public class LostItem implements Serializable {
    private Integer id;

    /**
     * 丢失物名称(非空)
     */
    private String name;

    /**
     * 丢失物描述
     */
    private String description;

    /**
     * 拾取人信息 对应wx_user的open_id和app_user的username
     */
    private String picker;

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

    /**
     * 区域id
     */
    private Integer regionId;

    private static final long serialVersionUID = 1L;
}