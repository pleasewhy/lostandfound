package team.cfc.lostandfound.service;

import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.dto.RegionDto;

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
    public List<RegionDto> getAllRegion();

    /**
     * 申请成为区域的管理员
     *
     * @param regionId 申请区域id
     * @param username 申请用户名
     */
    public CommonResult applyRegionManager(int regionId, String username);

    CommonResult createRegion(int regionId, String username);

}
