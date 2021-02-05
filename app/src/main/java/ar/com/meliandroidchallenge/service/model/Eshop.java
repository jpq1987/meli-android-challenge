package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Eshop implements Parcelable {
    @SerializedName("eshop_id")
    private long eshopID;
    @SerializedName("nick_name")
    private String nickName;
    @SerializedName("eshop_logo_url")
    private String eshopLogoURL;
    @SerializedName("eshop_Experience")
    private long eshopExperience;

    public Eshop() {
    }

    protected Eshop(Parcel in) {
        eshopID = in.readLong();
        nickName = in.readString();
        eshopLogoURL = in.readString();
        eshopExperience = in.readLong();
    }

    public static final Creator<Eshop> CREATOR = new Creator<Eshop>() {
        @Override
        public Eshop createFromParcel(Parcel in) {
            return new Eshop(in);
        }

        @Override
        public Eshop[] newArray(int size) {
            return new Eshop[size];
        }
    };

    public long getEshopID() {
        return eshopID;
    }

    public void setEshopID(long eshopID) {
        this.eshopID = eshopID;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEshopLogoURL() {
        return eshopLogoURL;
    }

    public void setEshopLogoURL(String eshopLogoURL) {
        this.eshopLogoURL = eshopLogoURL;
    }

    public long getEshopExperience() {
        return eshopExperience;
    }

    public void setEshopExperience(long eshopExperience) {
        this.eshopExperience = eshopExperience;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(eshopID);
        parcel.writeString(nickName);
        parcel.writeString(eshopLogoURL);
        parcel.writeLong(eshopExperience);
    }
}
