package team.cfc.lostandfound.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.cfc.lostandfound.model.ApplyManagerMsg;
import team.cfc.lostandfound.model.ApplyManagerMsgExample;

public interface ApplyManagerMsgDao {
    long countByExample(ApplyManagerMsgExample example);

    int deleteByExample(ApplyManagerMsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApplyManagerMsg record);

    int insertSelective(ApplyManagerMsg record);

    List<ApplyManagerMsg> selectByExample(ApplyManagerMsgExample example);

    ApplyManagerMsg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApplyManagerMsg record, @Param("example") ApplyManagerMsgExample example);

    int updateByExample(@Param("record") ApplyManagerMsg record, @Param("example") ApplyManagerMsgExample example);

    int updateByPrimaryKeySelective(ApplyManagerMsg record);

    int updateByPrimaryKey(ApplyManagerMsg record);
}