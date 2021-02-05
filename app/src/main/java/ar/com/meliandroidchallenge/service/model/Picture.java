package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Picture implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("secure_url")
    private String secureURL;
    @SerializedName("size")
    private String size;
    @SerializedName("max_size")
    private String maxSize;
    @SerializedName("quality")
    private String quality;

    public Picture() {
    }

    protected Picture(Parcel in) {
        id = in.readString();
        url = in.readString();
        secureURL = in.readString();
        size = in.readString();
        maxSize = in.readString();
        quality = in.readString();
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecureURL() {
        return secureURL;
    }

    public void setSecureURL(String secureURL) {
        this.secureURL = secureURL;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(url);
        parcel.writeString(secureURL);
        parcel.writeString(size);
        parcel.writeString(maxSize);
        parcel.writeString(quality);
    }
}
