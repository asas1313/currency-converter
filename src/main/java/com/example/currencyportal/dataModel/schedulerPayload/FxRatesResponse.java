package com.example.currencyportal.dataModel.schedulerPayload;

@SuppressWarnings("unused")
public class FxRatesResponse {
    private boolean success;
    private String jobId;
    private String jobGroup;
    private String message;

    public FxRatesResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public FxRatesResponse(boolean success, String jobId, String jobGroup, String message) {
        this.success = success;
        this.jobId = jobId;
        this.jobGroup = jobGroup;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String returnValue = "{success=" + success;
        if (!jobId.isEmpty()) returnValue += ", jobId='" + jobId + '\'';
        if (!jobGroup.isEmpty()) returnValue += ", jobGroup='" + jobGroup + '\'';
        returnValue += ", message='" + message + "'}";
        return returnValue;
    }
}
