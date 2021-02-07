package com.zyg.jas.managerport.service;

import com.zyg.jas.common.pojo.Resources;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ResourceService {

    Integer saveResource(MultipartFile multipartFile,String resourceType,String comId) throws Exception;

    List<Resources> getResource(String comId,Integer pageNo,Integer pageSize);

    Integer getCountByComId(String comId);

    Integer removeById(Integer id);

    List<Resources> getForSearch(String comId,String resourceName);

    Resources getResourceById(Integer id);
}
