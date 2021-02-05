package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Metric implements Parcelable {
    @SerializedName("sales")
    private Sale sales;
    @SerializedName("claims")
    private MetricDetail claims;
    @SerializedName("delayed_handling_time")
    private MetricDetail delayedHandlingTime;
    @SerializedName("cancellations")
    private MetricDetail cancellations;

    public Metric() {
    }

    protected Metric(Parcel in) {
        sales = in.readParcelable(Sale.class.getClassLoader());
        claims = in.readParcelable(MetricDetail.class.getClassLoader());
        delayedHandlingTime = in.readParcelable(MetricDetail.class.getClassLoader());
        cancellations = in.readParcelable(MetricDetail.class.getClassLoader());
    }

    public static final Creator<Metric> CREATOR = new Creator<Metric>() {
        @Override
        public Metric createFromParcel(Parcel in) {
            return new Metric(in);
        }

        @Override
        public Metric[] newArray(int size) {
            return new Metric[size];
        }
    };

    public Sale getSales() {
        return sales;
    }

    public void setSales(Sale sales) {
        this.sales = sales;
    }

    public MetricDetail getClaims() {
        return claims;
    }

    public void setClaims(MetricDetail claims) {
        this.claims = claims;
    }

    public MetricDetail getDelayedHandlingTime() {
        return delayedHandlingTime;
    }

    public void setDelayedHandlingTime(MetricDetail delayedHandlingTime) {
        this.delayedHandlingTime = delayedHandlingTime;
    }

    public MetricDetail getCancellations() {
        return cancellations;
    }

    public void setCancellations(MetricDetail cancellations) {
        this.cancellations = cancellations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(sales, i);
        parcel.writeParcelable(claims, i);
        parcel.writeParcelable(delayedHandlingTime, i);
        parcel.writeParcelable(cancellations, i);
    }
}
