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

public class FragmentContact extends Fragment {

    String tranMethod="" ;
    String transID= "";
    String tranDate= "";
    String tranAmount= "";
    String tranStatus= "";
    String transTypes= "";

    View v;
    private RecyclerView myrecyclerview;
    public List<Contact> lstContact= new ArrayList<>();;
    Context context;


    public FragmentContact(Context context) {
        this.context= context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment, container, false);

        try{
            JSONObject obj= new JSONObject(laodJSONfromAssets());
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
        } catch (JSONException e) {
            e.printStackTrace();

        }
        myrecyclerview = v.findViewById(R.id.contact_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), filter("MATM2"));
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