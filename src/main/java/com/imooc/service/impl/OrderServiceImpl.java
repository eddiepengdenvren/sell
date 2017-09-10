package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.OrderService;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hasee on 2017/9/10.
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        BigDecimal totalPrice = new BigDecimal("0");
        for(OrderDetail orderDetail:orderDetailList){
            //1.遍历订单项验证是否有不存在的商品
            ProductInfo productInfo = productInfoRepository.findOne(orderDetail.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总金额
            totalPrice = orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(totalPrice);
            //3.订单项入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(productInfo,orderDetail);
        orderDetailRepository.save(orderDetail);
    }
    //4.订单主表入库
    OrderMaster orderMaster = new OrderMaster();
    orderMaster.setOrderId(orderId);
    orderMaster.setOrderAmount(totalPrice);
    orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
    orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
    BeanUtils.copyProperties(orderDTO,orderMaster);
    orderMasterRepository.save(orderMaster);

    //5.减库存

    return null;
    }

    @Override
    public OrderDTO findOne(String openId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
