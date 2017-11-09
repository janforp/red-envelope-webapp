package com.iask.red_envelope.model;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-07-13
 */
public class ReParamConfig implements java.io.Serializable {

    // Fields

    // 参数类型
    private String paramType;
    // 参数名称
    private String paramName;
    // 参数值1
    private String firstValue;
    // 参数值2
    private String secondValue;

    // Constructors

    /**
     * default constructor
     */
    public ReParamConfig() {
    }

    /**
     * full constructor
     */
    public ReParamConfig(String paramType, String paramName, String firstValue, String secondValue) {
        this.paramType = paramType;
        this.paramName = paramName;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    // Property accessors

    /**
     * 参数类型
     */
    public String getParamType() {
        return this.paramType;
    }

    /**
     * 参数类型
     */
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    /**
     * 参数名称
     */
    public String getParamName() {
        return this.paramName;
    }

    /**
     * 参数名称
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * 参数值1
     */
    public String getFirstValue() {
        return this.firstValue;
    }

    /**
     * 参数值1
     */
    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    /**
     * 参数值2
     */
    public String getSecondValue() {
        return this.secondValue;
    }

    /**
     * 参数值2
     */
    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

}