package com.example.user.machinetest;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {

    EditText RegNme, Regphone, Regaddress, Regemail, RegPassword;
    Button Regregter;
    AsyncHttpClient client;
    RequestParams params;
    JSONObject regjobj;
    String url = "http://sicsglobal.co.in/Food_App/API/Register.aspx";

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vreg = inflater.inflate(R.layout.fragment_register, container, false);
        client = new AsyncHttpClient();
        params = new RequestParams();
        RegNme = vreg.findViewById(R.id.regnme);
        Regphone = vreg.findViewById(R.id.regphoneno);
        Regaddress = vreg.findViewById(R.id.regAddress);
        Regemail = vreg.findViewById(R.id.regEmail);
        RegPassword = vreg.findViewById(R.id.regpassword);
        Regregter = vreg.findViewById(R.id.regggg);
        Regregter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.put("name", RegNme.getText().toString());
                params.put("phoneno", Regphone.getText().toString());
                params.put("address", Regaddress.getText().toString());
                params.put("email", Regemail.getText().toString());
                params.put("password", RegPassword.getText().toString());
                client.get(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        try {
                            regjobj = new JSONObject(content);
                            if (regjobj.getString("Status").equals("Success")) {
                                Toast.makeText(getContext(), "" + regjobj.getString("Status"), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "" + regjobj.getString("Status"), Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e) {
                        }
                    }
                });


            }

        });
        return vreg;
    }
}
