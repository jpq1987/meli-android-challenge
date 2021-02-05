package ar.com.meliandroidchallenge.service.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ar.com.meliandroidchallenge.service.model.Item;

public class ItemsResponse implements Parcelable {
    @SerializedName("site_id")
    private String siteID;
    @SerializedName("query")
    private String query;
    @SerializedName("paging")
    private Paging paging;
    @SerializedName("results")
    private List<Item> results;

    public ItemsResponse(){}

    protected ItemsResponse(Parcel in) {
        siteID = in.readString();
        query = in.readString();
        paging = in.readParcelable(Paging.class.getClassLoader());
        results = in.createTypedArrayList(Item.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(siteID);
        dest.writeString(query);
        dest.writeParcelable(paging, flags);
        dest.writeTypedList(results);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemsResponse> CREATOR = new Creator<ItemsResponse>() {
        @Override
        public ItemsResponse createFromParcel(Parcel in) {
            return new ItemsResponse(in);
        }

        @Override
        public ItemsResponse[] newArray(int size) {
            return new ItemsResponse[size];
        }
    };

    public String getSiteID() { return siteID; }
    public void setSiteID(String value) { this.siteID = value; }

    public String getQuery() { return query; }
    public void setQuery(String value) { this.query = value; }

    public Paging getPaging() { return paging; }
    public void setPaging(Paging value) { this.paging = value; }

    public List<Item> getResults() { return results; }
    public void setResults(List<Item> value) { this.results = value; }


}
