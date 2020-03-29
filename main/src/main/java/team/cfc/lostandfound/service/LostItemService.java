package team.cfc.lostandfound.service;

import team.cfc.lostandfound.dto.LostItemDto;
import team.cfc.lostandfound.model.LostItem;

import java.util.Date;
import java.util.List;

public interface LostItemService {
    public int create(LostItemDto lostItemDto);
    public List<LostItem> listLostItem(int pageNum, int pageSize, int status);
    public List<LostItem> search(String keywords,int pageNum, int pageSize, int status, Date startDate, Date endDate);
}
