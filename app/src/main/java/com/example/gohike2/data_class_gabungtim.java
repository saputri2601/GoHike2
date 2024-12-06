package com.example.gohike2;

import android.os.Parcel;
import android.os.Parcelable;

public class data_class_gabungtim implements Parcelable {
    private String teamName;
    private String slotAvailability;
    private String tripDate;
    private String tripPrice;
    private String trailName;
    private int imageResId;

    public data_class_gabungtim(String teamName, String slotAvailability, String tripDate, String tripPrice, String trailName, int imageResId) {
        this.teamName = teamName;
        this.slotAvailability = slotAvailability;
        this.tripDate = tripDate;
        this.tripPrice = tripPrice;
        this.trailName = trailName;
        this.imageResId = imageResId;
    }

    protected data_class_gabungtim(Parcel in) {
        teamName = in.readString();
        slotAvailability = in.readString();
        tripDate = in.readString();
        tripPrice = in.readString();
        trailName = in.readString();
        imageResId = in.readInt();
    }

    public static final Creator<data_class_gabungtim> CREATOR = new Creator<data_class_gabungtim>() {
        @Override
        public data_class_gabungtim createFromParcel(Parcel in) {
            return new data_class_gabungtim(in);
        }

        @Override
        public data_class_gabungtim[] newArray(int size) {
            return new data_class_gabungtim[size];
        }
    };

    // Getters
    public String getTeamName() {
        return teamName;
    }

    public String getSlotAvailability() {
        return slotAvailability;
    }

    public String getTripDate() {
        return tripDate;
    }

    public String getTripPrice() {
        return tripPrice;
    }

    public String getTrailName() {
        return trailName;
    }

    public int getImageResId() {
        return imageResId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(teamName);
        dest.writeString(slotAvailability);
        dest.writeString(tripDate);
        dest.writeString(tripPrice);
        dest.writeString(trailName);
        dest.writeInt(imageResId);
    }
}
