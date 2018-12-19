package cn.footman.controller;

import cn.footman.bean.T_MALL_PRODUCT;
import cn.footman.service.SpuService;
import cn.footman.util.MyFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author footman77
 * @create 2018-12-04 1:19
 */
@Controller
public class SpuController {

    @Autowired
    private SpuService spuService;

    @RequestMapping("goto_spu_add")
    public String goto_spu_add(ModelMap map, T_MALL_PRODUCT spu){

        map.put("spu",spu);
        return "spuAdd";
    }

    @RequestMapping("spu_add")
    public ModelAndView spu_add(@RequestParam("files") MultipartFile[] files, T_MALL_PRODUCT spu,int mainpic){

        //上传图片
        List<String> list_image = MyFileUpload.upload_image(files);
        //保存商品信息
        spuService.save_spu(spu,list_image,mainpic);

        ModelAndView modelAndView = new ModelAndView("redirect:/goto_spu_add.do");
        modelAndView.addObject("flbh1",spu.getFlbh1());
        modelAndView.addObject("flbh2",spu.getFlbh2());
        modelAndView.addObject("pp_id",spu.getPp_id());

//        System.out.println(spu);
        return modelAndView;
    }

    @RequestMapping("get_spu_list")
    @ResponseBody
    public List<T_MALL_PRODUCT> get_spu_list(int pp_id,int flbh2){
        List<T_MALL_PRODUCT> list = spuService.get_spu_list(pp_id,flbh2);
        return list;
    }

}
