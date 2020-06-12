package team.cfc.lostandfound.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "lostItemDto", description = "上传丢失物品的对象")
@Data
public class LostItemDto implements Serializable {

    /**
     * 物品id
     */
    private int articleId;

    /**
     * 物品创建时间
     */
    private String submitTime;

    /**
     * 丢失物所属区域
     */
    private int regionId;

    /**
     * 丢失物品图片描述url
     */
    private String imageUrl;

    /**
     * 丢失物文字描述
     */
    private String description;

    /**
     * 标签
     */
    private String label;

    /**
     * 拾取人
     */
    private int pickerId;

    /**
     * 拾取时间
     */
    private String pickTime;

    /**
     * 拾取地点
     */
    private String pickAddress;

    /**
     * 联系方式
     */
    private String recoverDetails;

    /**
     * 暂存地点
     */
    private String receiveLocation;

    /**
     * recoverDetails的用途
     * 0->暂存地点，1->联系方式
     */
    private int recoverMethod;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatarUrl;


    private static final long serialVersionUID = 1L;
}
