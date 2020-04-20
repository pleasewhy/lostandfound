package team.cfc.lostandfound.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import team.cfc.lostandfound.common.api.CommonResult;
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
    public CommonResult getApplyRegion() {
        return CommonResult.success(regionService.getAllRegion());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(String username, String password) {
        return adminService.login(username, password);
    }
}
