package team.cfc.lostandfound.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * apply_manager_msg
 * @author 
 */
@Data
public class ApplyManagerMsg implements Serializable {
    private Integer id;

    private Integer regionId;

    private Integer wxUserId;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 0->未审核，1->审核成功,2->审核失败
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}