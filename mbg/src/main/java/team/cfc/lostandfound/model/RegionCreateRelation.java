package team.cfc.lostandfound.model;

import java.io.Serializable;
import lombok.Data;

/**
 * region_create_relation
 * @author 
 */
@Data
public class RegionCreateRelation implements Serializable {
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