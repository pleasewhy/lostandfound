package team.cfc.lostandfound.dto;

import lombok.Data;
import team.cfc.lostandfound.model.WxUser;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApplyRegionDto implements Serializable  {
    private int id;
    private String regionName;
    private WxUserDto applyWxUser;
    private Date applyTime;
    private static final long serialVersionUID = 1L;
}
