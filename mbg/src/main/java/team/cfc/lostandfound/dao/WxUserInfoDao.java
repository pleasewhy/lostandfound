package team.cfc.lostandfound.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.cfc.lostandfound.model.WxUserInfo;
import team.cfc.lostandfound.model.WxUserInfoExample;

public interface WxUserInfoDao {
    long countByExample(WxUserInfoExample example);

    int deleteByExample(WxUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxUserInfo record);

    int insertSelective(WxUserInfo record);

    List<WxUserInfo> selectByExample(WxUserInfoExample example);

    WxUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxUserInfo record, @Param("example") WxUserInfoExample example);

    int updateByExample(@Param("record") WxUserInfo record, @Param("example") WxUserInfoExample example);

    int updateByPrimaryKeySelective(WxUserInfo record);

    int updateByPrimaryKey(WxUserInfo record);
}