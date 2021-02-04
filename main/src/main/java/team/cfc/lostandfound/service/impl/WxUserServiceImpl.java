package team.cfc.lostandfound.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import team.cfc.lostandfound.bo.WxUserDetails;
import team.cfc.lostandfound.dao.*;
import team.cfc.lostandfound.dto.ApplyLostMsgDto;
import team.cfc.lostandfound.dto.LostItemDto;
import team.cfc.lostandfound.dto.WxUserDto;
import team.cfc.lostandfound.model.*;
import team.cfc.lostandfound.security.util.JwtTokenUtil;
import team.cfc.lostandfound.service.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxUserServiceImpl implements WxUserService {
    private Logger logger = LoggerFactory.getLogger(WxUserService.class);

    public static final String WX_APP_ID = "xxx";
    public static final String WX_APP_KEY = "xxxx";
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Value("${jwt.tokenHead}")
    String tokenHead;

    @Autowired
    private WxUserDao wxUserDao;

    @Autowired
    ApplyManagerMsgDao applyManagerMsgDao;

    @Autowired
    ApplyRegionMsgDao applyRegionMsgDao;

    @Autowired
    RegionManagerRelationDao managerRelationDao;

    @Autowired
    RegionCreateRelationDao createRelationDao;

    @Autowired
    RegionDao regionDao;

    @Autowired
    LostItemDao lostItemDao;

    @Autowired
    WxUserInfoDao wxUserInfoDao;

    @Autowired
    RegionService regionService;

    @Autowired
    LostItemService lostItemService;

    @Autowired
    ApplyLostMsgService applyLostMsgService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    ApplyManagerMsgService applyManagerMsgService;


    @Override
    public WxUser login(String code) throws Exception {
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append(WX_LOGIN_URL).append("?appid=")
                .append(WX_APP_ID).append("&secret=")
                .append(WX_APP_KEY).append("&js_code=").append(code)
                .append("&grant_type=authorization_code");
        String body = getBody(loginUrl.toString());
        Map<String, Object> responseMap = JSONUtil.parseObj(body);
        if ((Integer) responseMap.getOrDefault("errcode", 0) == 0) {
            String openId = (String) responseMap.get("openid");
            String sessionKey = (String) responseMap.get("session_key");
            WxUser wxUser = insertOrUpdateWxUser(openId, sessionKey);
            return wxUser;
        }
        throw new Exception(body);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        WxUser wxUser = getWxUserByOpenId(username);
        if (wxUser != null) {
            return new WxUserDetails(wxUser);
        }
        return null;
    }

    @Override
    public WxUser getWxUserByPrimaryKey(int id) {
        return wxUserDao.selectByPrimaryKey(id);
    }

    @Override
    public WxUser getWxUserByOpenId(String openId) {
        WxUserExample example = new WxUserExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<WxUser> wxUsers = wxUserDao.selectByExample(example);
        if (wxUsers != null && wxUsers.size() > 0) {
            return wxUsers.get(0);
        }
        return null;
    }

    @Override
    public WxUserDto getWxUserDto(WxUser wxUser) {
        WxUserDto wxUserDto = new WxUserDto();
        wxUserDto.setId(wxUser.getId());
        WxUserInfoExample example = new WxUserInfoExample();
        example.createCriteria().andWxUserIdEqualTo(wxUser.getId());
        List<WxUserInfo> infos = wxUserInfoDao.selectByExample(example);
        if (CollectionUtils.isEmpty(infos)) {
            return wxUserDto;
        }
        wxUserDto.setAvatarUrl(infos.get(0).getAvatarUrl());
        wxUserDto.setNickName(infos.get(0).getNickName());
        return wxUserDto;
    }


    @Override
    public List<Region> getManageRegion(WxUser wxUser) {
        int wxUserId = wxUser.getId();
        RegionManagerRelationExample example = new RegionManagerRelationExample();
        example.createCriteria().andWxUserIdEqualTo(wxUserId);
        List<RegionManagerRelation> relations = managerRelationDao.selectByExample(example);
        List<Region> regions = new ArrayList<>();
        for (RegionManagerRelation r : relations) {
            Region region = regionService.getRegionByPrimaryKey(r.getRegionId());
            regions.add(region);
        }
        return regions;
    }

    @Override
    public Region getCreateRegion(WxUser wxUser) {
        int wxUserId = wxUser.getId();
        RegionCreateRelationExample example = new RegionCreateRelationExample();
        example.createCriteria().andWxUserIdEqualTo(wxUserId);
        List<RegionCreateRelation> relations = createRelationDao.selectByExample(example);
        if (relations == null || relations.size() == 0) {
            return null;
        }
        return regionService.getRegionByPrimaryKey(relations.get(0).getRegionId());
    }

    @Override
    public List<LostItem> getPickLost(WxUser wxUser) {
        LostItemExample example = new LostItemExample();
        example.createCriteria().andPickerIdEqualTo(wxUser.getId());
        return lostItemDao.selectByExample(example);
    }

    @Override
    public Region getSelectRegion(WxUser wxUser) throws Exception {
        Integer regionId = wxUser.getSelectRegionId();
        if (regionId == null) {
            throw new Exception(wxUser.getOpenId() + "未选择区域");
        }
        return regionDao.selectByPrimaryKey(regionId);
    }

    @Override
    public void setSelectRegion(WxUser wxUser, int regionId) throws Exception {
        Region result = regionDao.selectByPrimaryKey(regionId);
        if (result == null || result.getStatus() == 1) {
            throw new Exception("该区域未创建");
        }
        wxUser.setSelectRegionId(regionId);
        wxUserDao.updateByPrimaryKeySelective(wxUser);
    }

    public WxUser insertOrUpdateWxUser(String openId, String sessionKey) {
        WxUser wxUser = new WxUser();
        wxUser.setOpenId(openId);
        wxUser.setSessionKey(sessionKey);
        DateTime now = new DateTime();
        wxUser.setModifyTime(now);
        WxUserExample example = new WxUserExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<WxUser> wxUsers = wxUserDao.selectByExample(example);
        if (wxUsers.size() < 1) {
            wxUser.setCreateTime(now);
            wxUserDao.insertSelective(wxUser);
        } else {
            wxUserDao.updateByExampleSelective(wxUser, example);
        }
        return wxUser;
    }

    @Override
    public List<Region> getMyAllRegion(WxUser wxUser) {
        List<Region> regionList = getManageRegion(wxUser);
        Region region = getCreateRegion(wxUser);
        if (region != null) {
            regionList.add(region);
        }
        return regionList;
    }

    @Override
    public WxUserInfo getWxUserInfo(WxUser wxUser) {
        WxUserInfoExample example = new WxUserInfoExample();
        example.createCriteria().andWxUserIdEqualTo(wxUser.getId());
        List<WxUserInfo> wxUserInfos = wxUserInfoDao.selectByExample(example);
        if (!CollectionUtils.isEmpty(wxUserInfos)) {
            return wxUserInfos.get(0);
        }
        return new WxUserInfo();
    }


    @Override
    public int getWxUserMsgNum(WxUser wxUser) {
        Map<String, List> map = getWxUserMsg(wxUser);
        int res = 0;
        for (Map.Entry<String, List> entry : map.entrySet()
        ) {
            if (CollectionUtils.isEmpty(entry.getValue())
                    || "mySubmitLost".equals(entry.getKey())
                    || "myApplyLost".equals(entry.getKey())) {
                continue;
            }
            res += entry.getValue().size();
        }
        return res;
    }



    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd hh:mm");
    @Override
//    @Cacheable(value = "wxUserMsg", key = "#wxUser.id")
    public Map<String, List> getWxUserMsg(WxUser wxUser) {
        Map<String, List> res = new HashMap<>();
        res.put("mySubmitLost", lostItemService.getMySubmitLost(wxUser, LostItemService.CHECKING));  // 我的提交
        List<ApplyLostMsgDto> dtos = applyLostMsgService.getMyApplyLost(wxUser, ApplyLostMsgService.CHECKING);
        List<LostItemDto> lostItemDtos = new ArrayList<>();
        for (ApplyLostMsgDto dto : dtos) {
            lostItemDtos.add(dto.getArticleDetail());
        }
        res.put("myApplyLost", lostItemDtos);  // 我的申请
        res.put("managerApply", applyManagerMsgService.getApplyManagerMsg(wxUser));  // 我创建区域的管理员申请

        List<ApplyLostMsgDto> lostItemApplyDtos = new ArrayList<>();
        List<ApplyLostMsg> msgs = applyLostMsgService.getApplyLostInMyRegion(wxUser);
        List<ApplyLostMsg> applyLostMyLostItem = applyLostMsgService.getApplyLostMyLostItem(wxUser);
        msgs.addAll(applyLostMyLostItem);
        for (ApplyLostMsg msg : msgs) {
            ApplyLostMsgDto applyLostMsgDto = new ApplyLostMsgDto();
            applyLostMsgDto.setApplyRecordId(msg.getId());
            applyLostMsgDto.setApplyTime(simpleDateFormat.format(msg.getApplyTime()));

            int lostItemId = msg.getLostItemId();
            applyLostMsgDto.setArticleDetail(lostItemService.convert(lostItemService.getLostItemById(lostItemId)));
            lostItemApplyDtos.add(applyLostMsgDto);
        }
        res.put("lostItemApply", lostItemApplyDtos);  // 我管理区域的丢失物品申请
        res.put("checkLostItem", lostItemService.getNotCheckLostInMyRegion(wxUser));  // 审核丢失物品
        return res;
    }

    @Override
    public List<ApplyLostMsgDto> getCheckedApplyLost(WxUser wxUser) {
        List<ApplyLostMsgDto> success = applyLostMsgService.getMyApplyLost(wxUser, ApplyLostMsgService.SUCCESS);
        List<ApplyLostMsgDto> failed = applyLostMsgService.getMyApplyLost(wxUser, ApplyLostMsgService.FAILED);
        List<ApplyLostMsgDto> outDate = applyLostMsgService.getMyApplyLost(wxUser, ApplyLostMsgService.OUTDATE);
        success.addAll(failed);
        success.addAll(outDate);
        for (ApplyLostMsgDto dto : success) {
            dto.setHandleStatus(dto.getStatusCode() - 1);
        }
        return success;
    }

    public String getBody(String url) {
        String body = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet get = new HttpGet(url);
            get.addHeader("Accept-Charset", "utf-8");
            HttpResponse response = sendRequest(httpClient, get);
            body = parseResponse(response);
        } catch (IOException e) {
            logger.error("send post request failed: {}", e.getMessage());
        }
        return body;
    }

    private HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost)
            throws ClientProtocolException, IOException {
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        return response;
    }

    private String parseResponse(HttpResponse response) {
        logger.info("get response from http server..");
        HttpEntity entity = response.getEntity();
        Charset charset = ContentType.getOrDefault(entity).getCharset();
        if (charset != null) {
            logger.info(charset.name());
        }

        String body = null;
        try {
            body = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            logger.warn("{}: cannot parse the entity", e.getMessage());
        }
        return body;
    }

}
