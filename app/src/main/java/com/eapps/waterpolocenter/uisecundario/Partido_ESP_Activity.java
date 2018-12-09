package com.eapps.waterpolocenter.uisecundario;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.eapps.waterpolocenter.R;
import com.eapps.waterpolocenter.clases.header_misligas_item;
import com.eapps.waterpolocenter.clases.jugador_partido_item;
import com.eapps.waterpolocenter.clases.partido_misligas_item;
import com.eapps.waterpolocenter.libary.NonScrollListView;
import com.eapps.waterpolocenter.utiles;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.parse.ParsePush;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Partido_ESP_Activity extends AppCompatActivity {


    partido_misligas_item partidoinfo;
    Boolean notificaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido);
        //Set up content
        TextView periodo = findViewById(R.id.fecha_periodo);
        TextView local = findViewById(R.id.localtext);
        TextView visitante = findViewById(R.id.visitantetext);
        TextView resultado = findViewById(R.id.resultado);
        ImageView escudol = findViewById(R.id.escudo1);
        ImageView escudov = findViewById(R.id.escudo2);
        ListView rv_goles = (NonScrollListView) findViewById(R.id.partido_list_goleadores);
        ListView rv_exp = (NonScrollListView) findViewById(R.id.partido_list_exp);


        //Set up action bar

        Toolbar toolbar = findViewById(R.id.toolbar_partido);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(false);

        ImageView flag = toolbar.findViewById(R.id.toolbar_flag);
        TextView liga = toolbar.findViewById(R.id.toolbar_liga);
        TextView jornada = toolbar.findViewById(R.id.toolbar_jornada);



        //Retrieve data from intent

        Intent intent = getIntent();
        String ligas = intent.getStringExtra("Liga");
        String partido = intent.getStringExtra("Partido");
        final Gson gson1 = new Gson();
        final header_misligas_item ligainfo = gson1.fromJson(ligas, header_misligas_item.class);
        final Gson gson = new Gson();
        partidoinfo = gson.fromJson(partido, partido_misligas_item.class);
        ArrayList<jugador_partido_item> gol_list = new ArrayList<>();
        ArrayList<jugador_partido_item> exp_list = new ArrayList<>();


        //Cargar info

        flag.setImageResource(ligainfo.getFlag());
        liga.setText(ligainfo.getLiga());
        jornada.setText(getResources().getString(R.string.session)+" "+partidoinfo.getJornada());

        periodo.setText(partidoinfo.getPeriodo());
        local.setText(partidoinfo.getLocal());
        visitante.setText(partidoinfo.getVisitante());
        escudol.setImageResource(partidoinfo.getEscudol());
        escudov.setImageResource(partidoinfo.getEscudov());
        resultado.setText(partidoinfo.getResultado());



        JsonParser jsonParser = new JsonParser();

        JsonArray jlocal =jsonParser.parse(partidoinfo.getJugadoresl()).getAsJsonArray();
        JsonArray jvisitante =jsonParser.parse(partidoinfo.getJugadoresv()).getAsJsonArray();

        for (JsonElement j : jlocal){

            JsonObject jugador = jsonParser.parse(j.toString()).getAsJsonObject();
            int GT = jugador.get("GT").getAsInt();
            String nombre = jugador.get("nombre").getAsString();
            int EXP = jugador.get("EXP").getAsInt();
            if(GT != 0){
                gol_list.add(new jugador_partido_item(nombre, GT, partidoinfo.getEscudol()));
            }
            if(EXP != 0){
                exp_list.add(new jugador_partido_item(nombre, EXP, partidoinfo.getEscudol()));
            }



        }
        for (JsonElement j : jvisitante){
            JsonObject jugador = j.getAsJsonObject();
            int GT = jugador.get("GT").getAsInt();
            String nombre = jugador.get("nombre").getAsString();
            int EXP = jugador.get("EXP").getAsInt();

            if(GT != 0){
                gol_list.add(new jugador_partido_item(nombre, GT, partidoinfo.getEscudov()));
            }
            if(EXP != 0){
                exp_list.add(new jugador_partido_item(nombre, EXP, partidoinfo.getEscudov()));
            }
        }

        Collections.sort(gol_list, new Comparator<jugador_partido_item>() {
            @Override
            public int compare(jugador_partido_item z1, jugador_partido_item z2) {
                if (z1.getDato() < z2.getDato())
                    return 1;
                if (z1.getDato() > z2.getDato())
                    return -1;
                return 0;
            }
        });

        Collections.sort(exp_list, new Comparator<jugador_partido_item>() {
            @Override
            public int compare(jugador_partido_item z1, jugador_partido_item z2) {
                if (z1.getDato() < z2.getDato())
                    return 1;
                if (z1.getDato() > z2.getDato())
                    return -1;
                return 0;
            }
        });

        CustomAdapter adapter1= new CustomAdapter(gol_list,getApplicationContext());
        CustomAdapter adapter2= new CustomAdapter(exp_list,getApplicationContext());

        rv_goles.setAdapter(adapter1);
        rv_exp.setAdapter(adapter2);




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.partido_menu, menu);
        notificaciones = utiles.getCheckBoxState(partidoinfo.getId(),getApplicationContext());
        if(!notificaciones){
            menu.getItem(0).setIcon(R.drawable.ic_notifications_off);
        } else {
            menu.getItem(0).setIcon(R.drawable.ic_notifications_active);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            case R.id.action_informar:
                Log.d("INFORMAR","OK");
                return true;
            case R.id.notificaciones:
                if(!notificaciones){
                    item.setIcon(R.drawable.ic_notifications_active);
                    Log.d("NOTIFICACIONES","ON");
                    utiles.saveCheckBox(partidoinfo.getId(),true,getApplicationContext());
                    notificaciones=true;
                    utiles.toast(getString(R.string.notifications_ON),getApplicationContext());
                    ParsePush.subscribeInBackground(partidoinfo.getId());
                } else {
                    item.setIcon(R.drawable.ic_notifications_off);
                    Log.d("NOTIFICACIONES","Off");
                    utiles.saveCheckBox(partidoinfo.getId(),false,getApplicationContext());
                    notificaciones = false;
                    utiles.toast(getString(R.string.notifications_OFF),getApplicationContext());
                    ParsePush.unsubscribeInBackground(partidoinfo.getId());
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

class CustomAdapter extends ArrayAdapter<jugador_partido_item> {

    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtDatos;

        ImageView escudo;
    }

    public CustomAdapter(ArrayList<jugador_partido_item> data, Context context) {
        super(context, R.layout.rv_jugador_partido, data);
        this.mContext=context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        jugador_partido_item dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.rv_jugador_partido, parent, false);
            viewHolder.txtName = convertView.findViewById(R.id.j_nombre);
            viewHolder.txtDatos = convertView.findViewById(R.id.j_num);
            viewHolder.escudo = convertView.findViewById(R.id.j_escudo);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        viewHolder.txtName.setText(dataModel.getNombre());
        viewHolder.txtDatos.setText(""+dataModel.getDato());
        viewHolder.escudo.setImageResource(dataModel.getFlag());

        // Return the completed view to render on screen
        return convertView;
    }
}

