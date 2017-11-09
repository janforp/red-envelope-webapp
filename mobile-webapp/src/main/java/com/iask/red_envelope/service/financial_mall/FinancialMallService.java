package com.iask.red_envelope.service.financial_mall;

import com.iask.red_envelope.dao.ReFinancialMallDAO;
import com.iask.red_envelope.model.ReFinancialMall;
import com.iask.red_envelope.model.vo.financial_mall.FinancialListVo;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 16/11/18.
 * 理财大厅
 */
@Service
public class FinancialMallService {

    @Autowired
    private ReFinancialMallDAO reFinancialMallDAO;

    /**
     * 排序规则0:综合性排序,1:按收益排序,2:按流动性排序
     * @param request
     * @param orderType
     * @return
     */
    public List<FinancialListVo> getListOrderByType(HttpServletRequest request, Integer orderType){

        boolean isIos = CommonMethod.isAppleBrower(request);
        int platform = 0;
        if (!isIos){
            platform = 1;
        }
        List<ReFinancialMall> malls = reFinancialMallDAO.getListOrderByType(orderType,platform);

        return turnVo(malls);
    }

    /**
     * 转换
     * @param malls
     * @return
     */
    public List<FinancialListVo> turnVo(List<ReFinancialMall> malls){

        List<FinancialListVo> vos = null;

        if (malls != null && malls.size() > 0){

            vos = new ArrayList<>();
            for (ReFinancialMall mall : malls){

                FinancialListVo vo = new FinancialListVo();
                vo.setId(mall.getId());
                vo.setTitle(mall.getTitle());
                vo.setIcon(mall.getIcon());
                vo.setDesc(mall.getDesc());
                vo.setInvestmentTime(mall.getInvestmentTime());
                vo.setPackageName(mall.getPackageName());
                vo.setAndroidPackageUrl(mall.getAndroidPackageUrl());
                vo.setIosPackageUrl(mall.getIosPackageUrl());
                vo.setClickUrl(mall.getClickUrl());
                String labels = mall.getLabels();
                if (!StringUtil.isEmpty(labels)){

                    String[] labs = labels.split(",");
                    List<String> label = Arrays.asList(labs);
                    vo.setLabel(label);
                }

                vo.setMoney(turnToString(mall.getMoney()));
                vo.setEarning(turnToString(mall.getEarning()));

                vos.add(vo);
            }
        }
        return vos;
    }


    /**
     * 若是14.15则需要14.15,若是14.00则需要14
     * @param num
     * @return
     */
    public String turnToString(BigDecimal num){

        String numStr = num.toString();
        if (numStr.contains(".00")){

            numStr = numStr.substring(0,numStr.indexOf("."));
        }

        return numStr;
    }

}
