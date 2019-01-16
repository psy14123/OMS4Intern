package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.dto.GoodsGoodsOrderDto;
import cn.com.connext.oms.commons.dto.GoodsStockDto;
import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.service.TbGoodsListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Title: TbGetGoodOrderById</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/7
 */
@RestController
public class TbGetGoodsListController {
    @Autowired
    private TbGoodsListService tbGoodsListService;
    /**
        * @Author: ZhaoJun
        * @Description: 根据订单编号查询该订单的商品编号
        * @Param: []
        * @Return: cn.com.connext.oms.commons.dto.BaseResult
        * @Create: 2019/1/7 11:51
        */
    @GetMapping("getGoodsList")
    @ApiOperation(value = "订单商品列表接口")
    public BaseResult GetGoodsList(int orderId){
        try {
            List<TbGoods> allGoods = tbGoodsListService.getGoodsImformation(orderId);
            return BaseResult.success("成功",allGoods);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("失败");
        }
    }
    /**
        * @Author: ZhaoJun
        * @Description: 查询所有商品的信息和库存
        * @Param: []
        * @Create: 2019/1/7 17:36
        */
    @GetMapping("getAllGoods")
    @ApiOperation(value = "查询所有商品的信息和库存")
    public BaseResult getAllGoods(int pageNum,int pageSize){
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<GoodsStockDto> getAllGoods =this.tbGoodsListService.getAllGoods();
            PageInfo<GoodsStockDto> goodsListInfo = new PageInfo<>(getAllGoods);
            return BaseResult.success("成功",goodsListInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }
    /**
        * @Author: ZhaoJun
        * @Description: 根据订单号查询订单对应的商品信息
        * @Param: []
        * @Return:
        * @Create: 2019/1/8 17:36
        */
   @GetMapping("goodsListFromOrder")
    public BaseResult goodsListFromOrder(int orderId){
        try {
            List<GoodsGoodsOrderDto> goodsListFromOrder= tbGoodsListService.goodsListFromOrder(orderId);
            return BaseResult.success("成功",goodsListFromOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }

}
