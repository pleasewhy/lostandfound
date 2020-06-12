package team.cfc.lostandfound.service.impl;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import team.cfc.lostandfound.dao.LostItemDao;
import team.cfc.lostandfound.dao.WxUserInfoDao;
import team.cfc.lostandfound.dto.LostItemDto;
import team.cfc.lostandfound.model.*;
import team.cfc.lostandfound.security.config.IgnoreUrls;
import team.cfc.lostandfound.service.LostItemService;
import team.cfc.lostandfound.service.WxUserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author hy
 */
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "list")
@Data
@Service
public class LostItemServiceImpl implements LostItemService {

    @Autowired
    LostItemDao lostItemDao;

    @Autowired
    WxUserInfoDao infoDao;

    @Autowired
    WxUserInfoDao wxUserInfoDao;

    @Autowired
    WxUserService wxUserService;

    @Autowired
    IgnoreUrls ignoreUrls;


    private List<String> timeOfDay = new ArrayList<>();

    public static final String DATE_FORMATER = "yyyy/MM/dd";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(LostItem lostItem, WxUserInfo userInfo) {
        lostItem.setSubmitTime(new DateTime());
        if (lostItem.getRecoverMethod() == 1) {
            lostItem.setStatus(LostItemService.DETERMINE);
        } else {
            lostItem.setStatus(LostItemService.CHECKING);
        }
        String pickTime = lostItem.getPickTime();
        lostItem.setPickTime(pickTimeConvert(pickTime));
        WxUserInfoExample example = new WxUserInfoExample();
        example.createCriteria().andWxUserIdEqualTo(userInfo.getWxUserId());
        long count = wxUserInfoDao.countByExample(example);
        if (count < 1) {
            wxUserInfoDao.insertSelective(userInfo);
        } else {
            wxUserInfoDao.updateByExampleSelective(userInfo, example);
        }
        return lostItemDao.insertSelective(lostItem);
    }

    @Override
    public String pickTimeConvert(String pickTime) {
        String month = pickTime.substring(0, pickTime.indexOf('月'));
        String day = pickTime.substring(pickTime.indexOf('月') + 1, pickTime.indexOf('日'));
        String pieceTime = pickTime.substring(pickTime.indexOf('-') + 1);
        month = month.length() < 2 ? "0" + month : month;
        day = day.length() < 2 ? "0" + day : day;
        DateTime now = new DateTime();
        String date = now.year() + "/" + month + "/" + day;
        DateTime dateTime = new DateTime(date, DATE_FORMATER);
        if (dateTime.compareTo(now) > 0) {
            dateTime.offset(DateField.YEAR, -1);
        }
        int schoolTime = timeOfDay.indexOf(pieceTime);
        return dateTime.toDateStr() + " " + schoolTime;
    }

    public String convertPickTime(String time) {
        int timePieceIndex = time.charAt(time.length() - 1) - '0';
        String timePiece = timeOfDay.get(timePieceIndex);
        String date = time.substring(5, 10);
        date = Integer.parseInt(date.substring(0, 2)) + "月" + Integer.parseInt(date.substring(3, 5));
        date = date + "日";
        return date + timePiece;
    }

    @Override
    public List<LostItem> listLostItem(int pageNum, int pageSize, int status, int regionId, String label) {
        PageHelper.startPage(pageNum, pageSize);
        LostItemExample lostItemExample = new LostItemExample();
        long now = System.currentTimeMillis();
        long twoWeekAgo = now - 1000 * 60 * 60 * 24 * 14;
        DateTime dateTime = new DateTime(twoWeekAgo);
        LostItemExample.Criteria criteria = lostItemExample.createCriteria();
        criteria.andSubmitTimeGreaterThanOrEqualTo(dateTime)
                .andStatusEqualTo(status).andRegionIdEqualTo(regionId);
        if (label != null) {
            criteria.andLabelEqualTo(label);
        }
        lostItemExample.setOrderByClause("submit_time");
        return lostItemDao.selectByExample(lostItemExample);
    }


    @Override
    public List<LostItem> search(String keywords, int pageNum, int pageSize, int status,
                                 int regionId, String label, List<String> timeInterval) {
        PageHelper.startPage(pageNum, pageSize);
        LostItemExample lostItemExample = new LostItemExample();
        LostItemExample.Criteria criteria = lostItemExample.createCriteria();
        if (status != -1) {
            criteria.andStatusEqualTo(status);
        }
        if (regionId != -1) {
            criteria.andRegionIdEqualTo(regionId);
        }
        if (!StringUtils.isEmpty(keywords)) {
            criteria.andDescriptionLike("%" + keywords + "%");
        }
        if (!StringUtils.isEmpty(label) && !label.equals("全部")) {
            criteria.andLabelEqualTo(label);
        }
        if (timeInterval != null && timeInterval.size() > 0) {
            String startTime = timeInterval.get(0);
            String endTime = timeInterval.get(1);
            if (startTime != null) {
                criteria.andPickTimeGreaterThanOrEqualTo(startTime);
            }
            if (endTime != null) {
                criteria.andPickTimeLessThan(endTime);
            }
        }
        return lostItemDao.selectByExample(lostItemExample);
    }

    @Override
    public List<String> getTimeInterval(String searchTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 0");
        DateTime today = new DateTime();
        long todayTimeStamp = today.getTime();
        long day = 60 * 60 * 24 * 1000;
        List<String> res = new ArrayList<>();
        if ("全部".equals(searchTime)) {
            return null;
        } else if ("今天".equals(searchTime)) {
            DateTime endTime = new DateTime(todayTimeStamp + day);
            res.add(sdf.format(today));
            res.add(sdf.format(endTime));
        } else if ("昨天".equals(searchTime)) {
            DateTime startTime = new DateTime(todayTimeStamp - day);
            res.add(sdf.format(startTime));
            res.add(sdf.format(today));
        } else if ("七天内".equals(searchTime)) {
            DateTime startTime = new DateTime(todayTimeStamp - 6 * day);
            DateTime endTime = new DateTime(todayTimeStamp + day);
            res.add(sdf.format(startTime));
            res.add(sdf.format(endTime));
        } else if ("七天前".equals(searchTime)) {
            DateTime endTime = new DateTime(todayTimeStamp - 6 * day);
            res.add(null);
            res.add(sdf.format(endTime));
        }
        return res;
    }

    String submitTimeConvert(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd hh:mm");
        String s = sdf.format(date);
        String s1 = Integer.parseInt(s.substring(0, 2)) + "-" + Integer.parseInt(s.substring(3, 5));
        s1 += " " + Integer.parseInt(s.substring(6, 8)) + ":" + Integer.parseInt(s.substring(9, 11));
        return s1;
    }

    @Override
    public List<LostItemDto> convert(List<LostItem> lostItems) {
        List<LostItemDto> lostItemDtos = new ArrayList<>();
        for (LostItem l : lostItems) {
            lostItemDtos.add(convert(l));
        }
        return lostItemDtos;
    }

    @Override
    public LostItemDto convert(LostItem lostItem) {
        LostItemDto lostItemDto = new LostItemDto();
        BeanUtils.copyProperties(lostItem, lostItemDto);
        lostItemDto.setArticleId(lostItem.getId());
        Date submitTime = lostItem.getSubmitTime();
        String submitTimeStr = submitTimeConvert(submitTime);
        lostItemDto.setSubmitTime(submitTimeStr);
        String pickTime = lostItemDto.getPickTime();
        lostItemDto.setPickTime(convertPickTime(pickTime));

        WxUserInfoExample example = new WxUserInfoExample();
        example.createCriteria().andWxUserIdEqualTo(lostItemDto.getPickerId());
        List<WxUserInfo> infos = infoDao.selectByExample(example);
        if (infos.size() < 1) {
            throw new RuntimeException();
        }
        BeanUtils.copyProperties(infos.get(0), lostItemDto);
        return lostItemDto;
    }

    @Override
    public LostItem getLostItemById(int id) {
        return lostItemDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(LostItem record) {
        return lostItemDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<LostItemDto> getMySubmitLost(WxUser wxUser, int status) {
        LostItemExample example = new LostItemExample();
        example.createCriteria().andPickerIdEqualTo(wxUser.getId()).andStatusEqualTo(status);
        List<LostItem> lostItems = lostItemDao.selectByExample(example);
        return convert(lostItems);
    }

    // TODO 测试
    List<LostItem> getNotCheckLostInRegion(Region region) {
        LostItemExample example = new LostItemExample();
        example.createCriteria().andRegionIdEqualTo(region.getId()).andStatusEqualTo(0);
        return lostItemDao.selectByExample(example);
    }

    @Override
    public List<LostItemDto> getNotCheckLostInMyRegion(WxUser wxUser) {
        List<Region> myRegions = wxUserService.getMyAllRegion(wxUser);
        List<LostItem> res = new ArrayList<>();
        for (Region region : myRegions) {
            List<LostItem> lostItemList = getNotCheckLostInRegion(region);
            if (!CollectionUtils.isEmpty(lostItemList)) {
                res.addAll(lostItemList);
            }
        }
        return convert(res);
    }

    @Override
    public List<LostItem> getAllLostItem() {
        LostItemExample example = new LostItemExample();
        return lostItemDao.selectByExample(example);
    }
}
