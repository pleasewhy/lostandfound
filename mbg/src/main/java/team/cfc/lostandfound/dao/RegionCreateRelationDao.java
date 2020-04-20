package team.cfc.lostandfound.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.cfc.lostandfound.model.RegionCreateRelation;
import team.cfc.lostandfound.model.RegionCreateRelationExample;

public interface RegionCreateRelationDao {
    long countByExample(RegionCreateRelationExample example);

    int deleteByExample(RegionCreateRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RegionCreateRelation record);

    int insertSelective(RegionCreateRelation record);

    List<RegionCreateRelation> selectByExample(RegionCreateRelationExample example);

    RegionCreateRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RegionCreateRelation record, @Param("example") RegionCreateRelationExample example);

    int updateByExample(@Param("record") RegionCreateRelation record, @Param("example") RegionCreateRelationExample example);

    int updateByPrimaryKeySelective(RegionCreateRelation record);

    int updateByPrimaryKey(RegionCreateRelation record);
}