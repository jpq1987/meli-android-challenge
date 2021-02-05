package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Shipping implements Parcelable {
    @SerializedName("free_shipping")
    private boolean freeShipping;
    @SerializedName("mode")
    private String mode;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("logistic_type")
    private String logisticType;
    @SerializedName("store_pick_up")
    private boolean storePickUp;

    public Shipping(){}

    protected Shipping(Parcel in) {
        freeShipping = in.readByte() != 0;
        mode = in.readString();
        tags = in.createStringArrayList();
        logisticType = in.readString();
        storePickUp = in.readByte() != 0;
    }

    public static final Creator<Shipping> CREATOR = new Creator<Shipping>() {
        @Override
        public Shipping createFromParcel(Parcel in) {
            return new Shipping(in);
        }

        @Override
        public Shipping[] newArray(int size) {
            return new Shipping[size];
        }
    };

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(String logisticType) {
        this.logisticType = logisticType;
    }

    public boolean isStorePickUp() {
        return storePickUp;
    }

    public void setStorePickUp(boolean storePickUp) {
        this.storePickUp = storePickUp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (freeShipping ? 1 : 0));
        parcel.writeString(mode);
        parcel.writeStringList(tags);
        parcel.writeString(logisticType);
        parcel.writeByte((byte) (storePickUp ? 1 : 0));
    }
}
