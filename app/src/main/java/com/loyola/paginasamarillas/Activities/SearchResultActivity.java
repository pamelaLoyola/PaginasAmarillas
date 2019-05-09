package com.loyola.paginasamarillas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.loyola.paginasamarillas.Adapters.CompanyAdapter;
import com.loyola.paginasamarillas.Models.Company;
import com.loyola.paginasamarillas.R;
import com.loyola.paginasamarillas.Repositories.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private String categoria;
    private RecyclerView reciclerView;
    private TextView results_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        reciclerView = findViewById(R.id.result_list);
        results_view = findViewById(R.id.results_text_view);

        categoria=getIntent().getExtras().getString("categoria");

        results_view.setText("Resultados de la Búsqueda: "+categoria);


        List<Company> companies = CompanyRepository.BuscarCategoria(categoria);

        reciclerView.setLayoutManager(new LinearLayoutManager(this));
        CompanyAdapter adapter = new CompanyAdapter(this);
        adapter.setCompanies(companies);

        reciclerView.setAdapter(adapter);
    }
}
