package team.cfc.lostandfound.service;

import team.cfc.lostandfound.dto.LostItemDto;
import team.cfc.lostandfound.model.LostItem;

import java.util.Date;
import java.util.List;

public interface LostItemService {
    /**
     * 将lostItem插入数据库
     * @param lostItem 丢失物信息
     * @return 受到影响的行数
     */
    public int create(LostItem lostItem);

    /**
     * 分页查询丢失物品
     * @param pageNum 查询的页码
     * @param pageSize 每页的数量
     * @param status 0->未归还，已归还
     * @return 对应页的丢失物信息
     */
    public List<LostItem> listLostItem(int pageNum, int pageSize, int status);

    /**
     * 丢失物模糊匹配
     * @param keywords 关键词
     * @param pageNum 查询的页码
     * @param pageSize 每页的数量
     * @param status 0->未归还，已归还
     * @param startDate 限制时间区间的起点
     * @param endDate 限制时间区间的终点
     * @return 对应的丢失物信息
     */
    public List<LostItem> search(String keywords,int pageNum, int pageSize, int status, Date startDate, Date endDate);
}
