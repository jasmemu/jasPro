package com.zyg.jas.managerport.service.impl;

import com.github.pagehelper.PageHelper;
import com.zyg.jas.common.pojo.Resources;
import com.zyg.jas.common.tool.util.MultipartFileToFile;
import com.zyg.jas.managerport.dao.ResourceDao;
import com.zyg.jas.managerport.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Value("${resourcePath}")
    private String resourcePath;

    @Value("${resourceURL}")
    private String resourceUrl;

    @Autowired
   private ResourceDao resourceDao;




    @Override
    public Integer saveResource(MultipartFile multipartFile, String resourceType,String comId) throws Exception {
        if (multipartFile.isEmpty()) {
            return 0;
        }
        String fileName = multipartFile.getOriginalFilename();
        int i = fileName.lastIndexOf(".");
        String suffix = fileName.substring(i);
        String fileUUID =  UUID.randomUUID().toString()+suffix;
        File dest = new File(new File(resourcePath).getAbsolutePath()+ "/" + fileUUID);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        multipartFile.transferTo(dest); // 保存文件
        // 信息保存到数据库
        Resources resources = new Resources();
        resources.setComId(comId);
        resources.setResourceName(fileName);
        resources.setResourceType(resourceType);
        resources.setResourceUrl(resourceUrl + fileUUID);
        resources.setResourcePath(dest.getAbsoluteFile().toString());
        resources.setUploadTime(new Date());
        Integer r=  this.resourceDao.insertResource(resources);
        return r;

    }

    @Override
    public List<Resources> getResource(String comId,Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return this.resourceDao.selectResources(comId) ;
    }

    @Override
    public Integer getCountByComId(String comId) {
        return this.resourceDao.selectCountByCmtId(comId);
    }

    @Override
    public Integer removeById(Integer id) {
        return this.resourceDao.deleteById(id);
    }

    @Override
    public List<Resources> getForSearch(String comId,String resourceName) {
          Map paramsMap = new HashMap();
          paramsMap.put("comId",comId);
          paramsMap.put("resourceName",resourceName);
         return this.resourceDao.selectForSearch(paramsMap);
    }

    @Override
    public Resources getResourceById(Integer id) {
        return this.resourceDao.selectResourceById(id);
    }
}
