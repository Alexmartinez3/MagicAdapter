package com.amm.sample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.amm.sample.adapter.MyPersonAdapter;
import com.amm.sample.model.Person;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MyPersonAdapter adapter;
    private List<Object> listaPersonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPersonas = new ArrayList<Object>();
        for (int i = 0; i < 130; i++) {
            if (i % 2 == 0)
                listaPersonas.add((Object) new Person("Alejandro numero " + i, "http://rocketdock.com/images/screenshots/supermario.png", "Desarrollador Android"));
            else
                listaPersonas.add((Object) new Person("Alejandro numero " + i, "http://newsupermariobrosu.nintendo.com/_ui/img/luigi/characters-luigi-jump.png", "Desarrollador Android"));
        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new MyPersonAdapter();
        adapter.initializeMagicAdapter(this, listaPersonas, false);
        //Inicializaci�n RecyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }


}
