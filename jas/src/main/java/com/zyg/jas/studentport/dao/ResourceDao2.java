package com.zyg.jas.studentport.dao;

import com.zyg.jas.common.pojo.Resources;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResourceDao2 {
    Integer insertResource(Resources resources);

    List<Resources> selectResources(String comId);

    Integer selectCountByCmtId(String comId);

    Integer deleteById(Integer id);

    List<Resources> selectForSearch(Map paramMap);

    Resources selectResourceById(Integer id);

    Integer deleteByBatch(@Param("resourcesList") List<Resources> resources);
//    学生端使用
    List<Resources> getResourceWithoutId();
}
