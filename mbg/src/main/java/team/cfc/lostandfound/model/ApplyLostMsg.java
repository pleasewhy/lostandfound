package team.cfc.lostandfound.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * apply_lost_msg
 * @author 
 */
@Data
public class ApplyLostMsg implements Serializable {
    private Integer id;

    private Integer wxUserId;

    private Integer lostItemId;

    /**
     * 区域id
     */
    private Integer regionId;

    /**
     * 0->未处理,1->成功,2->失败
     */
    private Integer status;

    /**
     * 申请时间
     */
    private Date applyTime;

    private static final long serialVersionUID = 1L;
}