package com.example.mks.furabonorenewal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactAdd extends Activity {
    ArrayList<ItemData> aData_add;


    TextView text_add_name, text_add_age, text_add_phone, text_add_country;
    TextView debugTest;

    //EditText editText_add_name, editText_add_age, editText_add_phonenum, editText_add_country;

    Button ContactAdd;

    ImageView ContactImage;

    boolean isKeyboardShow = false;

    //String inputName="", inputAge="", inputPhone="", inputCountry="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_add);

        Intent i = getIntent();

        aData_add = i.getExtras().getParcelableArrayList("list");

        text_add_name = (TextView) findViewById(R.id.editText_add_name);
        text_add_age = (TextView) findViewById(R.id.editText_add_age);
        text_add_phone = (TextView) findViewById(R.id.editText_add_phonenum);
        text_add_country = (TextView) findViewById(R.id.editText_add_country);

        debugTest = (TextView) findViewById(R.id.debugtest);

        final EditText editText_add_name = (EditText) findViewById(R.id.editText_add_name);
        final EditText editText_add_age = (EditText) findViewById(R.id.editText_add_age);
        final EditText editText_add_phonenum = (EditText) findViewById(R.id.editText_add_phonenum);
        final EditText editText_add_country = (EditText) findViewById(R.id.editText_add_country);

        ContactAdd = (Button) findViewById(R.id.contact_add_button);

        ContactImage = (ImageView) findViewById(R.id.Contact_add_Iamge);


        ContactAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aData_add.add(new ItemData(inputName, inputPhone, inputCountry, Integer.parseInt(inputAge)));


                final String inputName = editText_add_name.getText().toString();
                final String inputAge = editText_add_age.getText().toString();
                final String inputPhone = editText_add_phonenum.getText().toString();
                final String inputCountry = editText_add_country.getText().toString();


                aData_add.add(new ItemData(inputName, inputPhone, inputCountry, Integer.parseInt(inputAge)));

                //debugTest.setText(""+ aData_add.get(aData_add.size() - 1).strName + " " + aData_add.get(aData_add.size() - 1).intAge);



                Toast.makeText(getApplicationContext(), inputName + " 연락처 추가", Toast.LENGTH_SHORT).show();
                onBackPressed();

            }
        });

        //폰으로 어플을 실행시키면 처음에 키보드가 안보였다가, editText를 누르면 키보드가 보여지고 그런 부분//
        final InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        editText_add_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Auto-generated method stub
                if(isKeyboardShow) {
                    //InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.showSoftInput(getCurrentFocus(), 0);
                    isKeyboardShow = false;
                }

                else {
                    mgr.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    isKeyboardShow = true;
                }
            }
        });

        editText_add_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Auto-generated method stub
                if(isKeyboardShow) {
                    //InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.showSoftInput(getCurrentFocus(), 0);
                    isKeyboardShow = false;
                }

                else {
                    mgr.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    isKeyboardShow = true;
                }
            }
        });

        editText_add_phonenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Auto-generated method stub
                if(isKeyboardShow) {
                    //InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.showSoftInput(getCurrentFocus(), 0);
                    isKeyboardShow = false;
                }

                else {
                    mgr.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    isKeyboardShow = true;
                }
            }
        });

        editText_add_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Auto-generated method stub
                if(isKeyboardShow) {
                    //InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.showSoftInput(getCurrentFocus(), 0);
                    isKeyboardShow = false;
                }

                else {
                    mgr.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    isKeyboardShow = true;
                }
            }
        });

    }
}
