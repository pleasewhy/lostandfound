package team.cfc.lostandfound.service;

import team.cfc.lostandfound.dto.LostItemDto;
import team.cfc.lostandfound.model.LostItem;
import team.cfc.lostandfound.model.WxUser;
import team.cfc.lostandfound.model.WxUserInfo;

import java.util.List;

public interface LostItemService {
    public static final int CHECKING = 0;  // 审核中
    public static final int DETERMINE = 1;  // 已经确定
    public static final int APPLYING = 2;  // 当前物品正在北申请
    public static final int RETURNED = 3;  // 已归还
    public static final int OUTDATE = 4;

    /**
     * 将lostItem插入数据库
     *
     * @param lostItem 丢失物信息
     * @return 受到影响的行数
     */
    int create(LostItem lostItem, WxUserInfo userInfo);

    public String pickTimeConvert(String pickTime);

    /**
     * 分页查询丢失物品(只查询最近14天内的)
     *
     * @param pageNum  查询的页码
     * @param pageSize 每页的数量
     * @return 对应页的丢失物信息
     */
    List<LostItem> listLostItem(int pageNum, int pageSize, int status, int regionId, String label);

    /**
     * 丢失物模糊匹配
     *
     * @param keywords     关键词
     * @param pageNum      查询的页码
     * @param pageSize     每页的数量
     * @param label        物品的分类
     * @param timeInterval 时间区间，若为空则为全部，timeInterval[0],timeInterval[1]分别
     *                     为起止时间，可为空
     * @param status       0未归还,1归还,2全部
     * @param regionId     搜索的区域，-1为全部
     * @return 对应的丢失物信息
     */
    List<LostItem> search(String keywords, int pageNum, int pageSize, int status, int regionId,
                          String label, List<String> timeInterval);

    /**
     * 将数据库中的信息转化未前端可用信息
     *
     * @param lostItem 数据库的model
     * @return 前端可用的数据
     */
    LostItemDto convert(LostItem lostItem);

    List<LostItemDto> convert(List<LostItem> lostItems);

    /**
     * 得到搜索时间区间
     *
     * @param searchTime 今天，昨天，七天内，七天前，全部
     * @return
     */
    List<String> getTimeInterval(String searchTime);

    /**
     * 通过id获取丢失物
     *
     * @param id
     * @return
     */
    LostItem getLostItemById(int id);


    public int updateByPrimaryKeySelective(LostItem record);

    /**
     * 得到我提交的丢失物品
     *
     * @param wxUser
     * @return
     */
    public List<LostItemDto> getMySubmitLost(WxUser wxUser,int status);

    /**
     * 得到自己管理区域的未确定丢失物品提交
     *
     * @param wxUser
     * @return
     */
    public List<LostItemDto> getNotCheckLostInMyRegion(WxUser wxUser);

    public List<LostItem> getAllLostItem();


}
