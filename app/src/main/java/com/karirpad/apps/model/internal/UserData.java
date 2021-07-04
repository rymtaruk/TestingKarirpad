package com.karirpad.apps.model.internal;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created By reynard on 7/3/21.
 */
@Entity(tableName = "user_data")
public class UserData implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    int id;

    @SerializedName("profile_picture")
    @ColumnInfo(name = "profile_picture")
    String profilePicture;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    String name;

    @SerializedName("email")
    @ColumnInfo(name = "email")
    String email;

    @SerializedName("password")
    @ColumnInfo(name = "password")
    String password;

    @SerializedName("phone_number")
    @ColumnInfo(name = "phone_number")
    String phoneNumber;

    @SerializedName("job_title")
    @ColumnInfo(name = "job_title")
    String jobTitle;

    @SerializedName("expected_salary")
    @ColumnInfo(name = "expected_salary")
    String expectedSalary;

    @SerializedName("biodata")
    @ColumnInfo(name = "biodata")
    String biodata;


    protected UserData(Parcel in) {
        id = in.readInt();
        profilePicture = in.readString();
        name = in.readString();
        email = in.readString();
        password = in.readString();
        jobTitle = in.readString();
        phoneNumber = in.readString();
        expectedSalary = in.readString();
        biodata = in.readString();
    }

    @Ignore
    public UserData() {
    }

    public UserData(int id, String profilePicture, String name, String email, String password, String jobTitle, String phoneNumber, String expectedSalary, String biodata) {
        this.id = id;
        this.profilePicture = profilePicture;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.jobTitle = jobTitle;
        this.expectedSalary = expectedSalary;
        this.biodata = biodata;
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getBiodata() {
        return biodata;
    }

    public void setBiodata(String biodata) {
        this.biodata = biodata;
    }
}
