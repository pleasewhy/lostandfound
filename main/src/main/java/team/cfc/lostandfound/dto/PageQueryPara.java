package team.cfc.lostandfound.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PageQueryPara {
    @NotNull
    private Integer pageNum;
    @NotNull
    private Integer pageSize;
    @NotNull
    private Integer status;
}
