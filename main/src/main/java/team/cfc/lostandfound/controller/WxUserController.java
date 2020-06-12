package team.cfc.lostandfound.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.dto.*;
import team.cfc.lostandfound.model.*;
import team.cfc.lostandfound.security.util.JwtTokenUtil;
import team.cfc.lostandfound.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author hy
 */
@RestController
@RequestMapping("/wxUser")
public class WxUserController {
    private static final Logger logger = LoggerFactory.getLogger(WxUserController.class);

    @Autowired
    WxUserService wxUserService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    RegionService regionService;

    @Autowired
    RedisService redisService;

    @Autowired
    ApplyLostMsgService applyLostMsgService;

    @Autowired
    LostItemService lostItemService;

    @Autowired
    ApplyManagerMsgService applyManagerMsgService;

    @Value("${jwt.tokenHead}")
    String tokenHead;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public CommonResult login(String code) {
        if ("abcd".equals(code) || "test".equals(code)) {
            Map<String, Object> map = new HashMap<>();
            String token = jwtTokenUtil.generateToken(wxUserService.loadUserByUsername(code));
            map.put("token", tokenHead + " " + token);
            return CommonResult.success(map);
        }
        try {
            WxUser wxUser = wxUserService.login(code);
            Map<String, Object> map = new HashMap<>();
            String token = jwtTokenUtil.generateToken(wxUserService.loadUserByUsername(wxUser.getOpenId()));
            map.put("token", tokenHead + " " + token);
            return CommonResult.success(map);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return CommonResult.failed("登陆失败");
        }

    }

    @RequestMapping(value = "/applyRegionCreator", method = RequestMethod.GET)
    public CommonResult applyRegionCreator(@RequestParam("code") String code,
                                           @RequestParam("receiveLocation") String receiveLocation,
                                           HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        try {
            Region region = regionService.verifyInviteCode(code);
            region.setReceiveLocation(receiveLocation);
            regionService.setRegionCreator(region, wxUser);
            return CommonResult.success(null, "成功创建区域");
        } catch (Exception e) {
            logger.info(e.getMessage());
            return CommonResult.failed(e.getMessage());
        }
    }

    @RequestMapping(value = "/applyRegionManager", method = RequestMethod.GET)
    public CommonResult applyRegionManager(@RequestParam("regionId") int regionId, HttpServletRequest request) throws Exception {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        Region region = regionService.getRegionByPrimaryKey(regionId);
        List<Region> manageRegions = wxUserService.getManageRegion(wxUser);
        Region createRegion = wxUserService.getCreateRegion(wxUser);
        if (createRegion != null) {
            manageRegions.add(createRegion);
        }
        for (Region r : manageRegions) {
            if (r.getId().equals(region.getId())) {
                return CommonResult.failed("你已经是该区域的管理员或创建者");
            }
        }
        List<ApplyManagerDto> msgs = applyManagerMsgService.getMyApplyManager(wxUser);
        if (msgs.size() > 0) {
            return CommonResult.failed("请等待上个申请完成");
        }
        ApplyManagerMsg msg = new ApplyManagerMsg();
        msg.setWxUserId(wxUser.getId());
        msg.setRegionId(region.getId());
        msg.setApplyTime(new Date());
        applyManagerMsgService.insertApplyMsg(msg);
        return CommonResult.success(null, "申请成功");
    }

    @RequestMapping(value = "/getCreateRegion", method = RequestMethod.GET)
    public CommonResult getCreateRegion(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        Region region = wxUserService.getCreateRegion(wxUser);
        return CommonResult.success(region);
    }

    @RequestMapping(value = "/getManageRegion", method = RequestMethod.GET)
    public CommonResult getManageRegion(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        Map<String, Object> res = new HashMap<>();
        res.put("create", regionService.convert(wxUserService.getCreateRegion(wxUser)));
        res.put("manage", regionService.convert(wxUserService.getManageRegion(wxUser)));
        return CommonResult.success(res);
    }

    @RequestMapping(value = "/getPickLost", method = RequestMethod.GET)
    public CommonResult getPickLost(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        List<LostItem> lostItems = wxUserService.getPickLost(wxUser);
        return CommonResult.success(lostItems);
    }

    @PreAuthorize("hasAuthority('wxUser')")
    @RequestMapping(value = "/getRegionInfo", method = RequestMethod.GET)
    public CommonResult getRegionInfo(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        WxUserInfoDto infoDto = new WxUserInfoDto();
        Region createRegion = wxUserService.getCreateRegion(wxUser);
        List<Region> manageRegions = wxUserService.getManageRegion(wxUser);
        try {
            Region selectRegion = wxUserService.getSelectRegion(wxUser);
            infoDto.setSelectRegion(selectRegion);
        } catch (Exception e) {
            logger.info(e.getMessage());
            infoDto.setSelectRegion(null);
        }
        infoDto.setCreateRegion(createRegion);
        infoDto.setManageRegions(manageRegions);

        return CommonResult.success(infoDto);
    }

    @PreAuthorize("hasAuthority('wxUser')")
    @RequestMapping(value = "/getSelectRegion", method = RequestMethod.GET)
    public CommonResult getSelectRegion(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        Integer regionId = wxUser.getSelectRegionId();
        if (regionId == null) {
            return CommonResult.failed("你未选择区域");
        }
        Region region = regionService.getRegionByPrimaryKey(regionId);
        if (region == null) {
            return CommonResult.failed("你未选择区域");
        }
        return CommonResult.success(region);
    }

    @PreAuthorize("hasAuthority('wxUser')")
    @RequestMapping(value = "/setSelectRegion", method = RequestMethod.GET)
    public CommonResult setSelectRegion(int regionId, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        try {
            wxUserService.setSelectRegion(wxUser, regionId);
            return CommonResult.success(null, "设置成功");
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }

    @RequestMapping(value = "/applyLostItem", method = RequestMethod.GET)
    public CommonResult applyLostItem(@RequestParam("articleId") int id, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        LostItem lostItem = lostItemService.getLostItemById(id);
        if (lostItem.getStatus() != LostItemService.DETERMINE) {
            return CommonResult.failed("物品已被申领");
        }
        lostItem.setStatus(LostItemService.APPLYING);
        lostItemService.updateByPrimaryKeySelective(lostItem);
        ApplyLostMsg msg = new ApplyLostMsg();
        msg.setLostItemId(lostItem.getId());
        msg.setRegionId(lostItem.getRegionId());
        msg.setWxUserId(wxUser.getId());
        msg.setApplyTime(new Date());
        applyLostMsgService.insertApplyMsg(msg);

        return CommonResult.success(null, "申请成功");
    }

    @RequestMapping(value = "/getMsg", method = RequestMethod.GET)
    public CommonResult getMsg(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        Map<String, List> res = wxUserService.getWxUserMsg(wxUser);
        return CommonResult.success(res);
    }

    @GetMapping(value = "/getMsgNum")
    public CommonResult getMsgNum(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        int num = wxUserService.getWxUserMsgNum(wxUser);
        Map<String, Integer> res = new HashMap();
        res.put("msgNum", num);
        return CommonResult.success(res);
    }

    @GetMapping(value = "/getCheckedApplyLostItem")
    public CommonResult getCheckedApplyLostItem(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        List<ApplyLostMsgDto> dtos = wxUserService.getCheckedApplyLost(wxUser);
        return CommonResult.success(dtos);
    }

    @GetMapping("/getSubmitRecords")
    public CommonResult getSubmitRecords(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);

        List<LostItemDto> determine = lostItemService.getMySubmitLost(wxUser, LostItemService.DETERMINE);
        ArrayList<MySubmitDto> res = new ArrayList<>();
        for (LostItemDto dto : determine) {
            MySubmitDto mySubmitDto = new MySubmitDto();
            mySubmitDto.setArticleDetail(dto);
            mySubmitDto.setHandleStatus(HandleStatus.success.code);
            res.add(mySubmitDto);
        }
        List<LostItemDto> OUTDATE = lostItemService.getMySubmitLost(wxUser, LostItemService.OUTDATE);
        for (LostItemDto dto : OUTDATE) {
            MySubmitDto mySubmitDto = new MySubmitDto();
            mySubmitDto.setArticleDetail(dto);
            mySubmitDto.setHandleStatus(HandleStatus.outDate.code);
            res.add(mySubmitDto);
        }
        return CommonResult.success(res);
    }

    @GetMapping("/confirmLostItem")
    public CommonResult confirmLostItem(int articleId, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        WxUser wxUser = wxUserService.getWxUserByOpenId(username);
        LostItem lostItem = lostItemService.getLostItemById(articleId);
        lostItem.setStatus(LostItemService.DETERMINE);
        lostItemService.updateByPrimaryKeySelective(lostItem);
        return CommonResult.success("", "已确认");
    }

    @GetMapping("/acceptApplyLostItem")
    public CommonResult acceptApplyLostItem(@RequestParam("applyRecordId") int applyRecordId) {
        ApplyLostMsg msg = applyLostMsgService.selectByPrimaryKey(applyRecordId);
        msg.setStatus(ApplyLostMsgService.SUCCESS);
        applyLostMsgService.updateApplyLostMsg(msg);
        return CommonResult.success("", "操作成功");
    }

    @GetMapping("/refuseApplyLostItem")
    public CommonResult refuseApplyLostItem(@RequestParam("applyRecordId") int applyRecordId) {
        ApplyLostMsg msg = applyLostMsgService.selectByPrimaryKey(applyRecordId);
        msg.setStatus(ApplyLostMsgService.FAILED);
        applyLostMsgService.updateApplyLostMsg(msg);
        return CommonResult.success("", "操作成功");
    }

    @GetMapping("/acceptApplyManager")
    public CommonResult acceptApplyManager(@RequestParam("id") int applyRecordId) {
        ApplyManagerMsg msg = applyManagerMsgService.selectByPrimaryKey(applyRecordId);
        msg.setStatus(ApplyManagerMsgService.SUCCESS);
        Region region = regionService.getRegionByPrimaryKey(msg.getRegionId());
        regionService.addRegionManager(region.getId(),msg.getWxUserId());
        applyManagerMsgService.updateApplyManagerMsg(msg);
        return CommonResult.success("", "操作成功");
    }

    @GetMapping("/refuseApplyManager")
    public CommonResult refuseApplyManager(@RequestParam("id") int applyRecordId) {
        ApplyManagerMsg msg = applyManagerMsgService.selectByPrimaryKey(applyRecordId);
        msg.setStatus(ApplyManagerMsgService.FAILED);
        applyManagerMsgService.updateApplyManagerMsg(msg);
        return CommonResult.success("", "操作成功");
    }

}
