package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.ReAddress;
import com.iask.red_envelope.service.AddressService;
import com.iask.red_envelope.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jan on 16/9/19.
 * 收货地址
 */
@RequestMapping(value = "/p/address", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class AddressController {

    @Autowired
    private AddressService addressService ;

    @RequestMapping(value = "/showAddress")
    @ResponseBody
    public String showAddress(HttpServletRequest request) {

        ReAddress address = addressService.showAddress(request) ;

        return JsonUtil.buildSuccessData(address);
    }


    @RequestMapping(value = "/save")
    @ResponseBody
    public String  save(HttpServletRequest request, ReAddress address) {


        return addressService.doSaveAddress(request,address);
    }
}
