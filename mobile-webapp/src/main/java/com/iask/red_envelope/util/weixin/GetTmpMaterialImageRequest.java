package com.iask.red_envelope.util.weixin;

import com.wujie.common.sdk.support.enums.HttpMethod;
import com.wujie.common.sdk.support.exception.ApiRuleException;
import com.wujie.common.sdk.weixin.official_account.WeixinRequest;
import com.wujie.common.sdk.weixin.official_account.internal.util.CheckUtils;
import com.wujie.common.sdk.weixin.official_account.response.WeixinStreamResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tuxingxing on 16-4-27.
 */
public class GetTmpMaterialImageRequest extends WeixinRequest<WeixinStreamResponse> {

    // 要获取的素材的media_id
    private String media_id;

    @Override
    public String getApiSubPath() {
        return "/cgi-bin/media/get";
    }

    @Override
    public Map<String, String> getUrlParams() {
        Map<String, String> params = new HashMap<>();
        params.put("media_id", media_id);
        return params;
    }

    @Override
    public String getApiBasePath() {
        return "http://file.api.weixin.qq.com";
    }

    @Override
    public void check() throws ApiRuleException {
        CheckUtils.checkNotEmpty(media_id, "media_id");
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
