package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.CurrentLoginUser;
import com.hlt.annotations.SkipAuth;
import com.hlt.controller.BaseController;
import com.qiniu.util.StringUtils;
import com.hlt.entity.*;
import com.hlt.service.CartService;
import com.hlt.service.GoodsService;
import com.hlt.service.GoodsSpecificatioService;
import com.hlt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/16 下午6:20
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;
    @Autowired
    private GoodsSpecificatioService goodsSpecificatioService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProductService productService;

    @PostMapping("/getcart")
    public Object getCartInfo(@CurrentLoginUser UserEntity currentUser){
        HashMap<String,Object> resultMap = new HashMap();

        Map param = new HashMap();
        param.put("user_id",currentUser.getUserId());
        List<CartEntity> cartList = cartService.queryList(param);

        //购物车中商品数量
        Integer goodsQuantity = 0;
        //购物车商品价值
        BigDecimal goodsAmount = new BigDecimal(0.00);
        //选中的购物车商品数
        Integer checkedGoodsQuantity = 0;
        //选中的商品价值
        BigDecimal checkedGoodsAmount = new BigDecimal(0.00);

        for (CartEntity cart:cartList){
            goodsQuantity = goodsQuantity+(cart.getNumber());
            goodsAmount = goodsAmount.add(cart.getRetail_price().multiply(new BigDecimal(cart.getNumber())));

            if (cart.getChecked() != null && cart.getChecked()==1){
                checkedGoodsQuantity = checkedGoodsQuantity+(cart.getNumber());
                checkedGoodsAmount = checkedGoodsAmount.add(cart.getRetail_price().multiply(new BigDecimal(cart.getNumber())));
            }
        }
        //处理优惠券

    }

    //@SkipAuth
    @PostMapping("/save")
    public Object save(@CurrentLoginUser UserEntity loginUser){
        JSONObject jsonRequest = getJsonRequest();
        String session_id = jsonRequest.getString("sessionId");
        Integer goods_id = jsonRequest.getInteger("goodsId");
        Integer number = jsonRequest.getInteger("number");
        Integer checked = jsonRequest.getInteger("checked");
        String list_pic_url = jsonRequest.getString("list_pic_url");

        GoodsEntity goodsEntity = goodsService.queryObject(goods_id);
        if (goodsEntity == null){
            return toResponsFail("商品已下架");
        }
        String goods_sn = goodsEntity.getGoods_sn();
        String goods_name = goodsEntity.getName();
        Integer product_id = goodsEntity.getProduct_id();
        BigDecimal market_price = goodsEntity.getMarket_price();
        BigDecimal retail_price = goodsEntity.getRetail_price();

        ProductEntity productEntity = productService.queryObject(product_id);
        String goods_specification_ids = productEntity.getGoods_specification_ids();

        CartEntity cartEntity = new CartEntity();
        cartEntity.setUser_id(loginUser.getUserId());
        cartEntity.setSession_id(session_id);
        cartEntity.setChecked(checked);
        cartEntity.setList_pic_url(list_pic_url);
        cartEntity.setGoods_id(goods_id);
        cartEntity.setNumber(number);
        cartEntity.setGoods_name(goods_name);
        cartEntity.setGoods_sn(goods_sn);
        cartEntity.setProduct_id(product_id);
        cartEntity.setMarket_price(market_price);
        cartEntity.setRetail_price(retail_price);

        cartService.save(cartEntity);

        return toResponsObject(200,"购物车保存成功",cartEntity);
    }

    //添加商品进购物车
    @PostMapping("/addtocart")
    public Object addToCart(@CurrentLoginUser UserEntity currentUser){
        JSONObject jsonParam = getJsonRequest();
        Integer goodsId = jsonParam.getInteger("goodsId");
        Integer productId = jsonParam.getInteger("productId");
        Integer number = jsonParam.getInteger("number");

        //判断商品是否可以购买
        GoodsEntity goods = goodsService.queryObject(goodsId);
        //如果商品不存在或者商品已删除又或者商品处于非卖状态
        if (goods==null || goods.getIs_delete() == 1 || goods.getIs_on_sale() != 1){
            return toResponsFail("此商品已下架");
        }
        //如果商品可以购买，则获取商品不同规格信息以及对应的数量
        ProductEntity product = productService.queryObject(productId);
        if (product == null || product.getGoods_number()<number){
            return toResponsFail("产品库存不足");
        }
        //判断购物车中是否存在此商品
        HashMap cartParam = new HashMap();
        cartParam.put("user_id",currentUser.getUserId());
        cartParam.put("goods_id",goodsId);
        cartParam.put("product_id",productId);
        //如果能够查到结果，最多只能有一个
        List<CartEntity> cartList = cartService.queryList(cartParam);

        CartEntity cartEntity;

        if (cartList != null && cartList.size()>0){
            cartEntity = cartList.get(0);
        }else {
            cartEntity=null;
        }
        //如果cartEntity为空，说明购物车中没有此商品，就新增商品进购物车
        if (cartEntity == null){
            //商品规格值
            String[] goodsSepcifitionValue = null;

            if (product.getGoods_specification_ids()!= null && product.getGoods_specification_ids().length()>0){
                //商品规格查询参数
                Map sprcificationParam = new HashMap();
                // ids数组中保存的元素类似：["1","5"]这样
                String[] ids = getGoodsSpecificationIds(product.getGoods_specification_ids());

                sprcificationParam.put("goods_id",goodsId);
                sprcificationParam.put("ids",ids);

                List<GoodsSpecificationEntity> goodsSpecificationList = goodsSpecificatioService.queryList(sprcificationParam);

                goodsSepcifitionValue = new String[goodsSpecificationList.size()];

                for (int i=0;i<goodsSpecificationList.size();i++){
                    //将商品的规格值存入数组
                    goodsSepcifitionValue[i] = goodsSpecificationList.get(i).getValue();
                }
            }

           cartEntity = new CartEntity();

           cartEntity.setGoods_id(goodsId);
           cartEntity.setGoods_sn(product.getGoods_sn());
           cartEntity.setProduct_id(productId);
           cartEntity.setUser_id(currentUser.getUserId());
           cartEntity.setGoods_name(goods.getName());
           cartEntity.setList_pic_url(goods.getList_pic_url());
           cartEntity.setNumber(number);
           cartEntity.setSession_id("1");
           cartEntity.setMarket_price(product.getMarket_price());
           cartEntity.setRetail_price(product.getRetail_price());

           if (goodsSepcifitionValue != null){
               //使用“；”将goodsSepcifitionValue中的值的字符连接起来 黑色；XXL  这样的值
               cartEntity.setGoods_specifition_name_value(StringUtils.join(goodsSepcifitionValue,";"));
           }
              cartEntity.setGoods_specifition_ids(product.getGoods_specification_ids());
              cartEntity.setChecked(1);

              cartService.save(cartEntity);
        }else {
            /**
             * 如果购物车中已经有此商品,则需要增加购物车中此商品的数量。
             * 这里需要判断此商品的库存与购物车原来此商品的数量+此次购买的数量
             */
            if (product.getGoods_number()<(cartEntity.getNumber()+number)){
                return toResponsFail("库存不足");
            }
            //如果库存充足
            cartEntity.setNumber(number+cartEntity.getNumber());

            cartService.update(cartEntity);
        }
        return null;
    }



    //获取商品规格ids，返回一个字符数组
    public String[] getGoodsSpecificationIds(String ids){
       String[] idsArray = null;
       if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)){
           /**
            * 在 goods_specification 表中goods_specification_ids是以类似于 1_2,3_8，2_3 这样的
            * 由“_”连接的数字组成，因此可用split方法以“_”字符为基准分拆ids为1，2这样的
            */
           String[] tempArray = ids.split("_");
           if (tempArray != null && tempArray.length>0){
               idsArray = tempArray;
           }
       }
       return idsArray;
    }
}
