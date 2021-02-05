package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Seller implements Parcelable {
    @SerializedName("id")
    private long id;
    @SerializedName("permalink")
    private String permalink;
    @SerializedName("registration_date")
    private String registrationDate;
    @SerializedName("car_dealer")
    private boolean carDealer;
    @SerializedName("real_estate_agency")
    private boolean realEstateAgency;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("eshop")
    private Eshop eshop;
    @SerializedName("seller_reputation")
    private SellerReputation sellerReputation;

    public Seller() {
    }


    protected Seller(Parcel in) {
        id = in.readLong();
        permalink = in.readString();
        registrationDate = in.readString();
        carDealer = in.readByte() != 0;
        realEstateAgency = in.readByte() != 0;
        tags = in.createStringArrayList();
        eshop = in.readParcelable(Eshop.class.getClassLoader());
        sellerReputation = in.readParcelable(SellerReputation.class.getClassLoader());
    }

    public static final Creator<Seller> CREATOR = new Creator<Seller>() {
        @Override
        public Seller createFromParcel(Parcel in) {
            return new Seller(in);
        }

        @Override
        public Seller[] newArray(int size) {
            return new Seller[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isCarDealer() {
        return carDealer;
    }

    public void setCarDealer(boolean carDealer) {
        this.carDealer = carDealer;
    }

    public boolean isRealEstateAgency() {
        return realEstateAgency;
    }

    public void setRealEstateAgency(boolean realEstateAgency) {
        this.realEstateAgency = realEstateAgency;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Eshop getEshop() {
        return eshop;
    }

    public void setEshop(Eshop eshop) {
        this.eshop = eshop;
    }

    public SellerReputation getSellerReputation() {
        return sellerReputation;
    }

    public void setSellerReputation(SellerReputation sellerReputation) {
        this.sellerReputation = sellerReputation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(id);
        parcel.writeString(permalink);
        parcel.writeString(registrationDate);
        parcel.writeByte((byte) (carDealer ? 1 : 0));
        parcel.writeByte((byte) (realEstateAgency ? 1 : 0));
        parcel.writeStringList(tags);
        parcel.writeParcelable(eshop, i);
        parcel.writeParcelable(sellerReputation, i);
    }

}
