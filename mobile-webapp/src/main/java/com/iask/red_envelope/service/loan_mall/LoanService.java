package com.iask.red_envelope.service.loan_mall;

import com.iask.red_envelope.dao.ReLoanMallDAO;
import com.iask.red_envelope.model.ReLoanMall;
import com.iask.red_envelope.model.vo.loan_mall.LoanListVo;
import com.iask.red_envelope.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Janita on 2016/12/19.
 * 贷款中心
 */
@Service
public class LoanService {
    @Autowired
    private ReLoanMallDAO reLoanMallDAO;

    /**
     * 排序规则0:贷款额度,1:利息,2:到账时间
     * @param request
     * @param orderType
     * @return
     */
    public List<LoanListVo> getListOrderByType(HttpServletRequest request, Integer orderType){


        List<ReLoanMall> loans = reLoanMallDAO.getListOrderByType(orderType);

        return turnVo(loans);
    }

    /**
     * 转换
     * @param malls
     * @return
     */
    public List<LoanListVo> turnVo(List<ReLoanMall> malls){

        List<LoanListVo> vos = null;

        if (malls != null && malls.size() > 0){

            vos = new ArrayList<>();
            for (ReLoanMall mall : malls){

                LoanListVo vo = new LoanListVo();
                vo.setId(mall.getId());
                vo.setTitle(mall.getTitle());
                vo.setIcon(mall.getIcon());
                vo.setClickUrl(mall.getClickUrl());
                vo.setParticipantsNum(mall.getParticipantsNum());
                vo.setDisplayMoney(mall.getDisplayMoney());
                vo.setMonthInterestRate(mall.getMonthInterestRate());
                String labels = mall.getLabels();
                if (!StringUtil.isEmpty(labels)){

                    String[] labs = labels.split(",");
                    List<String> label = Arrays.asList(labs);
                    vo.setLabel(label);
                }

                vos.add(vo);
            }
        }
        return vos;
    }
}
