package ar.com.meliandroidchallenge.service.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

class Paging implements Parcelable {
    @SerializedName("total")
    private long total;
    @SerializedName("primary_results")
    private long primaryResults;
    @SerializedName("offset")
    private long offset;
    @SerializedName("limit")
    private long limit;

    public Paging(){}

    protected Paging(Parcel in) {
        total = in.readLong();
        primaryResults = in.readLong();
        offset = in.readLong();
        limit = in.readLong();
    }

    public static final Creator<Paging> CREATOR = new Creator<Paging>() {
        @Override
        public Paging createFromParcel(Parcel in) {
            return new Paging(in);
        }

        @Override
        public Paging[] newArray(int size) {
            return new Paging[size];
        }
    };

    public long getTotal() { return total; }
    public void setTotal(long value) { this.total = value; }

    public long getPrimaryResults() { return primaryResults; }
    public void setPrimaryResults(long value) { this.primaryResults = value; }

    public long getOffset() { return offset; }
    public void setOffset(long value) { this.offset = value; }

    public long getLimit() { return limit; }
    public void setLimit(long value) { this.limit = value; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(total);
        parcel.writeLong(primaryResults);
        parcel.writeLong(offset);
        parcel.writeLong(limit);
    }
}
