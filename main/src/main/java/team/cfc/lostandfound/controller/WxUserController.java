package team.cfc.lostandfound.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.dto.WxAuthDto;
import team.cfc.lostandfound.security.util.JwtTokenUtil;
import team.cfc.lostandfound.service.RegionService;
import team.cfc.lostandfound.service.WxUserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

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

    @Value("${jwt.tokenHead}")
    String tokenHead;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public CommonResult login(String code) {
//        Map<String, Object> map = wxUserService.login(code);
        Map<String, Object> map = new HashMap<>();
        String token = jwtTokenUtil.generateToken(wxUserService.loadUserByUsername("abcd"));
        map.put("token", tokenHead + " " + token);
        return CommonResult.success(map);
    }

    @RequestMapping(value = "/createRegion", method = RequestMethod.GET)
    public CommonResult createRegion(@RequestParam("regionId") int regionId, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return regionService.createRegion(regionId, username);
    }
    @RequestMapping(value = "/applyRegionManager")
    public CommonResult applyRegionManager(@RequestParam("regionId") int regionId, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        regionService.applyRegionManager(regionId, username);
    }
}
