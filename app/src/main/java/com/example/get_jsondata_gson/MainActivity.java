package com.example.get_jsondata_gson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult=findViewById(R.id.text_view_result);
        Button buttonGetData=findViewById(R.id.button_get_data);

        requestQueue= Volley.newRequestQueue(this);

        buttonGetData.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }
    private void jsonParse(){
        String url="https://api.myjson.com/bins/10lakc";
        StringRequest request=new StringRequest(url,new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                Log.d("CODE",response);
                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();
                Student[] students=gson.fromJson(response,Student[].class);
                for(Student s : students){

                    String firstName=s.getFirstName();
                    String familyName=s.getFamillyName();
                    String dateEnrollment=s.getDateOfJoining();
                    textViewResult.append(firstName+","+familyName+","+dateEnrollment+"\n");

                }
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }
}