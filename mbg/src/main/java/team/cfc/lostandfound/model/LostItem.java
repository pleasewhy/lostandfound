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
     * 丢失物描述
     */
    private String description;

    /**
     * 拾取人信息 对应wx_user的open_id和app_user的username
     */
    private Integer pickerId;

    /**
     * 拾取日期
     */
    private String pickTime;

    /**
     * 拾取地点
     */
    private String pickAddress;

    /**
     * 物品图片url
     */
    private String imageUrl;

    /**
     * 标签
     */
    private String label;

    /**
     * 所属区域id
     */
    private Integer regionId;

    /**
     * 0->未归还, 1->归还
     */
    private Integer status;

    private Date submitTime;

    /**
     * 1->寄存点领取，对应receive_location
2->自己代为保管，对应recover_details
     */
    private Integer recoverMethod;

    /**
     * 拾取人联系方式
     */
    private String recoverDetails;

    private String receiveLocation;

    private static final long serialVersionUID = 1L;
}