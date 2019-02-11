package com.example.user.machinetest;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class View_order_by_id extends Fragment {
 EditText viewemail,vieworderid;
 Button viewok;

    public View_order_by_id() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_view_order_by_id, container, false);
        viewemail=v.findViewById(R.id.vweml);
        vieworderid=v.findViewById(R.id.editText);
        viewok=v.findViewById(R.id.vwokk);

        SharedPreferences patshred=getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);
       String eml12=patshred.getString("emailkey",null);
        Toast.makeText(getContext(), ""+eml12, Toast.LENGTH_SHORT).show();

        viewemail.setText(""+eml12);
        viewok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String odr=vieworderid.getText().toString();
                SharedPreferences patshred=getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);
                SharedPreferences.Editor edd=patshred.edit();
                edd.putString("order",odr);
                edd.commit();

            order_views vws=new order_views();
                android.support.v4.app.FragmentTransaction hmfrag
                        = getFragmentManager().beginTransaction();
                hmfrag.replace(R.id.frame,vws);
                hmfrag.commit();
            }
        });
        return v;
    }

}
