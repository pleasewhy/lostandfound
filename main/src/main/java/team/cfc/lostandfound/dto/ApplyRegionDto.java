package team.cfc.lostandfound.dto;

import lombok.Data;
import team.cfc.lostandfound.model.WxUser;

@Data
public class ApplyRegionDto {
    private String regionName;
    private WxUser applyWxUser;
}
