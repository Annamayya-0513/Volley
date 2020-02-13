package com.example.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetRequest extends AppCompatActivity {

    TextView IdTxtView, EmailTxtView,Mobile;
    Button OneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_request);

        IdTxtView = (TextView)findViewById(R.id.IdTxtView);
        EmailTxtView =(TextView)findViewById(R.id.EmailTxtView);
        Mobile =(TextView)findViewById(R.id.MobileTxtView);
        OneButton =(Button)findViewById(R.id.OneButton);

        final RequestQueue requestQueue = Volley.newRequestQueue(GetRequest.this);

        OneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonparse();
            }

            private void jsonparse() {
                String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d(response.toString(),"aaaaaaa");

                        try {
//read array within overall obj {[]}
                            JSONArray jsonArray = response.getJSONArray("users");
//read objects inside the array {[{},{},{}]}
                            for (int i = 0; i <= jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                if(jsonObject.has("id" )) {
                                    String id = jsonObject.getString("id" );
                                    IdTxtView.setText(id);
                                }

                                if(jsonObject.has("email")){
                                    String email = jsonObject.getString("email");
                                    EmailTxtView.setText(email);

                                }

                                for (int j= 0; j <=jsonObject.length();j++){
                                    JSONObject jsonObject1 = jsonObject.getJSONObject("contact");

                                    if(jsonObject1.has("mobile")){
                                        String mobile = jsonObject1.getString("mobile");
                                        Mobile.setText(mobile);
                                        Toast.makeText(GetRequest.this,mobile,Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                requestQueue.add(jsonObjectRequest);

            }

        });
    }
}
