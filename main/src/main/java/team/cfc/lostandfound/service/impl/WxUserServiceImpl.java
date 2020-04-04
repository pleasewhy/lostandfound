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
import team.cfc.lostandfound.bo.WxUserDetails;
import team.cfc.lostandfound.dao.WxUserDao;
import team.cfc.lostandfound.model.WxUser;
import team.cfc.lostandfound.model.WxUserExample;
import team.cfc.lostandfound.security.util.JwtTokenUtil;
import team.cfc.lostandfound.service.WxUserService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxUserServiceImpl implements WxUserService {
    private Logger logger = LoggerFactory.getLogger(WxUserService.class);

    public static final String WX_APP_ID = "wxc94daa26697fc479";
    public static final String WX_APP_KEY = "29b2e7ff5cbde050793e7201b61101ef";
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Value("${jwt.tokenHead}")
    String tokenHead;

    @Autowired
    private WxUserDao wxUserDao;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public Map<String, Object> login(String code) {
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append(WX_LOGIN_URL).append("?appid=")
                .append(WX_APP_ID).append("&secret=")
                .append(WX_APP_KEY).append("&js_code=").append(code)
                .append("&grant_type=authorization_code");
        String body = getBody(loginUrl.toString());
        Map<String, Object> responseMap = JSONUtil.parseObj(body);
        if ((Integer) responseMap.getOrDefault("errcode", 0) == 0) {
            WxUser wxUser = insertWxUser((String) responseMap.get("open_id"), (String) responseMap.get("session_key"));
            Map<String,Object> tokenMap = new HashMap<>();
            String token = jwtTokenUtil.generateToken(new WxUserDetails(wxUser));
            tokenMap.put("token", tokenHead+" "+token);
            return tokenMap;
        }
        return responseMap;
    }

    @Override
    public UserDetails loadUserByOpenId(String username) {
        return null;
    }

    public WxUser insertWxUser(String openId, String sessionKey) {
        WxUser wxUser = new WxUser();
        wxUser.setOpenId(openId);
        wxUser.setSessionKey(sessionKey);
        DateTime now = new DateTime();
        wxUser.setCreateTime(now);
        wxUser.setModifyTime(now);
        WxUserExample example = new WxUserExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<WxUser> wxUsers = wxUserDao.selectByExample(example);
        if (wxUsers.size() < 1) {
            wxUserDao.insertSelective(wxUser);
            return wxUser;
        }
        return wxUsers.get(0);
    }

    public String getBody(String url) {
        String body = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            logger.info("create httppost:" + url);
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
        logger.info("response status: " + response.getStatusLine());
        Charset charset = ContentType.getOrDefault(entity).getCharset();
        if (charset != null) {
            logger.info(charset.name());
        }

        String body = null;
        try {
            body = EntityUtils.toString(entity, "utf-8");
            logger.info("body " + body);
        } catch (IOException e) {
            logger.warn("{}: cannot parse the entity", e.getMessage());
        }
        return body;
    }

}
