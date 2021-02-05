package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Installments implements Parcelable {
    @SerializedName("quantity")
    private long quantity;
    @SerializedName("amount")
    private double amount;
    @SerializedName("rate")
    private double rate;
    @SerializedName("currency_id")
    private String currencyID;

    public Installments() {
    }

    protected Installments(Parcel in) {
        quantity = in.readLong();
        amount = in.readDouble();
        rate = in.readDouble();
        currencyID = in.readString();
    }

    public static final Creator<Installments> CREATOR = new Creator<Installments>() {
        @Override
        public Installments createFromParcel(Parcel in) {
            return new Installments(in);
        }

        @Override
        public Installments[] newArray(int size) {
            return new Installments[size];
        }
    };

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(String currencyID) {
        this.currencyID = currencyID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(quantity);
        parcel.writeDouble(amount);
        parcel.writeDouble(rate);
        parcel.writeString(currencyID);
    }
}
