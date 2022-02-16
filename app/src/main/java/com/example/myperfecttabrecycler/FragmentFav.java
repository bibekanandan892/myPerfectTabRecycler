package com.example.myperfecttabrecycler;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FragmentFav extends Fragment {

    String tranMethod="" ;
    String transID= "";
    String tranDate= "";
    String tranAmount= "";
    String tranStatus= "";
    String transTypes= "";

    View v;
    public List<Contact> lstContact = new ArrayList<>();;
    Context context;
    String json;
    public FragmentFav(Context context,String json) {
        this.context = context;
        this.json=json;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment, container, false);



        try{
            JSONObject obj= new JSONObject(laodJSONfromAssets());
            if(lstContact.isEmpty()){
                JSONArray dataArray= obj.getJSONArray("data");
                for(int i=0; i<dataArray.length();i++){
                    JSONObject transdetails= dataArray.getJSONObject(i);
                    tranMethod= transdetails.getString("tranMethod");

                    transID=transdetails.getString("tranID");
                    tranDate= transdetails.getString("tranDate");

                    tranAmount= transdetails.getString("tranAmount");

                    tranStatus= transdetails.getString("tranStatus");
                    transTypes= transdetails.getString("tranType");

                    lstContact.add(new Contact(tranMethod,transID,R.drawable.ic_group));
            }

            }
        } catch (JSONException e) {
            e.printStackTrace();

        }

//        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group));
//        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call));
//        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star));
//        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group));
//        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call));
//        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star));
//        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group));
//        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call));
//        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star));
//        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group));
//        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call));
//        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star));
//        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group));
//        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call));
//        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star));
//        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group));
//        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call));
//        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group));
//        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call));
//        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star));
//        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star));
//        lstContact.add(new Contact("Aaron Jones", "(111) 251236211", R.drawable.ic_group));
//        lstContact.add(new Contact("chan Jones", "(02) 251236211", R.drawable.ic_call));
//        lstContact.add(new Contact("park Jones", "(031) 251236211", R.drawable.ic_star));
//

        RecyclerView myrecyclerview = v.findViewById(R.id.contact_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), filter("AEPS2"));
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);


        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    private String laodJSONfromAssets() {
        String json =null;
        try {
            InputStream is = context.getAssets().open("trans.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json= new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }


    private List filter(String text) {
        ArrayList<Contact> filteredlist = new ArrayList<>();
        for (Contact item : lstContact) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        return filteredlist;
    }
}
