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
     * 0->已被创建，1->未被创建
     */
    private Integer status;

    /**
     * 区域的失物招领中心
     */
    private String receiveLocation;

    private static final long serialVersionUID = 1L;
}