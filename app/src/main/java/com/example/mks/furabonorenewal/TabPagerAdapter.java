package com.example.mks.furabonorenewal;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Locale;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private ArrayList<ItemData> aData = null;
    private String[] Imgs;

    private ArrayList<String> ImgAL = new ArrayList<>();
    private ArrayList<String> MsAL = new ArrayList<>();

    private String BasePath;
    //private Context context;

    public TabPagerAdapter(FragmentManager fm, int tabCount, ArrayList<ItemData> address, String basePath) {
        super(fm);
        this.tabCount = tabCount;
        this.aData = address;
        this.BasePath = basePath + File.separator;
        //this.context = context;


        //SD card->Pictures에 있는 사진들의 경로를 받아오는 부분//
        File imagefile = new File(this.BasePath + "/Pictures");
        if (!imagefile.exists()) {
            if (!imagefile.mkdirs()) {
                Log.d("MyGallery", "fail to create dir");
            }
        }

        Log.e("asdf1111111111111asdf", BasePath);

        File[] imagefiles = imagefile.listFiles(

                //이 부분 주석처리 하면 모든 확장자를 가져올 수 있다. //
                /*new FileFilter() {
            private final String[] FileExtension = new String[] {"jpg", ".png", "jpeg", "gif"};

            @Override
            public boolean accept(File pathname) {
                for (String extension : FileExtension) {
                    if (pathname.getName().toLowerCase().endsWith(extension)) {
                        return pathname.getName().toLowerCase(Locale.US).endsWith(".jpg"); //확장자
                    }
                }

                return false;
            }
        }*/);
        if (imagefiles != null) {
            for (int i = 0; i < imagefiles.length; i++) {
                this.ImgAL.add(imagefiles[i].getName());
            }
        }


        //Log.e("11111111111",ImgAL.get(1));

        //File imagefile = new File(this.BasePath+"/Pictures");

        //File[] imagefiles = imagefile.listFiles(new FileFilter() {
        //    @Override
        //   public boolean accept(File pathname) {
        //        return pathname.getName().toLowerCase(Locale.US).endsWith(".jpg"); //확장자
        //    }
        //});
        //if(imagefiles != null){
        // for (int i = 0; i < imagefiles.length; i++){
        //     this.ImgAL.add(imagefiles[i].getName());
        // }
        // }

        //음악 플레이어에서 노래 목록을 가져오는 부분//
        File musicfile = new File(this.BasePath + "/Music");
        File[] musicfiles = musicfile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase(Locale.US).endsWith(".mp3"); //확장자
            }
        });
        if (musicfiles != null) {
            for (int i = 0; i < musicfiles.length; i++) {
                this.MsAL.add(musicfiles[i].getName());
            }
        }
    }

    @Override
    public Fragment getItem(int position) {

        //TODO case 2, 3 tkrwp
        switch (position) {
            case 0:
                PageOneFragment tabFragment1 = new PageOneFragment();
                Bundle bundle1 = new Bundle();

                bundle1.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) aData);
                tabFragment1.setArguments(bundle1);
                return tabFragment1;

            case 1:
                PageOneFragment tabFragment2 = new PageOneFragment();
                Bundle bundle2 = new Bundle();

                bundle2.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) aData);
                tabFragment2.setArguments(bundle2);
                return tabFragment2;

            case 2:
                PageOneFragment tabFragment3 = new PageOneFragment();
                Bundle bundle3 = new Bundle();

                bundle3.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) aData);
                tabFragment3.setArguments(bundle3);
                return tabFragment3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
