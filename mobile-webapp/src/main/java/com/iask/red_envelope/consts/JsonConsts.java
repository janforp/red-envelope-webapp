package com.iask.red_envelope.consts;

import com.iask.red_envelope.util.JsonUtil;

/**
 * Created by wuqiang on 15-8-11.
 * <p/>
 * 一些固定的JSON结果
 *
 * @author wuqiang
 */
public class JsonConsts {

    // 内部错误
    public static final String error_system = JsonUtil.buildError(JsonCodeConsts.error_system, "内部错误");
    // 签名错误
    public static final String error_sign = JsonUtil.buildError(JsonCodeConsts.error_sign, "签名错误");
    // 时间戳错误
    public static final String error_timestamp = JsonUtil.buildError(JsonCodeConsts.error_timestamp, "时间戳错误");
    // 重复请求
    public static final String error_repeat_request = JsonUtil.buildError(JsonCodeConsts.error_repeat_request, "重复请求");
    // 请求次数超限
    public static final String error_over_limit_max_request_count = JsonUtil.buildError(JsonCodeConsts.error_over_limit_max_request_count, "请求次数超限");
    // app_key已过期
    public static final String error_app_key_overtime = JsonUtil.buildError(JsonCodeConsts.error_app_key_overtime, "app_key已过期");
    // 参数错误
    public static final String error_param = JsonUtil.buildError(JsonCodeConsts.error_param, "参数错误");

    public static final String error_param_format_req_id = JsonUtil.buildError(JsonCodeConsts.error_param, "参数格式错误：req_id只能由1~32位字母/数组组成（大小敏感）");
    public static final String error_data_not_exist = JsonUtil.buildError(JsonCodeConsts.error_data_not_exist, "参数错误：数据不存在");

    public static final String ERROR_userKey_required = JsonUtil.buildError(7, "uk required.");

}
