package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MetricDetail implements Parcelable{
    @SerializedName("rate")
    private double rate;
    @SerializedName("value")
    private long value;
    @SerializedName("period")
    private String period;

    public MetricDetail(){}

    protected MetricDetail(Parcel in) {
        rate = in.readDouble();
        value = in.readLong();
        period = in.readString();
    }

    public static final Creator<MetricDetail> CREATOR = new Creator<MetricDetail>() {
        @Override
        public MetricDetail createFromParcel(Parcel in) {
            return new MetricDetail(in);
        }

        @Override
        public MetricDetail[] newArray(int size) {
            return new MetricDetail[size];
        }
    };

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(rate);
        parcel.writeLong(value);
        parcel.writeString(period);
    }
}
