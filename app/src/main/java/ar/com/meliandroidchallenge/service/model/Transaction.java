package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Transaction implements Parcelable {
    @SerializedName("total")
    private long total;
    @SerializedName("canceled")
    private long canceled;
    @SerializedName("completed")
    private long completed;
    @SerializedName("period")
    private String period;
    @SerializedName("ratings")
    private Rating ratings;

    public Transaction(){}

    protected Transaction(Parcel in) {
        total = in.readLong();
        canceled = in.readLong();
        completed = in.readLong();
        period = in.readString();
        ratings = in.readParcelable(Rating.class.getClassLoader());
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCanceled() {
        return canceled;
    }

    public void setCanceled(long canceled) {
        this.canceled = canceled;
    }

    public long getCompleted() {
        return completed;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Rating getRatings() {
        return ratings;
    }

    public void setRatings(Rating ratings) {
        this.ratings = ratings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(total);
        parcel.writeLong(canceled);
        parcel.writeLong(completed);
        parcel.writeString(period);
        parcel.writeParcelable(ratings, i);
    }
}
