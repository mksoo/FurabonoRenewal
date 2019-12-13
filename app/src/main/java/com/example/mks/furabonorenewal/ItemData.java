package com.example.mks.furabonorenewal;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemData implements Parcelable {
    public String strName;
    public String strNum;
    public String strCountry;
    public int intAge;

    public ItemData(String strName, String strNum, String strCountry, int intAge){
        this.strName    = strName;
        this.strNum     = strNum;
        this.intAge     = intAge;
        this.strCountry = strCountry;
    }
    public ItemData(Parcel in){
        strNum      = in.readString();
        strName     = in.readString();
        intAge      = in.readInt();
        strCountry  = in.readString();
    }

    public static final Creator<ItemData> CREATOR = new Creator<ItemData>(){
        @Override
        public ItemData createFromParcel(Parcel in ){
            return new ItemData(in);
        }

        @Override
        public ItemData[] newArray (int size){
            return new ItemData[size];
        }
    };

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(strName);
        dest.writeString(strNum);
        dest.writeInt(intAge);
        dest.writeString(strCountry);
    }

}