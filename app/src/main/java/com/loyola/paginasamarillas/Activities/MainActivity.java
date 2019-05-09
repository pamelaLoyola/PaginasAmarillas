package com.loyola.paginasamarillas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.loyola.paginasamarillas.Adapters.CompanyAdapter;
import com.loyola.paginasamarillas.Models.Company;
import com.loyola.paginasamarillas.R;
import com.loyola.paginasamarillas.Repositories.CompanyRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button buttonBuscar;
    private SearchView searchCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_company_list);
        buttonBuscar = findViewById(R.id.main_btn_buscar);
        searchCompany = findViewById(R.id.main_search_company);

        CompanyAdapter adapter=new CompanyAdapter(this);
        final List<Company> companies = CompanyRepository.getCompanies();
        adapter.setCompanies(companies);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),SearchResultActivity.class);
                intent.putExtra("categoria", searchCompany.getQuery().toString());
                startActivity(intent);

            }
        });

    }
}
