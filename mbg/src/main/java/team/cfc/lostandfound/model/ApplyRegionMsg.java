package team.cfc.lostandfound.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * apply_region_msg
 * @author 
 */
@Data
public class ApplyRegionMsg implements Serializable {
    private Integer id;

    /**
     * 区域id
     */
    private Integer regionId;

    /**
     * 申请微信用户id
     */
    private Integer wxUserId;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 0->未审核，1->同意,2->不同意
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}