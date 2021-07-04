package com.karirpad.apps.model.internal;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created By reynard on 7/4/21.
 */
@Entity(tableName = "company_data")
public class CompanyData implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "image")
    String image;

    @ColumnInfo(name = "job_title")
    String jobTitle;

    @ColumnInfo(name = "company_name")
    String companyName;

    @ColumnInfo(name = "company_info")
    String companyInfo;

    @ColumnInfo(name = "company_location")
    String companyLocation;

    @ColumnInfo(name = "submited")
    String submited;

    @ColumnInfo(name = "time_posting")
    String timePosting;

    @ColumnInfo(name = "job_type")
    String jobType;

    @Ignore
    public CompanyData() {
    }

    public CompanyData(int id, String image, String jobTitle, String companyName, String companyInfo, String companyLocation, String submited, String timePosting, String jobType) {
        this.id = id;
        this.image = image;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.companyInfo = companyInfo;
        this.companyLocation = companyLocation;
        this.submited = submited;
        this.timePosting = timePosting;
        this.jobType = jobType;
    }

    protected CompanyData(Parcel in) {
        id = in.readInt();
        image = in.readString();
        jobTitle = in.readString();
        companyName = in.readString();
        companyInfo = in.readString();
        companyLocation = in.readString();
        submited = in.readString();
        timePosting = in.readString();
        jobType = in.readString();
    }

    public static final Creator<CompanyData> CREATOR = new Creator<CompanyData>() {
        @Override
        public CompanyData createFromParcel(Parcel in) {
            return new CompanyData(in);
        }

        @Override
        public CompanyData[] newArray(int size) {
            return new CompanyData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(image);
        parcel.writeString(jobTitle);
        parcel.writeString(companyName);
        parcel.writeString(companyInfo);
        parcel.writeString(companyLocation);
        parcel.writeString(submited);
        parcel.writeString(timePosting);
        parcel.writeString(jobType);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getSubmited() {
        return submited;
    }

    public void setSubmited(String submited) {
        this.submited = submited;
    }

    public String getTimePosting() {
        return timePosting;
    }

    public void setTimePosting(String timePosting) {
        this.timePosting = timePosting;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
}
