package com.eapps.waterpolocenter.dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.eapps.waterpolocenter.R;
import com.eapps.waterpolocenter.utiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class dialog_ligas_fragment extends DialogFragment {
    ListView lv;
    ArrayList<Boolean> ligaselegidas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_ligas, container,
                false);
        getDialog().setTitle(R.string.dialogtitle);
        ligaselegidas = utiles.getArray("arrayid", getActivity().getBaseContext());
        if (ligaselegidas!=null){
            for(int i = 0; i < ligaselegidas.size(); i++) {
                Log.d("lista int RETRIEVED",ligaselegidas.get(i).toString());
            }

        }else{
            ligaselegidas  = new ArrayList<Boolean>(Collections.nCopies(5,false));
            Log.d("FIRST TIME ARRAY","SI");
        }
        String[] ligas = getResources().getStringArray(R.array.ligas);
        ArrayList<String> stringList = new ArrayList<String>(Arrays.asList(ligas));
        // set the custom dialog components - text, image and button
        lv = (ListView)rootView.findViewById(R.id.dialog_listView);
        Button btn = (Button)rootView.findViewById(R.id.button);
        dialog_ligas_adapter adapter = new dialog_ligas_adapter(getActivity(),stringList);
        lv.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < ligaselegidas.size(); i++) {
                    Log.d("lista int",ligaselegidas.get(i).toString());
                }
                utiles.saveArray("arrayid",ligaselegidas,getActivity().getBaseContext());

                getDialog().dismiss();
            }
        });



        return rootView;
    }


    private class dialog_ligas_adapter extends ArrayAdapter<String> {
        public dialog_ligas_adapter(Context context, ArrayList<String> ligas) {
            super(context, 0, ligas);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            String actual = getItem(position);


            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_ligas_row, parent, false);
            }
            // Lookup view for data population
            TextView ligas = (TextView) convertView.findViewById(R.id.textView);
            ImageView flag = (ImageView) convertView.findViewById(R.id.flag);
            CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);
            // Populate the data into the template view using the data object
            ligas.setText(actual);
            cb.setChecked(utiles.getCheckBoxState(String.valueOf(position), getContext()));
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((CheckBox) v).isChecked()){
                        utiles.saveCheckBox(String.valueOf(position),true, getContext());
                        Log.d("CB", String.valueOf(position));
                        ligaselegidas.set(position, true);
                    }else{
                        utiles.saveCheckBox(String.valueOf(position),false, getContext());
                        Log.d("CBno", String.valueOf(position));
                        ligaselegidas.set(position, false);
                    }
                }
            });

            // Return the completed view to render on screen
            return convertView;
        }
    }
}

