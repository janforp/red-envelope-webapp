package com.iask.red_envelope.consts;

/**
 * Created by craig on 16/6/29.
 */
public interface JsonCodeConsts {
    // 请求成功返回的code
    int success = 0;
    // 内部错误
    int error_system = 1;
    // 签名错误
    int error_sign = 2;
    // 时间错误
    int error_timestamp = 3;
    // 重复请求错误
    int error_repeat_request = 4;
    // Api请求次数超限
    int error_over_limit_max_request_count = 5;
    // app_key已过期
    int error_app_key_overtime = 6;
    // 参数错误
    int error_param = 7;
    // 数据不存在
    int error_data_not_exist = 8;
    // 正常业务错误
    int error_normal = 9 ;

    // api请求成功返回的code
    int api_success = 200;

    //未注册
    int not_register = 10;

    //无快递地址
    int not_have_address = 11 ;

    int share_mission_is_end = 12;

}
