package team.cfc.lostandfound.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * apply_manager_msg
 * @author hy
 */
@Data
@RequiredArgsConstructor
public class ApplyManagerMsg implements Serializable {
    private Integer id;

    private Integer regionId;

    private Integer wxUserId;

    private static final long serialVersionUID = 1L;
}