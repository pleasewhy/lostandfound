package team.cfc.lostandfound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.model.Region;
import team.cfc.lostandfound.service.AdminService;
import team.cfc.lostandfound.service.RegionService;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    RegionService regionService;

    @Autowired
    AdminService adminService;

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/getApplyRegion", method = RequestMethod.GET)
    public CommonResult getApplyRegion(int pageNum, int pageSize) {
        return CommonResult.success(adminService.getApplyRegionMsg(pageNum, pageSize));
    }

    @RequestMapping(value = "/getRegionInviteCode", method = RequestMethod.GET)
    public CommonResult getInviteCode(@RequestParam("regionId") int regionId) {
        Region region = regionService.getRegionByPrimaryKey(regionId);
        try {
            String code = regionService.generateCreateInviteCode(region);
            return CommonResult.success(code);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }

    }

    @GetMapping(value = "/addRegion")
    public CommonResult addRegion(@RequestParam String regionName) {
        int cnt = regionService.addRegion(regionName);
        if (cnt > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed("添加失败");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(String username, String password) {
        return adminService.login(username, password);
    }

}
