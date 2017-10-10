package com.imooc.enums;

import lombok.Getter;

/**
 * Created by hasee on 2017/9/10.
 */
@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存异常"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单项不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"取消订单失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PARAM_ERROR(17,"参数错误"),
    PARAM_TRANSFER_ERROR(18,"参数转换错误"),
    ORDER_OWNER_ERROR(19,"订单不属于当前用户"),
    WECHAT_MP_ERROR(20,"微信公众号异常"),
    WECHAT_PAY_AMOUNT_ERROR(21,"支付订单金额不一致"),
    CANCEL_ORDER_SUCCESS(22,"取消订单成功"),
    FINISH_ORDER_SUCCESS(23,"完结订单成功"),
    PRODUCT_STATUS_RRROR(24,"商品状态不正确"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
