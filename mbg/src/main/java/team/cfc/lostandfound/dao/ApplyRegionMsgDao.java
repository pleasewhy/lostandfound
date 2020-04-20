package team.cfc.lostandfound.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.cfc.lostandfound.model.ApplyRegionMsg;
import team.cfc.lostandfound.model.ApplyRegionMsgExample;

public interface ApplyRegionMsgDao {
    long countByExample(ApplyRegionMsgExample example);

    int deleteByExample(ApplyRegionMsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApplyRegionMsg record);

    int insertSelective(ApplyRegionMsg record);

    List<ApplyRegionMsg> selectByExample(ApplyRegionMsgExample example);

    ApplyRegionMsg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApplyRegionMsg record, @Param("example") ApplyRegionMsgExample example);

    int updateByExample(@Param("record") ApplyRegionMsg record, @Param("example") ApplyRegionMsgExample example);

    int updateByPrimaryKeySelective(ApplyRegionMsg record);

    int updateByPrimaryKey(ApplyRegionMsg record);
}