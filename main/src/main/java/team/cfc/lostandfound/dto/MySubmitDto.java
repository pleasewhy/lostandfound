package team.cfc.lostandfound.dto;

import lombok.Data;

@Data
public class MySubmitDto {
    private LostItemDto articleDetail;
    private int handleStatus;
}
