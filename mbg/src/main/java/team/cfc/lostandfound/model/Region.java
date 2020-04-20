package team.cfc.lostandfound.model;

import java.io.Serializable;
import lombok.Data;

/**
 * region
 * @author 
 */
@Data
public class Region implements Serializable {
    private Integer id;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 0->正常，1->审核失败,2->审核中,默认为2
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}