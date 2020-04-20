package team.cfc.lostandfound.model;

import java.io.Serializable;
import lombok.Data;

/**
 * region_manager_relation
 * @author 
 */
@Data
public class RegionManagerRelation implements Serializable {
    private Integer id;

    /**
     * 区域id
     */
    private Integer regionId;

    /**
     * 微信用户id
     */
    private Integer wxUserId;

    private static final long serialVersionUID = 1L;
}