package com.eapps.waterpolocenter.uiprincipal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eapps.waterpolocenter.R;
import com.eapps.waterpolocenter.clases.partido_misligas_item;
import com.eapps.waterpolocenter.dialogs.dialog_ligas_fragment;
import com.eapps.waterpolocenter.uisecundario.activity_ligas_selector;

import java.util.List;


public class Fragment_misligas extends Fragment {

    FloatingActionButton fab;
    final int CHILD_SPECIFIED =1;

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

        //Find the floating button
        fab = (FloatingActionButton) fragmentView.findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        activity_ligas_selector.class);
                startActivityForResult(i, CHILD_SPECIFIED);
            }
        });
        return fragmentView;
    }





    public class ViewHolder_header extends RecyclerView.ViewHolder {

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
    public class ViewHolder_partido extends RecyclerView.ViewHolder {

        private TextView local, visitante, resultado, periodo;
        private ImageView escudolocal, escudovisitante;

        public ViewHolder_partido(View v) {
            super(v);
            local = (TextView) v.findViewById(R.id.local);
            visitante = (TextView) v.findViewById(R.id.visitante);
            resultado = (TextView) v.findViewById(R.id.resultado);
            periodo = (TextView) v.findViewById(R.id.periodo);
            escudolocal =(ImageView)v.findViewById(R.id.escudol);
            escudovisitante =(ImageView)v.findViewById(R.id.escudov);
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
            } else if (items.get(position) instanceof String) {
                return HEADER;
            }
            return -1;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            RecyclerView.ViewHolder viewHolder;
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());


            View v1 = inflater.inflate(R.layout.rv_misligas_header, viewGroup, false);
            viewHolder = new ViewHolder_header(v1);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            //More to come
        }
    }

}
