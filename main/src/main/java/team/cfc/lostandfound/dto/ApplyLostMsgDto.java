package team.cfc.lostandfound.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class ApplyLostMsgDto implements Serializable {
    private Integer applyRecordId;
    private LostItemDto articleDetail;
    private Integer statusCode;
    private Integer handleStatus;
    private static final long serialVersionUID = 1L;
}
