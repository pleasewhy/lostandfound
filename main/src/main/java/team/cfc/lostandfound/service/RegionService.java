package team.cfc.lostandfound.service;

import team.cfc.lostandfound.dto.RegionDto;
import team.cfc.lostandfound.model.Region;
import team.cfc.lostandfound.model.WxUser;

import java.util.List;

/**
 * @author hy
 */
public interface RegionService {
    /**
     * 得到全部的
     *
     * @return 返回全部的已经创建区域
     */
    List<RegionDto> getAllRegion(int status);


    /**
     * 通过区域id获得区域
     *
     * @param id
     * @return
     */
    Region getRegionByPrimaryKey(int id);

    /**
     * 获得区域的全部管理员
     *
     * @param regionId 区域id
     * @return 全部管理员
     */
    List<WxUser> getRegionManager(int regionId);

    /**
     * 获得区域的创建者
     *
     * @param regionId 区域id
     * @return 区域创建者
     */
    WxUser getRegionCreator(int regionId);


    /**
     * 生成区域对应的邀请码
     *
     * @param region 区域
     */
    String generateCreateInviteCode(Region region) throws RuntimeException;

    /**
     * 验证邀请码
     *
     * @param code 邀请码
     * @return 邀请码对应的区域
     */
    Region verifyInviteCode(String code) throws Exception;

    /**
     * 移除相关的邀请码
     *
     * @param code 管理员邀请码
     */
    void removeInviteCode(String code, Region region);

    /**
     * 设置区域管理员
     *
     * @param region
     * @param wxUser
     * @throws Exception
     */
    public void setRegionCreator(Region region, WxUser wxUser) throws Exception;

    /**
     * 添加区域的manager
     *
     * @param regionId 添加manager的区域
     * @param wxUserId manager的用户id
     * @return
     */
    int addRegionManager(int regionId, int wxUserId);

    /**
     * 将数据库model转化为前端可用对象
     *
     * @param region
     * @return
     */
    RegionDto convert(Region region);

    /**
     * 同上
     */
    List<RegionDto> convert(List<Region> regions);

    /**
     * 添加区域
     */
    int addRegion(String regionName);

}
