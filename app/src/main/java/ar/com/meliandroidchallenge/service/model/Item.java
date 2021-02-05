package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import ar.com.meliandroidchallenge.utils.StringUtils;

public class Item implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("permalink")
    private String permalink;
    @SerializedName("seller")
    private Seller seller;
    @SerializedName("address")
    private Address address;
    @SerializedName("official_store_id")
    private long officialStoreID;
    @SerializedName("currency_id")
    private String currencyID;
    @SerializedName("price")
    private double price;
    @SerializedName("sale_price")
    private double salePrice;
    @SerializedName("original_price")
    private double originalPrice;
    @SerializedName("base_price")
    private double basePrice;
    @SerializedName("buying_mode")
    private String buyingMode;
    @SerializedName("accepts_mercadopago")
    private boolean acceptsMercadopago;
    @SerializedName("sale_terms")
    private List<SaleTerm> saleTerms;
    @SerializedName("installments")
    private Installments installments;
    @SerializedName("initial_quantity")
    private long initialQuantity;
    @SerializedName("available_quantity")
    private long availableQuantity;
    @SerializedName("sold_quantity")
    private long soldQuantity;
    @SerializedName("condition")
    private String condition;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("secure_thumbnail")
    private String secureThumbnail;
    @SerializedName("pictures")
    private List<Picture> pictures;
    @SerializedName("attributes")
    private List<ItemAttribute> attributes;
    @SerializedName("category_id")
    private String categoryID;
    @SerializedName("domain_id")
    private String domainID;
    @SerializedName("catalog_product_id")
    private String catalogProductID;
    @SerializedName("catalog_listing")
    private boolean catalogListing;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("shipping")
    private Shipping shipping;
    @SerializedName("international_delivery_mode")
    private String internationalDeliveryMode;
    @SerializedName("warranty")
    private String warranty;
    @SerializedName("status")
    private String status;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("stop_time")
    private String stopTime;
    @SerializedName("date_created")
    private String dateCreated;
    @SerializedName("last_updated")
    private String lastUpdated;

    public Item() {
    }


    protected Item(Parcel in) {
        id = in.readString();
        title = in.readString();
        subtitle = in.readString();
        permalink = in.readString();
        seller = in.readParcelable(Seller.class.getClassLoader());
        address = in.readParcelable(Address.class.getClassLoader());
        officialStoreID = in.readLong();
        currencyID = in.readString();
        price = in.readDouble();
        salePrice = in.readDouble();
        originalPrice = in.readDouble();
        basePrice = in.readDouble();
        buyingMode = in.readString();
        acceptsMercadopago = in.readByte() != 0;
        saleTerms = in.createTypedArrayList(SaleTerm.CREATOR);
        installments = in.readParcelable(Installments.class.getClassLoader());
        initialQuantity = in.readLong();
        availableQuantity = in.readLong();
        soldQuantity = in.readLong();
        condition = in.readString();
        thumbnail = in.readString();
        secureThumbnail = in.readString();
        pictures = in.createTypedArrayList(Picture.CREATOR);
        attributes = in.createTypedArrayList(ItemAttribute.CREATOR);
        categoryID = in.readString();
        domainID = in.readString();
        catalogProductID = in.readString();
        catalogListing = in.readByte() != 0;
        tags = in.createStringArrayList();
        shipping = in.readParcelable(Shipping.class.getClassLoader());
        internationalDeliveryMode = in.readString();
        warranty = in.readString();
        status = in.readString();
        startTime = in.readString();
        stopTime = in.readString();
        dateCreated = in.readString();
        lastUpdated = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getOfficialStoreID() {
        return officialStoreID;
    }

    public void setOfficialStoreID(long officialStoreID) {
        this.officialStoreID = officialStoreID;
    }

    public String getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(String currencyID) {
        this.currencyID = currencyID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getBuyingMode() {
        return buyingMode;
    }

    public void setBuyingMode(String buyingMode) {
        this.buyingMode = buyingMode;
    }

    public boolean isAcceptsMercadopago() {
        return acceptsMercadopago;
    }

    public void setAcceptsMercadopago(boolean acceptsMercadopago) {
        this.acceptsMercadopago = acceptsMercadopago;
    }

    public List<SaleTerm> getSaleTerms() {
        return saleTerms;
    }

    public void setSaleTerms(List<SaleTerm> saleTerms) {
        this.saleTerms = saleTerms;
    }

    public Installments getInstallments() {
        return installments;
    }

    public void setInstallments(Installments installments) {
        this.installments = installments;
    }

    public long getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(long initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public long getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(long availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public long getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(long soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }

    public void setSecureThumbnail(String secureThumbnail) {
        this.secureThumbnail = secureThumbnail;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<ItemAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ItemAttribute> attributes) {
        this.attributes = attributes;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getDomainID() {
        return domainID;
    }

    public void setDomainID(String domainID) {
        this.domainID = domainID;
    }

    public String getCatalogProductID() {
        return catalogProductID;
    }

    public void setCatalogProductID(String catalogProductID) {
        this.catalogProductID = catalogProductID;
    }

    public boolean isCatalogListing() {
        return catalogListing;
    }

    public void setCatalogListing(boolean catalogListing) {
        this.catalogListing = catalogListing;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getInternationalDeliveryMode() {
        return internationalDeliveryMode;
    }

    public void setInternationalDeliveryMode(String internationalDeliveryMode) {
        this.internationalDeliveryMode = internationalDeliveryMode;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(subtitle);
        parcel.writeString(permalink);
        parcel.writeParcelable(seller, i);
        parcel.writeParcelable(address, i);
        parcel.writeLong(officialStoreID);
        parcel.writeString(currencyID);
        parcel.writeDouble(price);
        parcel.writeDouble(salePrice);
        parcel.writeDouble(originalPrice);
        parcel.writeDouble(basePrice);
        parcel.writeString(buyingMode);
        parcel.writeByte((byte) (acceptsMercadopago ? 1 : 0));
        parcel.writeTypedList(saleTerms);
        parcel.writeParcelable(installments, i);
        parcel.writeLong(initialQuantity);
        parcel.writeLong(availableQuantity);
        parcel.writeLong(soldQuantity);
        parcel.writeString(condition);
        parcel.writeString(thumbnail);
        parcel.writeString(secureThumbnail);
        parcel.writeTypedList(pictures);
        parcel.writeTypedList(attributes);
        parcel.writeString(categoryID);
        parcel.writeString(domainID);
        parcel.writeString(catalogProductID);
        parcel.writeByte((byte) (catalogListing ? 1 : 0));
        parcel.writeStringList(tags);
        parcel.writeParcelable(shipping, i);
        parcel.writeString(internationalDeliveryMode);
        parcel.writeString(warranty);
        parcel.writeString(status);
        parcel.writeString(startTime);
        parcel.writeString(stopTime);
        parcel.writeString(dateCreated);
        parcel.writeString(lastUpdated);
    }

}
