package com.loyola.paginasamarillas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loyola.paginasamarillas.Activities.DetailCompanyActivity;
import com.loyola.paginasamarillas.Activities.MainActivity;
import com.loyola.paginasamarillas.Models.Company;
import com.loyola.paginasamarillas.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private final AppCompatActivity activity;
    private List<Company> companies;
    private static final String TAG = CompanyAdapter.class.getSimpleName();

    public CompanyAdapter(AppCompatActivity activity) {
        this.activity = activity;
        companies = new ArrayList<>();
    }

    public void setCompanies(List<Company> companies) {

        this.companies = companies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_company,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final Company company=  this.companies.get(position);

        viewHolder.name.setText(company.getName());
        viewHolder.address.setText(company.getAddress());
        viewHolder.phone.setText(company.getPhone());

        final Context context= viewHolder.itemView.getContext();
        int idRes=context.getResources().getIdentifier(company.getLogo(), "drawable",context.getPackageName());
        viewHolder.logo.setImageResource(idRes);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: "+company);

                Intent intent = new Intent(v.getContext(), DetailCompanyActivity.class);
                intent.putExtra("id",company.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return companies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView address;
        TextView phone;
        ImageView logo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.logo_img);
            name = itemView.findViewById(R.id.name_text);
            address = itemView.findViewById(R.id.address_text);
            phone = itemView.findViewById(R.id.phone_text);
        }
    }
}
