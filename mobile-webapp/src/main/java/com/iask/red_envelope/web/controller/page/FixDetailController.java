package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.ParamConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.model.ReFixedRed;
import com.iask.red_envelope.model.vo.DetailSeparateVo;
import com.iask.red_envelope.model.vo.FixDetailListVo;
import com.iask.red_envelope.model.vo.GrabFixRedVo;
import com.iask.red_envelope.service.FixDetailService;
import com.iask.red_envelope.service.user.UserService;
import com.iask.red_envelope.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/8/26.
 * 定时红包 领取详情
 */
@RequestMapping(value = "/p/fixRed", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class FixDetailController {

    @Autowired
    private FixDetailService fixDetailService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/detail/{redId}")
    public String gotoDetailListPage(HttpServletRequest request,
                                     @PathVariable("redId") Integer redId) throws Exception {

        // 查询第一页数据
        List<FixDetailListVo> details = fixDetailService.getAllDetailByRedId(redId, 1);

        // 查询前三
        List<FixDetailListVo> tops = fixDetailService.getTop3Detail(redId);

        if(tops != null && tops.size() > 0) {
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    if (tops.size() >= 1) {
                        request.setAttribute("top1", tops.get(i));
                    }
                }else if (i == 1) {
                    if (tops.size() >= 2) {
                        request.setAttribute("top2", tops.get(i));
                    }
                }else if (i == 2) {
                    if (tops.size() >= 3) {
                        request.setAttribute("top3", tops.get(i));
                    }
                }
            }
        }

        // 红包领取情况
        DetailSeparateVo vo = fixDetailService.getSeparateVo(redId);

        // 已领取总数
        Integer total = fixDetailService.getTotalNumByRedId(redId);

        // 总页数
        Integer totalPage = total % ValueConsts.pageSize > 0 ? total / ValueConsts.pageSize+1 :total / ValueConsts.pageSize;

        ReFixedRed reFixedRed = fixDetailService.getFixRedById(redId);

        boolean isLogin = userService.isLogin(request);
        Integer userId = null;
        if (isLogin){

            userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
            String icon = userService.getUserById(userId).getPortrait();
            request.setAttribute("icon",icon);

            GrabFixRedVo grabFixRedVo = fixDetailService.getGrabVo(userId,redId);

            request.setAttribute("grab",grabFixRedVo);
        }
        request.setAttribute("details", details);
        request.setAttribute("title", reFixedRed.getFixedTitle());
        request.setAttribute("pageNum", 1);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("redId", redId);
        request.setAttribute("separate", vo);

        return "fix_detail2";

    }


    /**
     * 上下滑动屏幕
     * @param request
     * @param redId
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String turnPage(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.redId, required = true) Integer redId,
                           @RequestParam(value = ParamConsts.pageNum, defaultValue = "1") Integer pageNum) {

        List<FixDetailListVo> details = fixDetailService.getAllDetailByRedId(redId, pageNum);

        Integer total = fixDetailService.getTotalNumByRedId(redId);
        Integer totalPage = total % ValueConsts.pageSize1 > 0 ? total / ValueConsts.pageSize1+1 :total / ValueConsts.pageSize1;

        Map<String,Object> map = new HashMap<>(4);

        map.put("details",details);
        map.put("pageNum",pageNum);
        map.put("totalPage",totalPage);
        map.put("redId",redId);

        return JsonUtil.buildSuccessData(map);

    }
}
