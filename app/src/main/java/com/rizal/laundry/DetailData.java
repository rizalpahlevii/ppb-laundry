package com.rizal.laundry;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailData extends AppCompatActivity {
    TextView uid, name, address, phone, city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        uid = (TextView) findViewById(R.id.uid);
        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        city = (TextView) findViewById(R.id.city);

        uid.setText(getIntent().getStringExtra("uid"));
        name.setText(getIntent().getStringExtra("name"));
        address.setText(getIntent().getStringExtra("address"));
        phone.setText(getIntent().getStringExtra("phone"));
        city.setText(getIntent().getStringExtra("city"));
    }
}
