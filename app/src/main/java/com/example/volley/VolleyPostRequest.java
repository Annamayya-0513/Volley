package com.example.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyPostRequest extends AppCompatActivity {

    Button PostButton;
    TextView PostTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_post_request);

        PostTxtView = (TextView)findViewById(R.id.PostTxtView);
        PostButton = (Button)findViewById(R.id.PostButton);


        final String url = "https://reqres.in/api/register/post";


        final RequestQueue requestQueue = Volley.newRequestQueue(VolleyPostRequest.this);

        PostButton.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(VolleyPostRequest.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(VolleyPostRequest.this, "server error", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(VolleyPostRequest.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }){
                    protected Map <String,String> getParams() {
                        Map <String, String> params = new HashMap<>();
                        params.put("email","mailto:eve.holt@reqres.in");
                        params.put("password","pistol");

                        return params;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });


    }
}



