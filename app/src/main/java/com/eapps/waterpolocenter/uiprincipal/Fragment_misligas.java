package com.eapps.waterpolocenter.uiprincipal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eapps.waterpolocenter.R;
import com.eapps.waterpolocenter.clases.header_misligas_item;
import com.eapps.waterpolocenter.clases.ligas_dialogligas_item;
import com.eapps.waterpolocenter.clases.partido_misligas_item;

import com.eapps.waterpolocenter.uisecundario.activity_ligas_selector;
import com.github.fabtransitionactivity.SheetLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Fragment_misligas extends Fragment implements SheetLayout.OnFabAnimationEndListener {

    FloatingActionButton fab;
    final int CHILD_SPECIFIED =1;
    SheetLayout mSheetLayout;
    ArrayList<ligas_dialogligas_item> ligaselegidas;
    ArrayList<Object> rv_lista;

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
        RecyclerView rv = (RecyclerView) fragmentView.findViewById(R.id.recycler_view);
        mSheetLayout = (SheetLayout) fragmentView.findViewById(R.id.bottom_sheet);
        //Find the floating button
        fab = (FloatingActionButton) fragmentView.findViewById(R.id.fab);
        mSheetLayout.setFab(fab);
        mSheetLayout.setFabAnimationEndListener(this);
        rv_lista = new ArrayList<Object>();
        rv_lista.add(new header_misligas_item("DH masc", "Jornada 18/22",R.drawable.flag_spain));
        rv_lista.add(new partido_misligas_item("cata","canoe","19/04/90","12:45",R.drawable.catalunya, R.drawable.canoe ));
        rv_lista.add(new partido_misligas_item("cata","canoe","19/04/90","12:45",R.drawable.catalunya, R.drawable.canoe ));
        rv_lista.add(new partido_misligas_item("cata","canoe","19/04/90","12:45",R.drawable.catalunya, R.drawable.canoe ));
        rv_lista.add(new header_misligas_item("DH masc", "Jornada 18/22",R.drawable.flag_spain));
        rv_lista.add(new partido_misligas_item("cata","canoe","19/04/90","12:45",R.drawable.catalunya, R.drawable.canoe ));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSheetLayout.expandFab();

            }
        });
        // Create adapter passing in the sample user data
        final MisLigas_RecyclerViewAdapter adapter = new MisLigas_RecyclerViewAdapter(rv_lista);
        // Attach the adapter to the recyclerview to populate items
        rv.setAdapter(adapter);
        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(null);
        return fragmentView;
    }

    @Override
    public void onFabAnimationEnd() {
        Intent i = new Intent(getActivity(),
                activity_ligas_selector.class);
        startActivityForResult(i, CHILD_SPECIFIED);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CHILD_SPECIFIED){
            mSheetLayout.contractFab();

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
    public class MisLigas_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        // The items to display in your RecyclerView
        private List<Object> items;

        private final int HEADER = 0, PARTIDO = 1;

        // Provide a suitable constructor (depends on the kind of dataset)
        public MisLigas_RecyclerViewAdapter(List<Object> items) {
            this.items = items;
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return this.items.size();
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
                    viewHolder = new ViewHolder_partido(v1);
                    break;
                case HEADER:
                    View v2 = inflater.inflate(R.layout.rv_misligas_header, viewGroup, false);
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

        private void configureDefaultViewHolder(RecyclerViewSimpleTextViewHolder vh, int position) {

        }

        private void configureViewHolderHeader(ViewHolder_header vh1, int position) {
            header_misligas_item header = (header_misligas_item) items.get(position);
            if (header != null) {
                vh1.getFlag().setImageResource(header.getFlag());
                vh1.getCompeticion().setText(header.getLiga());
                vh1.getJornadas().setText(header.getJornada());


            }
        }

        private void configureViewHolderPartido(ViewHolder_partido vh2, int position) {
            partido_misligas_item partido = (partido_misligas_item) items.get(position);
            if (partido != null){
                vh2.getEscudolocal().setImageResource(partido.getEscudol());
                vh2.getEscudovisitante().setImageResource(partido.getEscudov());
                vh2.getLocal().setText(partido.getLocal());
                vh2.getPeriodo().setText(partido.getPeriodo());
                vh2.getResultado().setText(partido.getResultado());
                vh2.getVisitante().setText(partido.getVisitante());

                float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, getResources().getDisplayMetrics());

                if (!items.isEmpty()) {
                    if (!(items.size()==position+1)) {
                        if (items.get(position + 1) instanceof header_misligas_item) {
                            ViewGroup.LayoutParams params = vh2.getRv().getLayoutParams();
                            params.height= (int) pixels;
                            vh2.getRv().setBackgroundResource(R.drawable.cardf);
                            vh2.getRv().setLayoutParams(params);

                        }
                    }else{
                        ViewGroup.LayoutParams params = vh2.getRv().getLayoutParams();
                        params.height= (int) pixels;
                        vh2.getRv().setBackgroundResource(R.drawable.cardf);
                        vh2.getRv().setLayoutParams(params);
                    }
                }

            }
        }
    }

}
