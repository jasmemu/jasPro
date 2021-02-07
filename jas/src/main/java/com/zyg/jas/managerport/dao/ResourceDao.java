package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Resources;

import java.util.List;
import java.util.Map;

public interface ResourceDao {
    Integer insertResource(Resources resources);

    List<Resources> selectResources(String comId);

    Integer selectCountByCmtId(String comId);

    Integer deleteById(Integer id);

    List<Resources> selectForSearch(Map paramMap);

    Resources selectResourceById(Integer id);
}
