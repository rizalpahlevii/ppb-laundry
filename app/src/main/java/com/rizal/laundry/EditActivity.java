package com.rizal.laundry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {
    EditText uid, name, address, phone, city;
    String Uid, Name, Address, Phone, Id, City;
    Button button;
    Boolean valid = true;
    ProgressDialog progressDialog;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        uid = (EditText) findViewById(R.id.uid);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.phone);
        city = (EditText) findViewById(R.id.city);
        progressDialog = new ProgressDialog(this);
        button = (Button) findViewById(R.id.button);

        Log.i(TAG , getIntent().getStringExtra("name"));
        Id = getIntent().getStringExtra("id");
        uid.setText(getIntent().getStringExtra("uid"));
        name.setText(getIntent().getStringExtra("name"));
        address.setText(getIntent().getStringExtra("address"));
        phone.setText(getIntent().getStringExtra("phone"));
        city.setText(getIntent().getStringExtra("city"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uid = uid.getText().toString();
                Name = name.getText().toString();
                Address = address.getText().toString();
                Phone = phone.getText().toString();
                City = phone.getText().toString();

                if(TextUtils.isEmpty(Uid)){
                    uid.setError("UID Cannot be Empty");
                    valid = false;
                }else {
                    valid = true;

                    if(TextUtils.isEmpty(Name)){
                        name.setError("Name Cannot be Empty");
                        valid = false;
                    }else {
                        valid = true;

                        if(TextUtils.isEmpty(Address)){
                            address.setError("Address Cannot be Empty");
                            valid = false;
                        }else {
                            valid = true;

                            if(TextUtils.isEmpty(Phone)){
                                phone.setError("Contact Number Cannot be Empty");
                                valid = false;
                            }else {
                                valid = true;
                                if(TextUtils.isEmpty(City)){
                                    phone.setError("Contact Number Cannot be Empty");
                                    valid = false;
                                }else {
                                    valid = true;
                                }
                            }
                        }

                    }
                }

                if(valid){
                    progressDialog.setMessage("Loading");
                    progressDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_UPDATE, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(EditActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                if(jsonObject.getString("message").equals("Edit Data Berhasil")){
                                    com.rizal.laundry.ListActivity.ma.refresh_list();
                                    finish();
                                    Intent intent = new Intent(EditActivity.this,ListActivity.class);
                                    EditActivity.this.startActivity(intent);
                                }
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(EditActivity.this, "Failed",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        protected Map<String , String> getParams() throws AuthFailureError {
                            Map<String , String> params = new HashMap<>();
                            params.put("id", Id);
                            params.put("name", Name);
                            params.put("uid", Uid);
                            params.put("phone", Phone);
                            params.put("address",Address);
                            params.put("city",City);
                            return params;
                        }
                    };
                    com.rizal.laundry.RequestHandler.getInstance(EditActivity.this).addToRequestQueue(stringRequest);

                }
            }
        });
    }
}
