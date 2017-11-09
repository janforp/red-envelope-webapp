package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.ParamConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.model.ReBanner;
import com.iask.red_envelope.model.ReMission;
import com.iask.red_envelope.model.ReMissionSort;
import com.iask.red_envelope.model.dto.WxNotShareSign;
import com.iask.red_envelope.model.vo.MissionDetailVo;
import com.iask.red_envelope.model.vo.MissionListVo;
import com.iask.red_envelope.service.AppMissionService;
import com.iask.red_envelope.service.WxNotShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Jan on 16/8/9.
 * 红包拿不停页面
 */
@Controller
@RequestMapping(value = "/p/mission", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class AppMissionController {

    @Autowired
    private AppMissionService appMissionService;
    @Autowired
    private WxNotShareService wxNotShareService;



    /**
     * 跳转到 banner 红包 列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/bannerRedList")
    public String gotoRedEnvelopeBannerListPage(HttpServletRequest request){

        List<ReBanner> banners = appMissionService.getBannerList(request);
        List<MissionListVo> missions = appMissionService.getHotMissionList(request);
        List<ReMissionSort> sorts = appMissionService.getSortList(request);

        request.setAttribute("banners",banners);
        request.setAttribute("missions",missions) ;
        request.setAttribute("sorts",sorts);

        return "mission_index";
    }

    /**
     * 任务详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/missionDetail/{missionId}")
    public String gotoMissionDetailPage(HttpServletRequest request,
                                        @PathVariable("missionId") Integer missionId) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        appMissionService.addParticipantsNum (missionId);

        MissionDetailVo mission = appMissionService.getMissionById(missionId);

        request.setAttribute("mission",mission);


        WxNotShareSign share = wxNotShareService.getSign(request);

        request.setAttribute("share",share);

        return "mission_detail";
    }

    /**
     * 根据分类 显示 任务
     * @param request
     * @param sortId
     * @return
     */
    @RequestMapping(value = "/sortList/{sortId}")
    public String gotoMissionListBySortId (HttpServletRequest request,
                                           @PathVariable("sortId") Integer sortId,
                                           @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum) {

        Integer total = appMissionService.getMissionListBySortIdNum(sortId);

        Integer totalPage = total / ValueConsts.pageSize ;

        if (total % ValueConsts.pageSize > 0) {

            totalPage = totalPage + 1 ;
        }

        List<MissionListVo> missions = appMissionService.getMissionListBySortId(sortId,pageNum);

        if (missions != null && missions.size() > 0){

            String lastPage = "/c/p/red/sortList/"+sortId+"?pageNum="+(pageNum-1);
            String nextPage = "/c/p/red/sortList/"+sortId+"?pageNum="+(pageNum+1);

            request.setAttribute("missions",missions) ;
            request.setAttribute("totalPage",totalPage);
            request.setAttribute("sortId",sortId);
            request.setAttribute("pageNow",pageNum);
            request.setAttribute("lastPage",lastPage);
            request.setAttribute("nextPage",nextPage);

        }

        return "sort_list";
    }


}
