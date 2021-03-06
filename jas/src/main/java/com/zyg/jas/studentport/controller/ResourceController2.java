package com.zyg.jas.studentport.controller;

import com.zyg.jas.common.pojo.Resources;
import com.zyg.jas.managerport.service.ResourceService;
import com.zyg.jas.studentport.service.ResourceService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/sport/resource")
@CrossOrigin
public class ResourceController2 {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController2.class);

   @Autowired
   private ResourceService2 resourceService;

    //批量删除留言
    @RequestMapping(value = "/delete/byBatch",method = RequestMethod.POST)
    @ResponseBody
    public  Integer deleteStuByBatchHandler(@RequestBody List<Resources> resources) {
        return resourceService.removeByBatch(resources);
    }

   // 根据comId,resourceName搜索资料
   @RequestMapping(value = "/getForSearch/{comId}/{resourceName}",method = RequestMethod.GET)
   @ResponseBody
   public List<Resources> getForSearchHandler(@PathVariable("comId") String comId,@PathVariable("resourceName") String resourceName){
       return this.resourceService.getForSearch(comId,resourceName);
   }

   // 根据资料id删除资料
   @RequestMapping(value = "/deleteById/{id}",method = RequestMethod.GET)
   @ResponseBody
   public Integer deleteHandler(@PathVariable("id") Integer id){
       return this.resourceService.removeById(id);
   }

   // 获取该学委发布的资料数量
   @RequestMapping(value = "/getCount/{comId}",method = RequestMethod.GET)
   @ResponseBody
   public Integer getCountHandler(@PathVariable("comId") String comId){
       return this.resourceService.getCountByComId(comId);
   }

   // 获取资料信息，分页显示
    @RequestMapping(value = "/getResource/{comId}/{pageNo}/{pageSize}",method = RequestMethod.GET)
    @ResponseBody
    public List<Resources> saveResourceHandler(@PathVariable("comId") String comId,@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize")Integer pageSize)  {
        return this.resourceService.getResource(comId,pageNo,pageSize);
    }

    // 保存资料
    @RequestMapping(value = "/saveResource",method = RequestMethod.POST)
    @ResponseBody
    public String saveResourceHandler(@RequestParam("resourceFile")MultipartFile multipartFile,
                                      @RequestParam("resourceType") String resourceType, @RequestParam("comId") String comId) throws Exception {
        Integer r = this.resourceService.saveResource(multipartFile,resourceType,comId);
        if (r==1){
            return "success";
        }else {
            return "false";
        }
    }

//学生端使用
    // 获取资料信息not with id
    @RequestMapping(value = "/getResourceWithoutId",method = RequestMethod.GET)
    @ResponseBody
    public List<Resources> getResourceHandler()  {
        return this.resourceService.getResourceWithoutId();
    }

}
