package com.eapps.waterpolocenter.uisecundario;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.eapps.waterpolocenter.R;
import com.eapps.waterpolocenter.clases.ligas_dialogligas_item;
import com.eapps.waterpolocenter.libary.DragSortRecycler;
import com.eapps.waterpolocenter.utiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class activity_ligas_selector extends AppCompatActivity {

    private Toolbar toolbar;
    ArrayList<ligas_dialogligas_item> ligaselegidas;
    List<String> nombreligas, jornadasligas, urlLigas;
    RecyclerView rv_ligas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.animator.activity_open_translate_from_bottom, R.animator.activity_no_animation);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligas_selector);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rv_ligas = (RecyclerView) findViewById(R.id.dialog_rV);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_close);
        ab.setTitle(R.string.dialogtitle);

        //Recuperar array de ligas seleccionadas y orden
        ligaselegidas = utiles.getLigasArray("arrayid", this);
        nombreligas = Arrays.asList(getResources().getStringArray(R.array.ligas));
        jornadasligas = Arrays.asList(getResources().getStringArray(R.array.Jornadas));
        urlLigas = Arrays.asList(getResources().getStringArray(R.array.urlLigas));

        if (ligaselegidas!=null && ligaselegidas.size()==nombreligas.size()){
            for(int i = 0; i < ligaselegidas.size(); i++) {
                Log.d("lista bool RETRIEVED", ligaselegidas.get(i).getLiga().toString());
                Log.d("lista bool RETRIEVED", "" + ligaselegidas.get(i).isChecked());


            }
        }else{
            ligaselegidas =new ArrayList<ligas_dialogligas_item>();
            Log.d("FIRST TIME ARRAY", "SI");
            for (int i = 0; i < nombreligas.size(); i++) {
                ligas_dialogligas_item item = new ligas_dialogligas_item();
                item.setFlag(R.drawable.flag_spain);
                item.setLiga(nombreligas.get(i));
                item.setJornadas(jornadasligas.get(i));
                item.setUrl(urlLigas.get(i));
                item.setIsChecked(false);
                item.setId(i);
                ligaselegidas.add(i, item);


            }


        }


        // Create adapter passing in the sample user data
        final dialogAdapter adapter = new dialogAdapter(ligaselegidas);
        // Attach the adapter to the recyclerview to populate items
        rv_ligas.setAdapter(adapter);
        // Set layout manager to position the items
        rv_ligas.setLayoutManager(new LinearLayoutManager(this));
        rv_ligas.setItemAnimator(null);

        DragSortRecycler dragSortRecycler = new DragSortRecycler();
        dragSortRecycler.setViewHandleId(R.id.textView); //View you wish to use as the handle

        dragSortRecycler.setOnItemMovedListener(new DragSortRecycler.OnItemMovedListener() {
            @Override
            public void onItemMoved(int from, int to) {
                Log.d("MOVE", "onItemMoved " + from + " to " + to);
                ligas_dialogligas_item item = ligaselegidas.remove(from);
                ligaselegidas.add(to, item);
                adapter.notifyDataSetChanged();


            }
        });

        rv_ligas.addItemDecoration(dragSortRecycler);
        rv_ligas.addOnItemTouchListener(dragSortRecycler);
        rv_ligas.setOnScrollListener(dragSortRecycler.getScrollListener());



    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.animator.activity_no_animation, R.animator.activity_close_translate_to_bottom);
        super.onPause();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dialog_ligas, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
           case R.id.action_ok:
                // procesarGuardar()
               utiles.saveArray("arrayid",ligaselegidas, this);


               this.finish();
                break;
            case android.R.id.home:
                this.finish();
                return (true);
        }

        return super.onOptionsItemSelected(item);
    }

    //ADAPTER
    public class dialogAdapter extends
            RecyclerView.Adapter<dialogAdapter.ViewHolder> {

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public TextView liga;
            public ImageView flag;
            public CheckBox ckbox;

            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);

                liga = (TextView) itemView.findViewById(R.id.textView);
                ckbox = (CheckBox) itemView.findViewById(R.id.checkBox);
                flag = (ImageView) itemView.findViewById(R.id.flag);
            }
        }
        // Store a member variable for the contacts
        private List<ligas_dialogligas_item> mLigas;

        // Pass in the contact array into the constructor
        public dialogAdapter(List<ligas_dialogligas_item> ligas) {
            mLigas = ligas;
        }
        // Usually involves inflating a layout from XML and returning the holder
        @Override
        public dialogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.dialog_ligas_row, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(dialogAdapter.ViewHolder viewHolder, final int position) {
            // Get the data model based on position
            ligas_dialogligas_item liga = mLigas.get(position);

            // Set item views based on the data model
            TextView textView = viewHolder.liga;
            textView.setText(liga.getLiga());

             CheckBox cb = viewHolder.ckbox;
            cb.setChecked(liga.isChecked());
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((CheckBox) v).isChecked()) {
                        ligaselegidas.get(position).setIsChecked(true);
                    } else {
                        ligaselegidas.get(position).setIsChecked(false);
                    }
                }
            });

            ImageView flag = viewHolder.flag;
            flag.setImageResource(R.drawable.flag_spain);




        }

        // Return the total count of items
        @Override
        public int getItemCount() {
            return mLigas.size();
        }

    }




}

