package com.example.volley;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
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

import java.util.ArrayList;

public class GetRequestList extends AppCompatActivity {

    ArrayList<pojo> arrayList;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_listview_request);

        listView = (ListView) findViewById(R.id.GetListView);
        arrayList = new ArrayList<pojo>();
        jsonparse();
    }

    private void jsonparse() {

        final RequestQueue requestQueue = Volley.newRequestQueue(GetRequestList.this);

        String url = "https://reqres.in/api/users";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    //read array within overall obj {[]}
                    JSONArray jsonArray = response.getJSONArray("data");
//read objects inside the array {[{},{},{}]}
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        pojo detailspojo = new pojo();

                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        if (jsonObject.has("id")) {
                            String id = jsonObject.getString("id");
                            detailspojo.setId(id);


                        }

                        if (jsonObject.has("email")) {
                            String email = jsonObject.getString("email");
                            detailspojo.setEmail(email);

                        }
                        arrayList.add(detailspojo);
                        Toast.makeText(getApplicationContext(),"bbbbbbbbbbbbb",Toast.LENGTH_LONG).show();
                    }

                    CustomAdapter adapter = new CustomAdapter(getApplicationContext(), arrayList);
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"aaaaaaa",Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(GetRequestList.this,"error",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}