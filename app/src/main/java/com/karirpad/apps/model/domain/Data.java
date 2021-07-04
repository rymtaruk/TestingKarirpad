package com.karirpad.apps.model.domain;

import java.io.Serializable;

/**
 * Created By reynard on 7/3/21.
 */
public class Data implements Serializable {
    private String image;
    private String jobTitle;
    private String companyName;
    private String companyInfo;
    private String companyLocation;
    private String submited;
    private String timePosting;
    private String jobType;

    public void setImage(String image) {
        this.image = image;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public void setSubmited(String submited) {
        this.submited = submited;
    }

    public void setTimePosting(String timePosting) {
        this.timePosting = timePosting;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getImage() {
        return image;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public String getSubmited() {
        return submited;
    }

    public String getTimePosting() {
        return timePosting;
    }

    public String getJobType() {
        return jobType;
    }
}
