package team.cfc.lostandfound.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.cfc.lostandfound.model.LostItem;
import team.cfc.lostandfound.model.LostItemExample;

public interface LostItemDao {
    long countByExample(LostItemExample example);

    int deleteByExample(LostItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LostItem record);

    int insertSelective(LostItem record);

    List<LostItem> selectByExample(LostItemExample example);

    LostItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LostItem record, @Param("example") LostItemExample example);

    int updateByExample(@Param("record") LostItem record, @Param("example") LostItemExample example);

    int updateByPrimaryKeySelective(LostItem record);

    int updateByPrimaryKey(LostItem record);

}