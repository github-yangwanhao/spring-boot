package cn.yangwanhao.multipledatasource.model.db;

import java.io.Serializable;
import java.util.Date;

import lombok.ToString;

@ToString
public class ExceptionTrade implements Serializable {
    private String exceptionId;

    private String errorCode;

    private String errorMessage;

    private String moduleName;

    private String retryUrl;

    private String retryHttpMethod;

    private Integer retryTimes;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private String retryParam;

    private static final long serialVersionUID = 1L;

    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId == null ? null : exceptionId.trim();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getRetryUrl() {
        return retryUrl;
    }

    public void setRetryUrl(String retryUrl) {
        this.retryUrl = retryUrl == null ? null : retryUrl.trim();
    }

    public String getRetryHttpMethod() {
        return retryHttpMethod;
    }

    public void setRetryHttpMethod(String retryHttpMethod) {
        this.retryHttpMethod = retryHttpMethod == null ? null : retryHttpMethod.trim();
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRetryParam() {
        return retryParam;
    }

    public void setRetryParam(String retryParam) {
        this.retryParam = retryParam == null ? null : retryParam.trim();
    }
}