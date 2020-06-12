package team.cfc.lostandfound.dto;

import lombok.Data;
import team.cfc.lostandfound.model.WxUser;

import java.io.Serializable;
import java.util.List;

/**
 * @author hy
 */
@Data
public class RegionDto implements Serializable {
    private int id;
    private String name;
    private String receiveLocation;
    private WxUserDto creator;
    private List<WxUserDto> manager;
    private static final long serialVersionUID = 1L;
}
