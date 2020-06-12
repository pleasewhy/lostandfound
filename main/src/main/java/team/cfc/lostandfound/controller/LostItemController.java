package team.cfc.lostandfound.controller;


import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.cfc.lostandfound.common.api.CommonPage;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.common.api.SomeUtils;
import team.cfc.lostandfound.dto.LostItemDto;
import team.cfc.lostandfound.model.LostItem;
import team.cfc.lostandfound.model.Region;
import team.cfc.lostandfound.model.WxUser;
import team.cfc.lostandfound.model.WxUserInfo;
import team.cfc.lostandfound.service.LostItemService;
import team.cfc.lostandfound.service.WxUserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping
@RestController
@Api(tags = "LostItemController", description = "丢失物品的crud")
public class LostItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LostItemController.class);


    @Autowired
    WxUserService wxUserService;

    @Value("${web.upload-path}")
    String uploadPath;

    @Value("${web.endpoint}")
    String endpoint;

    @Autowired
    LostItemService lostItemService;

    @ApiOperation("上传文件")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public CommonResult uploadFile(MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            filename = filename.substring(0, filename.indexOf('.')) + "_" + (new Date()).getTime() + ".jpg";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "_" + filename;
            objectName = objectName.replaceAll(" ", "");

            SomeUtils.saveImage(file, uploadPath + File.separator + objectName);
            LOGGER.info("{} 上传成功", objectName);
            Map<String, String> map = new HashMap<>();
            map.put("name", filename);
            map.put("url", endpoint + "/" + objectName);
            return CommonResult.success(map);
        } catch (IOException e) {
            LOGGER.info("上传发生错误: {}！", e.getMessage());
            return CommonResult.failed("上传图片失败");
        }
    }


    @ApiOperation("上传丢失物")
    @RequestMapping(value = "/uploadLostItem", method = RequestMethod.POST)
    public CommonResult uploadLostItem(@RequestBody LostItemDto lostItemDto, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        LostItem lostItem = new LostItem();
        WxUserInfo userInfo = new WxUserInfo();
        BeanUtil.copyProperties(lostItemDto, lostItem);
        BeanUtils.copyProperties(lostItemDto, userInfo);
        userInfo.setWxUserId(wxUser.getId());

        try {
            Region selectRegion = wxUserService.getSelectRegion(wxUser);
            lostItem.setRegionId(selectRegion.getId());
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return CommonResult.failed();
        }
        lostItem.setPickerId(wxUser.getId());

        int c = lostItemService.create(lostItem, userInfo);

        if (c > 0) {
            return CommonResult.success(null, "上传成功");
        } else {
            return CommonResult.failed("上传失败");
        }
    }

    @RequestMapping(value = "/listLostItem", method = RequestMethod.GET)
    public CommonResult listLostItem(@RequestParam(defaultValue = "1") int pageNum,
                                     @RequestParam(defaultValue = "10") int pageSize,
                                     int regionId,
                                     String label) {
        List<LostItem> lostItems = lostItemService.listLostItem(pageNum, pageSize, 1, regionId, label);
        List<LostItemDto> lostItemDtos = new ArrayList<>();
        for (LostItem item : lostItems) {
            lostItemDtos.add(lostItemService.convert(item));
        }
        return CommonResult.success(CommonPage.restPage(lostItemDtos));
    }

    @ApiOperation("搜索未归还的丢失物")
    @RequestMapping("/search")
    public CommonResult search(HttpServletRequest request,
                               String keyword,
                               @RequestParam(defaultValue = "全部") String searchTime,
                               String label,
                               @RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "8") int pageSize) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        try {
            Region region = wxUserService.getSelectRegion(wxUser);
            searchTime = searchTime.trim();
            List<String> timeInterval = lostItemService.getTimeInterval(searchTime);
            List<LostItem> lostItems = lostItemService.search(keyword, pageNum, pageSize, LostItemService.DETERMINE,
                    region.getId(), label, timeInterval);

            List<LostItemDto> lostItemDtos = new ArrayList<>();
            for (LostItem item : lostItems) {
                lostItemDtos.add(lostItemService.convert(item));
            }
            return CommonResult.success(CommonPage.restPage(lostItemDtos));
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return CommonResult.failed("请选择区域");
        }
    }

    @ApiOperation("测试并发量")
    @GetMapping("/searchTest")
    public CommonResult searchTest(HttpServletRequest request,
                               String keyword,
                               @RequestParam(defaultValue = "全部") String searchTime,
                               String label,
                               @RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "8") int pageSize) {
        WxUser wxUser = wxUserService.getWxUserByOpenId("oahMt5BAqfrpaa8hwNlJVdZDfLm8");
        try {
            Region region = wxUserService.getSelectRegion(wxUser);
            List<String> timeInterval = lostItemService.getTimeInterval(searchTime);
            List<LostItem> lostItems = lostItemService.search(keyword, pageNum, pageSize, LostItemService.DETERMINE,
                    region.getId(), label, timeInterval);
            List<LostItemDto> lostItemDtos = new ArrayList<>();
            for (LostItem item : lostItems) {
                lostItemDtos.add(lostItemService.convert(item));
            }
            return CommonResult.success(CommonPage.restPage(lostItemDtos));
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return CommonResult.failed("请选择区域");
        }
    }


//    @ApiOperation("搜索已经归还的丢失物")
//    @RequestMapping("/searchReturn")
//    public CommonResult searchReturn(String keyword,
//                                     String lostDate,
//                                     @RequestParam(defaultValue = "1") int pageNum,
//                                     @RequestParam(defaultValue = "10") int pageSize) {
//        List<LostItem> lostItems = lostItemService.search(keyword, pageNum, pageSize, "全部", null);
//        List<LostItemDto> lostItemDtos = new ArrayList<>();
//        for (LostItem item : lostItems) {
//            lostItemDtos.add(lostItemService.convert(item));
//        }
//        return CommonResult.success(CommonPage.restPage(lostItemDtos));
//    }

}
