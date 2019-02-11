package com.example.user.machinetest;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    EditText loginEmail, loginpassword;
    Button loginbutton;
    AsyncHttpClient client;
    RequestParams params;
    JSONObject jobj;
    String url = "http://sicsglobal.co.in/Food_App/API/Login.aspx";

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vlog = inflater.inflate(R.layout.fragment_login, container, false);
        client = new AsyncHttpClient();
        params = new RequestParams();
        loginEmail = vlog.findViewById(R.id.logeml);
        loginpassword = vlog.findViewById(R.id.logpswd);
        loginbutton = vlog.findViewById(R.id.logbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String emll = loginEmail.getText().toString();

                String pswdd = loginpassword.getText().toString();

                params.put("email", emll);
                params.put("password", pswdd);
                client.get(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        try {
                            jobj = new JSONObject(content);
                            String str = jobj.getString("Status");

                            if (str.equals("Success")) {
                                String emll = loginEmail.getText().toString();
                                Toast.makeText(getContext(), "" + str, Toast.LENGTH_SHORT).show();
                                SharedPreferences patshred=getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);
                                SharedPreferences.Editor edd=patshred.edit();
                                edd.putString("emailkey",emll);

                                edd.commit();
                                Toast.makeText(getContext(), ""+emll, Toast.LENGTH_SHORT).show();
                               Intent in=new Intent(getContext(),userprofile.class);
                               startActivity(in);

                            }
                        } catch (Exception e) {

                        }
                    }
                });

            }

        });
        return vlog;
    }
}

