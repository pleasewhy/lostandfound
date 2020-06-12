package team.cfc.lostandfound.dto;

import lombok.Data;
import team.cfc.lostandfound.model.Region;

import java.io.Serializable;
import java.util.List;

@Data
public class WxUserInfoDto implements Serializable {
    Region createRegion;
    List<Region> manageRegions;
    Region selectRegion;
    private static final long serialVersionUID = 1L;
}
