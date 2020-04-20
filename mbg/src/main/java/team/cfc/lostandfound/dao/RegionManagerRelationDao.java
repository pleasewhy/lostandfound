package team.cfc.lostandfound.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.cfc.lostandfound.model.RegionManagerRelation;
import team.cfc.lostandfound.model.RegionManagerRelationExample;

public interface RegionManagerRelationDao {
    long countByExample(RegionManagerRelationExample example);

    int deleteByExample(RegionManagerRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RegionManagerRelation record);

    int insertSelective(RegionManagerRelation record);

    List<RegionManagerRelation> selectByExample(RegionManagerRelationExample example);

    RegionManagerRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RegionManagerRelation record, @Param("example") RegionManagerRelationExample example);

    int updateByExample(@Param("record") RegionManagerRelation record, @Param("example") RegionManagerRelationExample example);

    int updateByPrimaryKeySelective(RegionManagerRelation record);

    int updateByPrimaryKey(RegionManagerRelation record);
}