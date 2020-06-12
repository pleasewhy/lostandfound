package team.cfc.lostandfound.service;

import org.springframework.security.core.userdetails.UserDetails;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.dto.ApplyRegionDto;
import team.cfc.lostandfound.dto.LostItemDto;
import team.cfc.lostandfound.model.LostItem;
import team.cfc.lostandfound.model.WxUser;

import java.util.List;

public interface AdminService {

    /**
     * 通过用户名加载对应的UserDetails
     * @param username 用户名
     * @return 对应的UserDetails
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     */
    CommonResult login(String username, String password);

    /**
     * 同意申请区域
     * @param username
     * @param regionId
     * @return
     */
    CommonResult acceptApplyRegion(String username, int regionId);

    /**
     * 返回申请信息
     * @return 申请信息
     */
    List<ApplyRegionDto> getApplyRegionMsg(int pageNum, int pageSize);

}
