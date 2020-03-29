package team.cfc.lostandfound.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cfc.lostandfound.dao.LostItemDao;
import team.cfc.lostandfound.dao.PickerDao;
import team.cfc.lostandfound.dto.LostItemDto;
import team.cfc.lostandfound.model.LostItem;
import team.cfc.lostandfound.model.LostItemExample;
import team.cfc.lostandfound.model.Picker;
import team.cfc.lostandfound.service.LostItemService;

import java.util.Date;
import java.util.List;


@Service
public class LostItemServiceImpl implements LostItemService {
    @Autowired
    PickerDao pickerDao;

    @Autowired
    LostItemDao lostItemDao;

    @Override
    public int create(LostItemDto lostItemDto) {
        Picker picker = lostItemDto.getPicker();
        LostItem lostItem = new LostItem();
        BeanUtil.copyProperties(lostItemDto, lostItem, "picker");
        if (picker == null) {
            lostItem.setPickerId(0);
        } else {
            Picker p = pickerDao.selectByOpenId(picker.getOpenId());
            if (p == null) {
                pickerDao.insertSelective(picker);
                p = pickerDao.selectByOpenId(picker.getOpenId());
            }
            lostItem.setId(p.getId());
        }

        if (lostItem.getPickDate() == null) {
            lostItem.setPickDate(new Date());
        }
        lostItem.setStatus(0);
        return lostItemDao.insertSelective(lostItem);
    }

    @Override
    public List<LostItem> listLostItem(int pageNum, int pageSize, int status) {
        PageHelper.startPage(pageNum, pageSize);
        LostItemExample lostItemExample = new LostItemExample();
        lostItemExample.setOrderByClause("pick_date");
        lostItemExample.createCriteria().andStatusEqualTo(status);
        return lostItemDao.selectByExample(lostItemExample);
    }

    @Override
    public List<LostItem> search(String keywords, int pageNum, int pageSize, int status, Date startDate, Date endDate) {
        PageHelper.startPage(pageNum, pageSize);
        LostItemExample lostItemExample = new LostItemExample();
        LostItemExample.Criteria criteria = lostItemExample.createCriteria();
        criteria.andStatusEqualTo(status);
        if (keywords != null) {
            criteria.andNameLike("%" + keywords + "%");
        }
        if (startDate != null) {
            criteria.andPickDateGreaterThanOrEqualTo(startDate);
        }
        if (endDate != null) {
            criteria.andPickDateLessThanOrEqualTo(endDate);
        }
        return lostItemDao.selectByExample(lostItemExample);
    }
}
