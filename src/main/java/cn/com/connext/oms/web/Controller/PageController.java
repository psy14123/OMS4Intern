package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.service.TbAbnormalService;
import cn.com.connext.oms.service.TbRefundService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * <p>Title: PageController</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/8 15:23
 */
@Controller
public class PageController {

    @Autowired
    private TbRefundService tbRefundService;

    @Autowired
    private TbAbnormalService tbAbnormalService;

    /**
    * @Author: caps
    * @Description:异常订单列表详情页面
    * @Param: []
    * @Return: java.lang.String
    * @Create: 2019/1/12 16:01
    */
 /*   @RequestMapping("/abnormalModel")
    public String abnormalDetail(){
        return "pages/specific/abnormal-order";
    }*/

    /*@RequiresPermissions({"checked"})//没有的话 AuthorizationException*/
    @GetMapping("/abnormalDetail")
    @ApiOperation(value = "异常订单详情接口")
    public String abnormalDetail(int abnormalId,Model model){
        try {
            Map<String, Object> map = tbAbnormalService.abnormalDetail(abnormalId);
            model.addAttribute("map",map);
            return "pages/specific/abnormal-order";
        } catch (Exception e) {
            return null;
        }
    }
    @RequestMapping("/abnormal")
    public String abnormal(){
        return "pages/details/orders/error-order-list";
    }
    /**
    * @Author: caps
    * @Description:首页
    * @Param: []
    * @Return: java.lang.String
    * @Create: 2019/1/9 9:41
    */
    @RequestMapping("/index")
    public String indexPage(){
        return "pages/index/index";
    }


    /** 
    * @Description: 查看退款单页面
    * @Param: [] 
    * @return: java.lang.String 
    * @Author: Lili Chen 
    * @Date: 2019/1/10 
    */
    @RequestMapping("/refund")
    public String refundPage(Model model){
        Map<String,Object> map=tbRefundService.getAllRefundIndex(1,4);
        model.addAttribute("map",map);
        return "pages/details/orders/refund-list";
    }
    /**
     * @Description: 登录页面
     * @Param: []
     * @return: java.lang.String
     * @Author: Lili Chen
     * @Date: 2019/1/10
     */
    @GetMapping({"/","/login"})
    public String login(){
        return "pages/login/loadingOrder";
    }

}
