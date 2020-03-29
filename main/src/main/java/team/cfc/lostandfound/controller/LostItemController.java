package team.cfc.lostandfound.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.cfc.lostandfound.common.api.CommonPage;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.common.api.SomeUtils;
import team.cfc.lostandfound.dto.LostItemDto;
import team.cfc.lostandfound.model.LostItem;
import team.cfc.lostandfound.model.Picker;
import team.cfc.lostandfound.service.LostItemService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping
@RestController
@Api(tags = "LostItemController", description = "丢失物品的crud")
public class LostItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LostItemController.class);
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "_" + filename;
            objectName = objectName.replaceAll(" ", "");
            // 使用putObject上传一个文件到存储桶中
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
    public CommonResult uploadLostItem(@RequestBody LostItemDto lostItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CommonResult.failed("上传失败");
        }
        int c = lostItemService.create(lostItem);
        if (c > 0) {
            return CommonResult.success("上传成功");
        } else {
            return CommonResult.failed("上传失败");
        }
    }

    @ApiOperation("丢失物品分页")
    @RequestMapping(value = "/listLostItem", method = RequestMethod.GET)
    public CommonResult listLostItem(@RequestParam(defaultValue = "1") int pageNum,
                                     @RequestParam(defaultValue = "10") int pageSize,
                                     @RequestParam(defaultValue = "0") int status) {
        List<LostItem> lostItems = lostItemService.listLostItem(pageNum, pageSize, status);
        return CommonResult.success(CommonPage.restPage(lostItems));
    }

    @ApiOperation("搜索未归还的丢失物")
    @RequestMapping("/searchNotReturn")
    public CommonResult searchNotReturn(String keyword,
                                        Date startDate,
                                        Date endDate,
                                        @RequestParam(defaultValue = "1") int pageNum,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println(endDate);
        List<LostItem> lostItems = lostItemService.search(keyword,pageNum,pageSize,0, startDate, endDate);
        return CommonResult.success(CommonPage.restPage(lostItems));
    }

    @ApiOperation("搜索已经归还的丢失物")
    @RequestMapping("/searchReturn")
    public CommonResult searchReturn(String keyword,
                                        Date startDate,
                                        Date endDate,
                                        @RequestParam(defaultValue = "1") int pageNum,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println(endDate);
        List<LostItem> lostItems = lostItemService.search(keyword,pageNum,pageSize,1, startDate, endDate);
        return CommonResult.success(CommonPage.restPage(lostItems));
    }
}
