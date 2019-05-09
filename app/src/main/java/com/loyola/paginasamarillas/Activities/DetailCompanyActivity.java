package com.loyola.paginasamarillas.Activities;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.loyola.paginasamarillas.Models.Company;
import com.loyola.paginasamarillas.R;
import com.loyola.paginasamarillas.Repositories.CompanyRepository;

public class DetailCompanyActivity extends AppCompatActivity {

    private TextView detailName;
    private TextView detailPhone;
    private TextView detailAddress;
    private TextView detailInfo;
    private ImageView detailLogo;

    private String number;
    private String name;
    private String email;
    private String url;
    private String message;
    private String subject;
    private String shareMsg;

    private ImageButton btnWeb;
    private ImageButton btnEmail;
    private ImageButton btnSms;
    private ImageButton btnShare;
    private ImageButton btnCall;

    private static final int CALL_PERMISSION_REQUEST=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_company);

        detailInfo = findViewById(R.id.detail_info);
        detailAddress = findViewById(R.id.detail_address);
        detailName = findViewById(R.id.detail_name);
        detailPhone = findViewById(R.id.detail_phone);
        detailLogo = findViewById(R.id.detail_logo);

        int id=getIntent().getExtras().getInt("id");

        Company company = CompanyRepository.getCompany(id);

        if (company!=null){
            int photo = getResources().getIdentifier(
                company.getLogo(),
               "drawable",
                getPackageName()
            );

            detailName.setText(company.getName());
            detailAddress.setText(company.getAddress());
            detailPhone.setText(company.getPhone());
            detailInfo.setText(company.getInfo());
            detailLogo.setImageResource(photo);

            number =company.getPhone();
            email = company.getEmail();
            name = company.getName();
            url = company.getUrl();
        }

        btnWeb = findViewById(R.id.btn_web);
        btnEmail = findViewById(R.id.btn_email);
        btnSms = findViewById(R.id.btn_sms);
        btnShare = findViewById(R.id.btn_share);
        btnCall = findViewById(R.id.btn_call);

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchWeb(url);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject="Mayor informacion por favor :)";
                SendEmail(email,subject);
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message="¡Hola! Quisiera mayor información acerca de "+"'"+name+"'";
                SendMessage(message);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareMsg="¡Hola! Visita nuestra página web ";

                if(!url.startsWith("http://") || (!url.startsWith("https://"))){
                    url="http://"+url;
                }
                Share(shareMsg,url);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallCompany();
            }
        });

    }

    private void CallCompany(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST);
            return;
        }

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel: "+number));

            startActivity(intent);

    }

    private void SendEmail(String address, String subject) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void SendMessage(String message) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void SearchWeb (String url) {

        if(!url.startsWith("http://") || (!url.startsWith("https://"))){
            url="http://"+url;
        }

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);

        startActivity(intent);
    }

    private void Share(String shareMessage,String url){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        String msg = shareMessage + url;
        intent.putExtra(Intent.EXTRA_TEXT, msg);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
