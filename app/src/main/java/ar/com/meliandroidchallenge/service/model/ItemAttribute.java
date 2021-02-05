package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;

public class ItemAttribute implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("value_name")
    private String valueName;
    @SerializedName("source")
    private long source;
    @SerializedName("attribute_group")
    private AttributeGroup attributeGroup;

    public ItemAttribute() {
    }

    protected ItemAttribute(Parcel in) {
        id = in.readString();
        name = in.readString();
        valueName = in.readString();
        source = in.readLong();
        attributeGroup = in.readParcelable(AttributeGroup.class.getClassLoader());
    }

    public static final Creator<ItemAttribute> CREATOR = new Creator<ItemAttribute>() {
        @Override
        public ItemAttribute createFromParcel(Parcel in) {
            return new ItemAttribute(in);
        }

        @Override
        public ItemAttribute[] newArray(int size) {
            return new ItemAttribute[size];
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

    public long getSource() {
        return source;
    }

    public void setSource(long source) {
        this.source = source;
    }

    public AttributeGroup getAttributeGroup() {
        return attributeGroup;
    }

    public void setAttributeGroup(AttributeGroup attributeGroup) {
        this.attributeGroup = attributeGroup;
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
        parcel.writeLong(source);
        parcel.writeParcelable(attributeGroup, i);
    }
}