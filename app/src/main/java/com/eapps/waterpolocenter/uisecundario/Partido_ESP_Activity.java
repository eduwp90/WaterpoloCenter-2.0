package com.eapps.waterpolocenter.uisecundario;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.eapps.waterpolocenter.R;
import com.eapps.waterpolocenter.clases.header_misligas_item;
import com.eapps.waterpolocenter.clases.partido_misligas_item;
import com.google.gson.Gson;

public class Partido_ESP_Activity extends AppCompatActivity {


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido);
        //Set up content
        TextView periodo = (TextView)findViewById(R.id.fecha_periodo);
        TextView local = (TextView)findViewById(R.id.localtext);
        TextView visitante = (TextView)findViewById(R.id.visitantetext);
        TextView resultado = (TextView)findViewById(R.id.resultado);
        ImageView escudol =(ImageView)findViewById(R.id.escudo1);
        ImageView escudov =(ImageView)findViewById(R.id.escudo2);


        //Set up action bar

        toolbar = (Toolbar) findViewById(R.id.toolbar_partido);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(false);

        ImageView flag =(ImageView)toolbar.findViewById(R.id.toolbar_flag);
        TextView liga = (TextView)toolbar.findViewById(R.id.toolbar_liga);
        TextView jornada = (TextView)toolbar.findViewById(R.id.toolbar_jornada);

        //Retrieve data from intent

        Intent intent = getIntent();
        String ligas = intent.getStringExtra("Jornada");
        String partido = intent.getStringExtra("Partido");
        final Gson gson1 = new Gson();
        final header_misligas_item ligainfo = gson1.fromJson(ligas, header_misligas_item.class);
        final Gson gson = new Gson();
        final partido_misligas_item partidoinfo = gson.fromJson(partido, partido_misligas_item.class);

        //Cargar info

        flag.setImageResource(ligainfo.getFlag());
        liga.setText(ligainfo.getLiga());
        jornada.setText(getResources().getString(R.string.session)+" "+ligainfo.getJornada());

        periodo.setText(partidoinfo.getPeriodo());
        local.setText(partidoinfo.getLocal());
        visitante.setText(partidoinfo.getVisitante());
        escudol.setImageResource(partidoinfo.getEscudol());
        escudov.setImageResource(partidoinfo.getEscudov());
        resultado.setText(partidoinfo.getResultado());






    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
