package com.example.mks.furabonorenewal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    private ArrayList<ItemData> aData = null;
    private int aDataNum = 0;

    public ListAdapter(ArrayList<ItemData> aData){
        this.aData = aData;
        this.aDataNum = aData.size();
    }

    @Override
    public int getCount()
    {
        return aDataNum;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            final Context context = parent.getContext();

            //아직 inflate가 한번도 일어나지 않았을때//
            if (inflater == null)
            {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }

            //inflate가 한번이상 일어났었다면 if문을 수행하지않고 바로 convertView에 사용함.//
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        //listview_item.xml의 이미지뷰에 이미지를 연결해 주는 부분  //
        //ImageView imageView = (ImageView) convertView.findViewById(R.id.ListView_imageView);
        //imageView.setImageResource(R.drawable.simple1);

        //이미지 소스파일들을 배열로 만들어서 사용하기//
        //Image 파일명을 맞춰준다. img0.png, img1.png 이런식으로..//
        //int i = 0; (i%16) 하니깐 제대로 들어가지 않는데, inflate할때마다 랜덤으로 설정해주니//
        //계획한대로 사진이 잘 들어간다. //
        double RandomIndex = Math.random();
        // 0 ~ 99 까지의 정수가 생성됨. 밑에서 intIndex%16으로 imgs배열의 index로 사용할것 .. //
        int intIndex = (int)(RandomIndex * 100);
        int[] imgs = {
                R.drawable.simple0,  R.drawable.simple1,  R.drawable.simple2,  R.drawable.simple3,
                R.drawable.simple4,  R.drawable.simple5,  R.drawable.simple6,  R.drawable.simple7,
                R.drawable.simple8,  R.drawable.simple9,  R.drawable.simple10, R.drawable.simple11,
                R.drawable.simple12, R.drawable.simple13, R.drawable.simple14, R.drawable.simple15
        };

        //listview_item.xml에 연결해주는 부분//
        ImageView imageView      = (ImageView) convertView.findViewById(R.id.ListView_imageView);
        TextView TextName        = (TextView) convertView.findViewById(R.id.ListView_textName);
        TextView TextAge         = (TextView) convertView.findViewById(R.id.ListView_textAge);
        TextView TextNum         = (TextView) convertView.findViewById(R.id.ListView_textNum);
        TextView TextCountry     = (TextView) convertView.findViewById(R.id.ListView_textCountry);

        imageView.setImageResource(imgs[position % 16]);
        TextName.setText(aData.get(position).strName);
        TextAge.setText(String.valueOf(aData.get(position).intAge) + "살");
        TextNum.setText(aData.get(position).strNum);
        TextCountry.setText("  " + aData.get(position).strCountry);

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
