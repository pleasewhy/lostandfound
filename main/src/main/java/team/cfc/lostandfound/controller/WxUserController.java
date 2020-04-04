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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.cfc.lostandfound.common.api.CommonResult;
import team.cfc.lostandfound.dto.WxAuthDto;
import team.cfc.lostandfound.service.WxUserService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

@RestController
@RequestMapping("/wxUser")
public class WxUserController {
    private static final Logger logger = LoggerFactory.getLogger(WxUserController.class);

    @Autowired
    WxUserService wxUserService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public CommonResult login(String code) {
        Map<String,Object> map = wxUserService.login(code);
        return CommonResult.success(map);
    }

}
