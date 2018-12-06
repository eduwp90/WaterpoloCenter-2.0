package com.eapps.waterpolocenter.uiprincipal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eapps.waterpolocenter.R;
import com.eapps.waterpolocenter.clases.header_misligas_item;
import com.eapps.waterpolocenter.clases.ligas_dialogligas_item;
import com.eapps.waterpolocenter.clases.partido_misligas_item;

import com.eapps.waterpolocenter.uisecundario.Partido_ESP_Activity;
import com.eapps.waterpolocenter.uisecundario.activity_ligas_selector;
import com.eapps.waterpolocenter.utiles;
import com.github.fabtransitionactivity.SheetLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Fragment_misligas extends Fragment implements SheetLayout.OnFabAnimationEndListener {

    FloatingActionButton fab;
    final int CHILD_SPECIFIED =1;
    SheetLayout mSheetLayout;
    List<ligas_dialogligas_item> list;
    List<String> nombreligas, jornadasligas, urlLigas;
    ArrayList<Object> rv_lista;
    private SwipeRefreshLayout swipeRefreshLayout;
    MisLigas_RecyclerViewAdapter adapter;
    Gson g = new Gson();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cargar iconos menu



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_misligas, container, false);
        // Inflate the layout for this fragment
        final RecyclerView rv = (RecyclerView) fragmentView.findViewById(R.id.recycler_view);
        mSheetLayout = (SheetLayout) fragmentView.findViewById(R.id.bottom_sheet);
        //Find the floating button
        fab = (FloatingActionButton) fragmentView.findViewById(R.id.fab);
        mSheetLayout.setFab(fab);
        mSheetLayout.setFabAnimationEndListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.accent,
                R.color.primary,
                R.color.primary);
        nombreligas = Arrays.asList(getResources().getStringArray(R.array.ligas));
        jornadasligas = Arrays.asList(getResources().getStringArray(R.array.Jornadas));
        urlLigas = Arrays.asList(getResources().getStringArray(R.array.LigasShort));
        list = utiles.getLigasArray("arrayid", getActivity());
        rv_lista = new ArrayList<>();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSheetLayout.expandFab();

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Esto se ejecuta cada vez que se realiza el gesto
                actualizar();
            }
        });




        // Create adapter passing in the sample user data
        adapter = new MisLigas_RecyclerViewAdapter(rv_lista);
        //Handling onclick
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicionclick = rv.getChildLayoutPosition(v);
                int jornadaclick=posicionclick;
                if (rv_lista.get(posicionclick) instanceof partido_misligas_item) {
                    Log.d("CLICK","PARTIDO");

                    while (rv_lista.get(jornadaclick)instanceof partido_misligas_item){
                        jornadaclick=jornadaclick-1;
                    }
                    final Gson gson = new Gson();
                    final String partidoJSON = gson.toJson(rv_lista.get(posicionclick));
                    final Gson gsonj = new Gson();
                    final String jornadaJSON = gsonj.toJson(rv_lista.get(jornadaclick));

                    Intent intent = new Intent(getActivity(), Partido_ESP_Activity.class);
                    intent.putExtra("Partido", partidoJSON);
                    intent.putExtra("Liga", jornadaJSON);
                    Pair<View, String> p1 = Pair.create(v.findViewById(R.id.escudol), "escudo1");
                    //Pair<View, String> p2 = Pair.create((View)v.findViewById(R.id.resultado), "resultado");
                    Pair<View, String> p3 = Pair.create(v.findViewById(R.id.escudov), "escudo2");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(getActivity(), p1, p3);
                    getActivity().startActivity(intent, options.toBundle());
                } else if (rv_lista.get(posicionclick) instanceof header_misligas_item) {
                    Log.d("CLICK","HEADER");
                }

            }
        });
        // Attach the adapter to the recyclerview to populate items
        rv.setAdapter(adapter);
        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(null);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 0)
                    fab.hide();
                else if (dy < 0)
                    fab.show();
            }
        });

        actualizar();

        return fragmentView;
    }

    private void actualizar() {
        if (list!=null){
            ActualizarAsincrona act = new ActualizarAsincrona();
            act.execute();
        }else{
            utiles.toast(getResources().getString(R.string.sinligas),getActivity());
            swipeRefreshLayout.setRefreshing(false);
        }



    }

    @Override
    public void onFabAnimationEnd() {
        Intent i = new Intent(getActivity(),
                activity_ligas_selector.class);
        startActivityForResult(i, CHILD_SPECIFIED);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //RECIBIMOS DATOS DE LAS LIGAS ELEGIDAS
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CHILD_SPECIFIED){
            if(resultCode == getActivity().RESULT_OK) {
                Log.d("ligas elegidas","aki ");
                Gson gson = new Gson();
                String strEditText = data.getStringExtra("arrayid");
                list = Arrays.asList(new Gson().fromJson(strEditText, ligas_dialogligas_item[].class));
                Log.d("ligas elegidas",list.get(0).getUrl().toString());
            }
            mSheetLayout.contractFab();
            actualizar();


        }
    }

    private class ActualizarAsincrona extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            //Preparar constraints para queries
            JsonObject jornadas_activas = new JsonParser().parse(utiles.getData("jornadas_activasJSON",getActivity().getBaseContext())).getAsJsonObject();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, -3);
            Date fecha1 = calendar.getTime();
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 4);
            Date fecha2 = calendar.getTime();

            //Set the queries
            ParseQuery<ParseObject> dhm = ParseQuery.getQuery("T1819");
            dhm.whereEqualTo("liga", "DHM");
            dhm.whereEqualTo("jornada", Integer.parseInt(jornadas_activas.get("DHM").toString()));

            ParseQuery<ParseObject> n_dhm = ParseQuery.getQuery("T1819");
            n_dhm.whereGreaterThanOrEqualTo("fhora", fecha1);
            n_dhm.whereLessThanOrEqualTo("fhora", fecha2);
            n_dhm.whereEqualTo("liga", "DHM");
            n_dhm.whereNotEqualTo("jornada", Integer.parseInt(jornadas_activas.get("DHM").toString()));

            ParseQuery<ParseObject> dhf = ParseQuery.getQuery("T1819");
            dhf.whereEqualTo("liga", "DHF");
            dhf.whereEqualTo("jornada", Integer.parseInt(jornadas_activas.get("DHF").toString()));

            ParseQuery<ParseObject> n_dhf = ParseQuery.getQuery("T1819");
            n_dhf.whereGreaterThanOrEqualTo("fhora", fecha1);
            n_dhf.whereLessThanOrEqualTo("fhora", fecha2);
            n_dhf.whereEqualTo("liga", "DHF");
            n_dhf.whereNotEqualTo("jornada", Integer.parseInt(jornadas_activas.get("DHF").toString()));

            ParseQuery<ParseObject> pdm = ParseQuery.getQuery("T1819");
            pdm.whereEqualTo("liga", "PDM");
            pdm.whereEqualTo("jornada", Integer.parseInt(jornadas_activas.get("PDM").toString()));

            ParseQuery<ParseObject> n_pdm = ParseQuery.getQuery("T1819");
            n_pdm.whereGreaterThanOrEqualTo("fhora", fecha1);
            n_pdm.whereLessThanOrEqualTo("fhora", fecha2);
            n_pdm.whereEqualTo("liga", "PDM");
            n_pdm.whereNotEqualTo("jornada", Integer.parseInt(jornadas_activas.get("PDM").toString()));

            ParseQuery<ParseObject> pdf = ParseQuery.getQuery("T1819");
            pdf.whereEqualTo("liga", "PDF");
            pdf.whereEqualTo("jornada", Integer.parseInt(jornadas_activas.get("PDF").toString()));

            ParseQuery<ParseObject> n_pdf = ParseQuery.getQuery("T1819");
            n_pdf.whereGreaterThanOrEqualTo("fhora", fecha1);
            n_pdf.whereLessThanOrEqualTo("fhora", fecha2);
            n_pdf.whereEqualTo("liga", "PDF");
            n_pdf.whereNotEqualTo("jornada", Integer.parseInt(jornadas_activas.get("PDF").toString()));

            ParseQuery<ParseObject> sdm = ParseQuery.getQuery("T1819");
            sdm.whereEqualTo("liga", "SDM");
            sdm.whereEqualTo("jornada", Integer.parseInt(jornadas_activas.get("SDM").toString()));

            ParseQuery<ParseObject> n_sdm = ParseQuery.getQuery("T1819");
            n_sdm.whereGreaterThanOrEqualTo("fhora", fecha1);
            n_sdm.whereLessThanOrEqualTo("fhora", fecha2);
            n_sdm.whereEqualTo("liga", "SDM");
            n_sdm.whereNotEqualTo("jornada", Integer.parseInt(jornadas_activas.get("SDM").toString()));

            List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
            queries.add(dhm);
            queries.add(n_dhm);
            queries.add(dhf);
            queries.add(n_dhf);
            queries.add(pdm);
            queries.add(n_pdm);
            queries.add(pdf);
            queries.add(n_pdf);
            queries.add(sdm);
            queries.add(n_sdm);

            ParseQuery<ParseObject> mainQuery = ParseQuery.or(queries);
            mainQuery.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ONLY);

            try {
                List<ParseObject> activosList = mainQuery.find();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecked()==true){
                        rv_lista.add(new header_misligas_item(list.get(i).getLiga(), "",R.drawable.flag_spain));
                        String liga_iter = list.get(i).getUrl();
                        for (int j=0; j<activosList.size(); j++){
                            String partidoparseliga= activosList.get(j).getString("liga");
                            if(liga_iter.equals(partidoparseliga)){
                                ParseObject partidoparse =activosList.get(j) ;
                                String resultado= String.valueOf(partidoparse.getInt("goll"))+" - "+String.valueOf(partidoparse.getInt("golv"));
                                String local = partidoparse.getString("local");
                                String visitante=partidoparse.getString("visitante");
                                int escudol = utiles.convertirescudo(local);
                                int escudov = utiles.convertirescudo(visitante);
                                int jornada = partidoparse.getInt("jornada");
                                rv_lista.add(new partido_misligas_item(local,visitante,partidoparse.getString("fhora"),resultado,escudol,escudov,jornada,partidoparse.getJSONArray("goleadoresl"), partidoparse.getJSONArray("goleadoresv")));
                            }

                        }


                        publishProgress();
                    }
                }

            }catch(ParseException e){
                Log.d("score", "Error: " + e.getMessage());
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPreExecute() {
            fab.setVisibility(View.INVISIBLE);
            swipeRefreshLayout.setRefreshing(true);
            rv_lista.clear();

        }

        @Override
        protected void onPostExecute(Void result) {
            fab.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            utiles.toast("Tarea cancelada",getActivity());
        }
    }


    static class ViewHolder_header extends RecyclerView.ViewHolder {

        private TextView competicion, jornadas;
        private ImageView flag;

        public ViewHolder_header(View v) {
            super(v);
            competicion = (TextView) v.findViewById(R.id.liga);
            jornadas = (TextView) v.findViewById(R.id.jornada);
            flag =(ImageView)v.findViewById(R.id.flag);
        }

        public TextView getCompeticion() {
            return competicion;
        }

        public void setCompeticion(TextView competicion) {
            this.competicion = competicion;
        }

        public TextView getJornadas() {
            return jornadas;
        }

        public void setJornadas(TextView jornadas) {
            this.jornadas = jornadas;
        }
        public ImageView getFlag() {
            return flag;
        }

        public void setFlag(ImageView flag) {
            this.flag = flag;
        }
    }
    static class ViewHolder_partido extends RecyclerView.ViewHolder {

        private TextView local, visitante, resultado, periodo;
        private ImageView escudolocal, escudovisitante;
        LinearLayout rv;

        public ViewHolder_partido(View v) {
            super(v);
            local = (TextView) v.findViewById(R.id.local);
            visitante = (TextView) v.findViewById(R.id.visitante);
            resultado = (TextView) v.findViewById(R.id.resultado);
            periodo = (TextView) v.findViewById(R.id.periodo);
            escudolocal =(ImageView)v.findViewById(R.id.escudol);
            escudovisitante =(ImageView)v.findViewById(R.id.escudov);
            rv = (LinearLayout)v.findViewById(R.id.rv_layout);
            v.setClickable(true);
        }

        public TextView getLocal() {
            return local;
        }

        public void setLocal(TextView local) {
            this.local = local;
        }
        public TextView getVisitante() {
            return visitante;
        }
        public void setVisitante(TextView visitante) {
            this.visitante = visitante;
        }

        public TextView getResultado() {
            return resultado;
        }

        public void setResultado(TextView resultado) {
            this.resultado = resultado;
        }
        public TextView getPeriodo() {
            return periodo;
        }

        public void setPeriodo(TextView periodo) {
            this.periodo = periodo;
        }
        public ImageView getEscudolocal() {
            return escudolocal;
        }

        public void setEscudolocal(ImageView escudolocal) {
            this.escudolocal = escudolocal;
        }
        public ImageView getEscudovisitante() {
            return escudovisitante;
        }

        public void setEscudovisitante(ImageView escudovisitante) {
            this.escudovisitante = escudovisitante;
        }
        public LinearLayout getRv(){
            return rv;
        }
    }

    public static class RecyclerViewSimpleTextViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public RecyclerViewSimpleTextViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }


    //SIN FINALIZAR
    public class MisLigas_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

        // The items to display in your RecyclerView
        private List<Object> items;


        private final int HEADER = 0, PARTIDO = 1;

        // Provide a suitable constructor (depends on the kind of dataset)
        public MisLigas_RecyclerViewAdapter(List<Object> items) {
            this.items = items;
        }

        private View.OnClickListener listener;

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return this.items == null ? 0 :this.items.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (items.get(position) instanceof partido_misligas_item) {
                return PARTIDO;
            } else if (items.get(position) instanceof header_misligas_item) {
                return HEADER;
            }
            return -1;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            RecyclerView.ViewHolder viewHolder;
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            switch (viewType) {
                case PARTIDO:
                    View v1 = inflater.inflate(R.layout.rv_misligas_partido, viewGroup, false);
                    v1.setOnClickListener(this);
                    viewHolder = new ViewHolder_partido(v1);
                    break;
                case HEADER:
                    View v2 = inflater.inflate(R.layout.rv_misligas_header, viewGroup, false);
                    v2.setOnClickListener(this);
                    viewHolder = new ViewHolder_header(v2);
                    break;
                default:
                    View v = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                    viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                    break;
            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

            switch (viewHolder.getItemViewType()) {
                case HEADER:
                    ViewHolder_header vh1 = (ViewHolder_header) viewHolder;
                    configureViewHolderHeader(vh1, position);
                    break;
                case PARTIDO:
                    ViewHolder_partido vh2 = (ViewHolder_partido) viewHolder;
                    configureViewHolderPartido(vh2, position);
                    break;
                default:
                    RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
                    configureDefaultViewHolder(vh, position);
                    break;
            }
        }

        public void setOnClickListener(View.OnClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            if(listener != null)
                listener.onClick(view);

        }

        private void configureDefaultViewHolder(RecyclerViewSimpleTextViewHolder vh, int position) {

        }

        private void configureViewHolderHeader(ViewHolder_header vh1, int position) {
            header_misligas_item header = (header_misligas_item) items.get(position);
            if (header != null) {
                vh1.getFlag().setImageResource(header.getFlag());
                vh1.getCompeticion().setText(header.getLiga().toUpperCase());
                vh1.getJornadas().setText(header.getJornada());


            }
        }

        private void configureViewHolderPartido(ViewHolder_partido vh2, int position) {
            partido_misligas_item partido = (partido_misligas_item) items.get(position);
            if (partido != null){
                vh2.getEscudolocal().setImageResource(partido.getEscudol());
                vh2.getEscudovisitante().setImageResource(partido.getEscudov());
                vh2.getLocal().setText(utiles.convertirnombre(partido.getLocal()));
                vh2.getPeriodo().setText(partido.getPeriodo());
                vh2.getResultado().setText(partido.getResultado());
                vh2.getVisitante().setText(utiles.convertirnombre(partido.getVisitante()));

                float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, getResources().getDisplayMetrics());

                if (!items.isEmpty()) {
                    if (!(items.size()==position+1)) {
                        if (items.get(position + 1) instanceof header_misligas_item) {
                            ViewGroup.LayoutParams params = vh2.getRv().getLayoutParams();
                            params.height= (int) pixels;
                            vh2.getRv().setBackgroundResource(R.drawable.cardf);
                            vh2.getRv().setLayoutParams(params);

                        }else{
                            vh2.getRv().setBackgroundResource(R.drawable.cardm);
                        }
                    }else{

                        vh2.getRv().setBackgroundResource(R.drawable.cardf);

                    }
                }

            }
        }
    }

}
