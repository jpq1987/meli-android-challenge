package ar.com.meliandroidchallenge.service.model;

import android.os.Parcel;
import android.os.Parcelable;

public enum AttributeGroup implements Parcelable {
    EMPTY("EMPTY"), MAIN("MAIN"), OTHERS("OTHERS");

    private String group;

    AttributeGroup(){}

    AttributeGroup(String group) {
        this.group = group;
    }

    public static final Creator<AttributeGroup> CREATOR = new Creator<AttributeGroup>() {
        @Override
        public AttributeGroup createFromParcel(Parcel in) {
            AttributeGroup group = AttributeGroup.values()[in.readInt()];
            group.setGroup(in.readString());
            return group;
        }

        @Override
        public AttributeGroup[] newArray(int size) {
            return new AttributeGroup[size];
        }
    };

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(group);
    }
}
