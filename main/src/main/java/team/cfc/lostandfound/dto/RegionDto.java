package team.cfc.lostandfound.dto;

import lombok.Data;
import team.cfc.lostandfound.model.WxUser;

import java.util.List;

/**
 * @author hy
 */
@Data
public class RegionDto {
    private String name;
    private int createUserId;
    private List<Integer> managerId;
}
