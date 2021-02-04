package team.cfc.lostandfound.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApplyLostMsgDto implements Serializable {
    private Integer applyRecordId;
    private LostItemDto articleDetail;
    private String applyTime;
    private Integer statusCode;
    private Integer handleStatus;
    private static final long serialVersionUID = 1L;

}
