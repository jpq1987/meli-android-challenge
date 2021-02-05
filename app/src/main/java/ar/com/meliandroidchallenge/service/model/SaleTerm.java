package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SaleTerm implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("value_name")
    private String valueName;

    public SaleTerm() {
    }

    protected SaleTerm(Parcel in) {
        id = in.readString();
        name = in.readString();
        valueName = in.readString();
    }

    public static final Creator<SaleTerm> CREATOR = new Creator<SaleTerm>() {
        @Override
        public SaleTerm createFromParcel(Parcel in) {
            return new SaleTerm(in);
        }

        @Override
        public SaleTerm[] newArray(int size) {
            return new SaleTerm[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(valueName);
    }
}
