package team.cfc.lostandfound.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.cfc.lostandfound.model.ApplyLostMsg;
import team.cfc.lostandfound.model.ApplyLostMsgExample;

public interface ApplyLostMsgDao {
    long countByExample(ApplyLostMsgExample example);

    int deleteByExample(ApplyLostMsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApplyLostMsg record);

    int insertSelective(ApplyLostMsg record);

    List<ApplyLostMsg> selectByExample(ApplyLostMsgExample example);

    ApplyLostMsg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApplyLostMsg record, @Param("example") ApplyLostMsgExample example);

    int updateByExample(@Param("record") ApplyLostMsg record, @Param("example") ApplyLostMsgExample example);

    int updateByPrimaryKeySelective(ApplyLostMsg record);

    int updateByPrimaryKey(ApplyLostMsg record);
}