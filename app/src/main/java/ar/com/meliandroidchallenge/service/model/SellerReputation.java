package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SellerReputation implements Parcelable {
    @SerializedName("transactions")
    private Transaction transactions;
    @SerializedName("power_seller_status")
    private String powerSellerStatus;
    @SerializedName("metrics")
    private Metric metrics;
    @SerializedName("level_id")
    private String levelID;

    public SellerReputation() {
    }

    protected SellerReputation(Parcel in) {
        transactions = in.readParcelable(Transaction.class.getClassLoader());
        powerSellerStatus = in.readString();
        metrics = in.readParcelable(Metric.class.getClassLoader());
        levelID = in.readString();
    }

    public static final Creator<SellerReputation> CREATOR = new Creator<SellerReputation>() {
        @Override
        public SellerReputation createFromParcel(Parcel in) {
            return new SellerReputation(in);
        }

        @Override
        public SellerReputation[] newArray(int size) {
            return new SellerReputation[size];
        }
    };

    public Transaction getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction transactions) {
        this.transactions = transactions;
    }

    public String getPowerSellerStatus() {
        return powerSellerStatus;
    }

    public void setPowerSellerStatus(String powerSellerStatus) {
        this.powerSellerStatus = powerSellerStatus;
    }

    public Metric getMetrics() {
        return metrics;
    }

    public void setMetrics(Metric metrics) {
        this.metrics = metrics;
    }

    public String getLevelID() {
        return levelID;
    }

    public void setLevelID(String levelID) {
        this.levelID = levelID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(transactions, i);
        parcel.writeString(powerSellerStatus);
        parcel.writeParcelable(metrics, i);
        parcel.writeString(levelID);
    }
}

