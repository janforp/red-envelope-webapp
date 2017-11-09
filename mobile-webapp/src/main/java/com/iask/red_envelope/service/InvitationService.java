package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.dao.ReUserDAO;
import com.iask.red_envelope.dao.ReUserInvitationDAO;
import com.iask.red_envelope.model.ReUser;
import com.iask.red_envelope.model.ReUserCommissionDetail;
import com.iask.red_envelope.model.ReUserInvitation;
import com.iask.red_envelope.model.vo.InvitationListVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/9/21.
 * 邀请好友
 */
@Service
public class InvitationService {

    @Autowired
    private ReUserDAO reUserDAO ;
    @Autowired
    private ReUserInvitationDAO reUserInvitationDAO;

    /**
     * 获取用户的邀请码
     * @param request
     * @return
     */
    public String getInvitationCode(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        String invitationCode = null ;

        if (userId != null) {

            ReUser user = reUserDAO.selectByUserIdAndStatus(userId);

            invitationCode = user.getInvitationCode() ;
        }

        return invitationCode ;
    }

    /**
     * 获取邀请列表
     * @param request
     * @param pageNum
     * @return
     */
    public List<InvitationListVo> getInvitationList(HttpServletRequest request,Integer pageNum) {

        int offset = (pageNum - 1) * ValueConsts.pageSize ;

        RowBounds bounds = new RowBounds(offset , ValueConsts.pageSize) ;

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        List<ReUserInvitation> invitations = reUserInvitationDAO.getInvitationList(userId , bounds);

        List<InvitationListVo> vos = new ArrayList<>(invitations.size());

        for (ReUserInvitation invitation : invitations) {

            InvitationListVo vo = turnInvitationToVo(invitation) ;

            if(vo == null) {

                continue;
            }

            vos.add(vo);
        }

        return vos;

    }

    /**
     *
     * @param invitation
     * @return
     */
    public InvitationListVo turnInvitationToVo(ReUserInvitation invitation) {

        InvitationListVo vo = null;
        Integer invitedId = invitation.getInvitedUserId();
        ReUser user = reUserDAO.selectByUserIdAndStatus(invitedId) ;


        if (user != null) {
            vo = new InvitationListVo();
            vo.setIcon(user.getPortrait());
            vo.setName(user.getNickname());
            vo.setTime(invitation.getInvitedTime());
        }

        return vo;
    }


    /**
     * 记录数量
     * @param request
     * @return
     */
    public Integer getInvitationNum(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return reUserInvitationDAO.getInvitationNumByUserId(userId);
    }



}
