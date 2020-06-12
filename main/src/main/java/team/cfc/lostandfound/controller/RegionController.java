package team.cfc.lostandfound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.dto.RegionDto;
import team.cfc.lostandfound.model.Region;
import team.cfc.lostandfound.service.RegionService;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    @RequestMapping("/getAllRegion")
    public CommonResult getAllRegion(int status) {
        return CommonResult.success(regionService.getAllRegion(status));
    }

    @RequestMapping("/getRegionDetails")
    public CommonResult getRegionById(int regionId) {
        Region region = regionService.getRegionByPrimaryKey(regionId);
        if (region == null) {
            return CommonResult.failed("该区域id无效");
        }
        RegionDto regionDto = regionService.convert(region);
        return CommonResult.success(regionDto);
    }
}
