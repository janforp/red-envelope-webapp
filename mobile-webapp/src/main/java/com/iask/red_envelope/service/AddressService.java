package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.dao.ReAddressDAO;
import com.iask.red_envelope.dao.ReUserDAO;
import com.iask.red_envelope.model.ReAddress;
import com.iask.red_envelope.model.ReUser;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jan on 16/9/20.
 * 收货地址
 */
@Service
public class AddressService {

    @Autowired
    private ReAddressDAO reAddressDAO ;
    @Autowired
    private ReUserDAO reUserDAO;

    /**
     * 增加或修改地址
     * @param request
     * @param address
     * @return
     */
    public String doSaveAddress(HttpServletRequest request, ReAddress address) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        ReAddress oldAddress = reAddressDAO.selectByPrimaryKey(userId) ;

        String phone = address.getMobile();
        if (StringUtils.isEmpty(phone)){
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"手机号不能空");
        }
        if (! CommonMethod.isValidCellphone(phone) && phone.length() != 11) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"手机号不合法");
        }

        String name = address.getRealName();
        if (StringUtils.isEmpty(name)){
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"姓名不能为空");
        }

        String province = address.getProvince() ;
        if (StringUtils.isEmpty(province)){
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"省不能空");
        }

        String city = address.getCity();
        if (StringUtils.isEmpty(city)){
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"市不能空");
        }

        String detailAddress = address.getDetailAddress();
        if (StringUtils.isEmpty(detailAddress)){
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"详细地址不能空");
        }

        if (oldAddress == null) {

            address.setUserId(userId);
            reAddressDAO.insertSelective(address);

            return JsonUtil.buildSuccess("添加地址成功");

        }else {

            address.setUserId(userId);
            reAddressDAO.updateByPrimaryKeySelective(address);

            return JsonUtil.buildSuccess("修改地址成功");
        }
    }


    /**
     * 获取地址
     * @param request
     * @return
     */
    public ReAddress getAddress(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return reAddressDAO.selectByPrimaryKey(userId) ;
    }

    /**
     * 弹出地址框时的数据
     * @param request
     * @return
     */
    public ReAddress showAddress(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        ReAddress address = reAddressDAO.selectByPrimaryKey(userId) ;

        ReUser user = reUserDAO.selectByUserIdAndStatus(userId);

        if (address == null) {

            address = new ReAddress() ;

            address.setDetailAddress(user.getAddress());
            address.setRealName(user.getRealName());
            address.setMobile(user.getMobile());
        } else {

            String mobile = address.getMobile() ;
            if (StringUtils.isEmpty(mobile)){
                address.setMobile(mobile);
            }
            String name = address.getRealName();
            if (StringUtils.isEmpty(name)){
                address.setRealName(name);
            }
            String detailAddress = address.getDetailAddress();
            if (StringUtils.isEmpty(detailAddress)) {
                address.setDetailAddress(detailAddress);
            }
        }

        return address ;
    }
}
