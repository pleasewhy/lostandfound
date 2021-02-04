package team.cfc.lostandfound.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hy
 */
@Data
public class ApplyManagerDto implements Serializable {
    private Integer id;
    private String regionName;
    private String applyWxUserName;
    private String applyWxUserAvatarUrl;
    private String applyTime;
    private static final long serialVersionUID = 1L;
}
