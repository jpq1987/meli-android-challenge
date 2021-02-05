package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable {
    @SerializedName("state_id")
    private String stateID;
    @SerializedName("state_name")
    private String stateName;
    @SerializedName("city_id")
    private String cityID;
    @SerializedName("city_name")
    private String cityName;

    public Address() {
    }

    protected Address(Parcel in) {
        stateID = in.readString();
        stateName = in.readString();
        cityID = in.readString();
        cityName = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(stateID);
        parcel.writeString(stateName);
        parcel.writeString(cityID);
        parcel.writeString(cityName);
    }
}
