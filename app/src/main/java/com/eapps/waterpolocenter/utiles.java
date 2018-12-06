package com.eapps.waterpolocenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.eapps.waterpolocenter.clases.ligas_dialogligas_item;
import com.eapps.waterpolocenter.uisecundario.activity_ligas_selector;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class utiles {

    private static ProgressDialog progressDialog1;
    public static int convertirescudo(String equipo) {
        int resid;

        if (equipo.toLowerCase().contains("matar")) {
            resid = R.drawable.mataro;
        }
        else if (equipo.toLowerCase().contains("barcelona")) {
            resid = R.drawable.barcelona;
        }
        else if (equipo.toLowerCase().contains("barceloneta")) {
            resid = R.drawable.barceloneta;
        }
        else if (equipo.toLowerCase().contains("canoe")) {
            resid = R.drawable.canoe;
        }
        else if (equipo.toLowerCase().contains("catalu")){
            resid = R.drawable.catalunya;
        }
        else if (equipo.toLowerCase().contains("concepci")){
            resid = R.drawable.concepcion;
        }
        else if (equipo.toLowerCase().contains("helios")){
            resid = R.drawable.helios;
        }
        else if (equipo.toLowerCase().contains("medite")){
            resid = R.drawable.mediterrani;
        }
        else if (equipo.toLowerCase().contains("navarra")){
            resid = R.drawable.navarra;
        }
        else if (equipo.toLowerCase().contains("sabadell")){
            resid = R.drawable.sabadell;
        }
        else if (equipo.toLowerCase().contains("andreu")){
            resid = R.drawable.santandreu;
        }
        else if (equipo.toLowerCase().contains("terrassa")){
            resid = R.drawable.terassa;
        }else if (equipo.toLowerCase().contains("echeyde")) {
            resid = R.drawable.echeyde;
        }
        else if (equipo.toLowerCase().contains("feliu")) {
            resid = R.drawable.santfeliu;
        }
        else if (equipo.toLowerCase().contains("poble nou")) {
            resid = R.drawable.poblenou;
        }
        else if (equipo.toLowerCase().contains("cantos")) {
            resid = R.drawable.trescantos;
        }
        else if (equipo.toLowerCase().contains("sevilla")) {
            resid = R.drawable.wpsevilla;
        }
        else if (equipo.toLowerCase().contains("metropole")) {
            resid = R.drawable.metropole;
        }
        else if (equipo.toLowerCase().contains("moscard")) {
            resid = R.drawable.moscardo;
        }
        else if (equipo.toLowerCase().contains("molins")) {
            resid = R.drawable.molins;
        }
        else if (equipo.toLowerCase().contains("askartza")) {
            resid = R.drawable.askartza;
        }
        else if (equipo.toLowerCase().contains("horta")) {
            resid = R.drawable.horta;
        }
        else if (equipo.toLowerCase().contains("latina")) {
            resid = R.drawable.latina;
        }
        else if (equipo.toLowerCase().contains("rubi")) {
            resid = R.drawable.rubi;
        }
        else if (equipo.toLowerCase().contains("premia")) {
            resid = R.drawable.premia;
        }
        else if (equipo.toLowerCase().contains("portugalete")) {
            resid = R.drawable.portugalete;
        }
        else if (equipo.toLowerCase().contains("turia")) {
            resid = R.drawable.turia;
        }
        else if (equipo.toLowerCase().contains("cuatro caminos")) {
            resid = R.drawable.cuatrocaminos;
        }
        else if (equipo.toLowerCase().contains("caballa")) {
            resid = R.drawable.caballa;
        }
        else if (equipo.toLowerCase().contains("hermanas")) {
            resid = R.drawable.doshermanas;
        }
        else if (equipo.toLowerCase().contains("palmas")) {
            resid = R.drawable.laspalmas;
        }
        else if (equipo.toLowerCase().contains("malaga")) {
            resid = R.drawable.wpmalaga;
        }
        else if (equipo.toLowerCase().contains("98")) {
            resid = R.drawable.nueveocho;
        }
        else if (equipo.toLowerCase().contains("hospital")) {
            resid = R.drawable.hospitalet;
        }
        else if (equipo.toLowerCase().contains("escuela")) {
            resid = R.drawable.ewz;
        }
        else if (equipo.toLowerCase().contains("covibar")) {
            resid = R.drawable.covibar;
        }
        else if (equipo.toLowerCase().contains("leioa")) {
            resid = R.drawable.leioa;
        }
        else if (equipo.toLowerCase().contains("badia")) {
            resid = R.drawable.badia;
        }
        else if (equipo.toLowerCase().contains("grano")) {
            resid = R.drawable.granollers;
        }
        else if (equipo.toLowerCase().contains("marbella")) {
            resid = R.drawable.marbella;
        }
        else if (equipo.toLowerCase().contains("elx")) {
            resid = R.drawable.elche1;
        }
        else if (equipo.toLowerCase().contains("bilb")) {
            resid = R.drawable.cdbilbao;
        }
        else  {
            resid = R.drawable.sinescudo;
        }
        return resid;

    }
    public static void toast(String string, Context mcontext) {
        Toast toast1 = Toast.makeText(mcontext,
                string, Toast.LENGTH_SHORT);

        toast1.show();
    }
    public static void saveCheckBox(String pos, boolean isChecked, Context mcontext){
        SharedPreferences prefs = mcontext.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(pos,isChecked);
        editor.commit();
    }


    public static boolean getCheckBoxState(String pos, Context mcontext){
        SharedPreferences prefs = mcontext.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        return prefs.getBoolean(pos, false);
    }

    public static String periodo(int per) {
        String periodoview = null;
        switch(per){
            case 0:
                periodoview =("Sin Comenzar");

                break;
            case 1:
                periodoview=("Periodo "+per);

                break;
            case 2:
                periodoview=("Periodo "+per);

                break;
            case 3:
                periodoview=("Periodo "+per);

                break;
            case 4:
                periodoview=("Periodo "+per);

                break;
            case 5:
                periodoview=("Finalizado");

                break;
            case 6:
                periodoview=("Finalizado");

                break;

        }
        return periodoview;
    }

    public static int periodoaint(String per) {
        int periodoview = 0;
        if(per.equals("Periodo 1")){
            periodoview=1;
        }else if(per.equals("Periodo 2")){
            periodoview=2;
        }else if(per.equals("Periodo 3")){
            periodoview=3;
        }else if(per.equals("Periodo 4")){
            periodoview=4;
        }else if(per.equals("Finalizado")){
            periodoview=5;
        }
        return periodoview;
    }
    public static void pdialog(Context c,String msj){
        progressDialog1 = ProgressDialog.show(c, "",
                msj, true);

    }
    public static void pdialogdismiss(Context c){
        progressDialog1.dismiss();

    }

    public static String convertirnombre(String equipo) {
        String resid = null;

        if (equipo.toLowerCase().contains("matar")) {
            resid = "C.N. Mataró";
        }
        else if (equipo.toLowerCase().contains("barcelona")) {
            resid = "C.N. Barcelona";
        }
        else if (equipo.toLowerCase().contains("barceloneta")) {
            resid = "At. Barceloneta";
        }
        else if (equipo.toLowerCase().contains("canoe")) {
            resid = "Real Canoe N.C.";
        }
        else if (equipo.toLowerCase().contains("catalu")){
            resid = "C.N. Catalunya";
        }
        else if (equipo.toLowerCase().contains("concepci")){
            resid = "A.R. Concepción";
        }
        else if (equipo.toLowerCase().contains("helios")){
            resid = "C.N. Helios";
        }
        else if (equipo.toLowerCase().contains("medite")){
            resid = "C.E. Mediterrani";
        }
        else if (equipo.toLowerCase().contains("navarra")){
            resid = "C.W. Navarra";
        }
        else if (equipo.toLowerCase().contains("sabadell")){
            resid = "C.N. Sabadell";
        }
        else if (equipo.toLowerCase().contains("andreu")){
            resid = "C.N. Sant Andreu";
        }
        else if (equipo.toLowerCase().contains("terrassa")){
            resid = "C.N. Terrassa";
        }else if (equipo.toLowerCase().contains("echeyde")) {
            resid = "Tenerife Echeyde";
        }
        else if (equipo.toLowerCase().contains("feliu")) {
            resid = "C.N. Sant Feliu";
        }
        else if (equipo.toLowerCase().contains("poble nou")) {
            resid = "C.N. Poble Nou";
        }
        else if (equipo.toLowerCase().contains("cantos")) {
            resid = "C.N. Tres Cantos";
        }
        else if (equipo.toLowerCase().contains("sevilla")) {
            resid = "C.W. Sevilla";
        }
        else if (equipo.toLowerCase().contains("metropole")) {
            resid = "C.N. Metropole";
        }
        else if (equipo.toLowerCase().contains("moscard")) {
            resid = "C.N. Moscardó";
        }
        else if (equipo.toLowerCase().contains("molins")) {
            resid = "C.N. Molins de Rei";
        }
        else if (equipo.toLowerCase().contains("askartza")) {
            resid = "Askartza-Leioa";
        }
        else if (equipo.toLowerCase().contains("horta")) {
            resid = "U.E. Horta";
        }
        else if (equipo.toLowerCase().contains("latina")) {
            resid = "C.N. La Latina";
        }
        else if (equipo.toLowerCase().contains("rubi")) {
            resid = "C.N. Rubi";
        }else if (equipo.toLowerCase().contains("premia")) {
            resid = "C.N. Premia";
        }
        else if (equipo.toLowerCase().contains("portugalete")) {
            resid = "D.N. Portugalete";
        }
        else if (equipo.toLowerCase().contains("turia")) {
            resid = "C.D. WP Turia";
        }
        else if (equipo.toLowerCase().contains("cuatro caminos")) {
            resid = "C.N. Cuatro Caminos";
        }
        else if (equipo.toLowerCase().contains("caballa")) {
            resid = "C.N. Caballa";
        }
        else if (equipo.toLowerCase().contains("hermanas")) {
            resid = "C.W. Dos Hermanas";
        }
        else if (equipo.toLowerCase().contains("palmas")) {
            resid = "C.N. Las Palmas";
        }
        else if (equipo.toLowerCase().contains("malaga")) {
            resid = "C.D. WP Malaga";
        }
        else if (equipo.toLowerCase().contains("98")) {
            resid = "W.P. 98-02";
        }

        else if (equipo.toLowerCase().contains("hospital")) {
            resid = "C.N. Hospitalet";
        }
        else if (equipo.toLowerCase().contains("escuela")) {
            resid = "Escuela WP Zaragoza";
        }
        else if (equipo.toLowerCase().contains("covibar")) {
            resid = "C.D. Covibar";
        }
        else if (equipo.toLowerCase().contains("leioa")) {
            resid = "Leioa ASK";
        }
        else if (equipo.toLowerCase().contains("badia")) {
            resid = "C.N. Badia";
        }
        else if (equipo.toLowerCase().contains("grano")) {
            resid = "C.N. Granollers";
        }
        else if (equipo.toLowerCase().contains("marbella")) {
            resid = "C.D. WP Marbella";
        }
        else if (equipo.toLowerCase().contains("elx")) {
            resid = "C.W. Elche";
        }
        else if (equipo.toLowerCase().contains("bilb")) {
            resid = "C.D. Bilbao";
        }
        else{
            resid=equipo;
        }
        return resid;

    }
    public static String convertiraid(String equipo){
        String abc=null;
        if (equipo.toLowerCase().contains("matar")) {
            abc = "MAT";
        }
        else if (equipo.toLowerCase().contains("barceloneta")) {
            abc = "ATB";
        }
        else if (equipo.toLowerCase().contains("catalunya")) {
            abc = "CAT";
        }
        else if (equipo.toLowerCase().contains("barcelona")) {
            abc = "BAR";
        }
        else if (equipo.toLowerCase().contains("canoe")) {
            abc = "CAN";
        }
        else if (equipo.toLowerCase().contains("sabade")) {
            abc = "SAB";
        }
        else if (equipo.toLowerCase().contains("concep")) {
            abc = "CON";
        }
        else if (equipo.toLowerCase().contains("andreu")) {
            abc = "SAN";
        }
        else if (equipo.toLowerCase().contains("terrassa")) {
            abc = "TER";
        }
        else if (equipo.toLowerCase().contains("mediter")) {
            abc = "MED";
        }
        else if (equipo.toLowerCase().contains("navarra")) {
            abc = "NAV";
        }
        else if (equipo.toLowerCase().contains("helios")) {
            abc = "HEL";
        }
        else if (equipo.toLowerCase().contains("echeyde")) {
            abc = "ECH";
        }
        else if (equipo.toLowerCase().contains("feliu")) {
            abc = "SFE";
        }
        else if (equipo.toLowerCase().contains("poble nou")) {
            abc = "PNE";
        }
        else if (equipo.toLowerCase().contains("cantos")) {
            abc = "TCA";
        }
        else if (equipo.toLowerCase().contains("sevilla")) {
            abc = "SEV";
        }
        else if (equipo.toLowerCase().contains("metropole")) {
            abc = "MET";
        }
        else if (equipo.toLowerCase().contains("moscard")) {
            abc = "MOS";
        }
        else if (equipo.toLowerCase().contains("molins")) {
            abc = "MDR";
        }
        else if (equipo.toLowerCase().contains("askartza")) {
            abc = "ASK";
        }
        else if (equipo.toLowerCase().contains("horta")) {
            abc = "HOR";
        }
        else if (equipo.toLowerCase().contains("latina")) {
            abc = "LAT";
        }
        else if (equipo.toLowerCase().contains("rubi")) {
            abc = "RUB";
        }else if (equipo.toLowerCase().contains("premia")) {
            abc = "PRE";
        }
        else if (equipo.toLowerCase().contains("portugalete")) {
            abc = "POR";
        }
        else if (equipo.toLowerCase().contains("turia")) {
            abc = "TUR";
        }
        else if (equipo.toLowerCase().contains("cuatro caminos")) {
            abc = "CCA";
        }
        else if (equipo.toLowerCase().contains("caballa")) {
            abc = "CAB";
        }
        else if (equipo.toLowerCase().contains("hermanas")) {
            abc = "DHM";
        }
        else if (equipo.toLowerCase().contains("palmas")) {
            abc = "PAL";
        }
        else if (equipo.toLowerCase().contains("malaga")) {
            abc = "MAL";
        }
        else if (equipo.toLowerCase().contains("98")) {
            abc = "WP982";
        }
        else if (equipo.toLowerCase().contains("hospital")) {
            abc = "HOS";
        }
        else if (equipo.toLowerCase().contains("escuela")) {
            abc = "EWZ";
        }
        else if (equipo.toLowerCase().contains("covibar")) {
            abc = "COV";
        }
        else if (equipo.toLowerCase().contains("leioa")) {
            abc = "LEI";
        }
        else if (equipo.toLowerCase().contains("badia")) {
            abc = "BAD";
        }
        else if (equipo.toLowerCase().contains("grano")) {
            abc = "GLL";
        }
        else if (equipo.toLowerCase().contains("marbella")) {
            abc = "MAR";
        }
        else if (equipo.toLowerCase().contains("elx")) {
            abc = "ELX";
        }
        else if (equipo.toLowerCase().contains("bilb")) {
            abc = "BIL";
        }

        return abc;

    }
    public static void saveData(String pos, String isChecked, Context mcontext){
        SharedPreferences prefs = mcontext.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(pos,isChecked);
        editor.commit();
    }


    public static String getData(String pos, Context mcontext){
        SharedPreferences prefs = mcontext.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        return prefs.getString(pos, null);
    }
    public static void saveInt(String pos, int jor, Context mcontext){
        SharedPreferences prefs = mcontext.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(pos, jor);
        editor.commit();
    }


    public static int getInt(String pos, Context mcontext){
        SharedPreferences prefs = mcontext.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        return prefs.getInt(pos, 2);
    }

    public static void saveArray(String pos, ArrayList jor, Context mcontext){
        SharedPreferences prefs = mcontext.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(jor);
        Log.d("SAVE",json);
        editor.putString(pos, json);
        editor.commit();
    }


    public static ArrayList getLigasArray(String pos, Context mcontext){
        SharedPreferences prefs = mcontext.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        String retorno =prefs.getString(pos, null);

        ArrayList<Boolean> target2;
        if (retorno!=null){
            Log.d("RETORNO",retorno);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<ligas_dialogligas_item>>() {}.getType();
            target2 = gson.fromJson(retorno, listType);
            Log.d("RETRIEVE",retorno);
        }else{
            target2=null;
        }

        return target2 ;
    }


}

