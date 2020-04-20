package team.cfc.lostandfound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.service.AdminService;
import team.cfc.lostandfound.service.RegionService;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    @RequestMapping("/getAllRegion")
    public CommonResult getAllRegion() {
        return CommonResult.success(regionService.getAllRegion());
    }


}
