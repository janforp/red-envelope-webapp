package com.iask.red_envelope.enums;

/**
 * Created by Jan on 16/8/25.
 */
public enum HbUserStatus {

    /**
     * 永久封号
     */
    permanent_blockd (0) ,

    /**
     * 正常
     */
    normal(1);

    public final Integer val ;

    private HbUserStatus(Integer val){

        this.val = val ;
    }



}
