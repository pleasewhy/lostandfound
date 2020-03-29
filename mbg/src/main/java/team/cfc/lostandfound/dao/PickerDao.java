package team.cfc.lostandfound.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.cfc.lostandfound.model.LostItem;
import team.cfc.lostandfound.model.Picker;
import team.cfc.lostandfound.model.PickerExample;

public interface PickerDao {
    long countByExample(PickerExample example);

    int deleteByExample(PickerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Picker record);

    int insertSelective(Picker record);

    List<Picker> selectByExample(PickerExample example);

    Picker selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Picker record, @Param("example") PickerExample example);

    int updateByExample(@Param("record") Picker record, @Param("example") PickerExample example);

    int updateByPrimaryKeySelective(Picker record);

    int updateByPrimaryKey(Picker record);

    Picker selectByOpenId(String openId);
}