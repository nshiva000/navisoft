package com.gmail.hanivisushiva.maps.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.hanivisushiva.maps.Models.Database.DChildCompany;
import com.gmail.hanivisushiva.maps.DProject;
import com.gmail.hanivisushiva.maps.R;
import com.gmail.hanivisushiva.maps.Storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class ChildCompanyAdapter extends RecyclerView.Adapter<ChildCompanyAdapter.CompanyRecyclerViewHolderr>{

    private Context context;
    private List<DChildCompany> list;
    String m_company_id,total_projects;
    ArrayList ans_array = new ArrayList();

    public ChildCompanyAdapter(Context context, List<DChildCompany> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CompanyRecyclerViewHolderr onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.company_card,viewGroup,false);
        final CompanyRecyclerViewHolderr mViewHolder = new CompanyRecyclerViewHolderr(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CompanyRecyclerViewHolderr companyRecyclerViewHolderr, int i) {

        companyRecyclerViewHolderr.title.setText(list.get(i).getNAME());
        m_company_id= list.get(i).getCID();


        total_projects = SharedPrefManager.get_mInstance(context).getProjects();
        String foo = total_projects;
        String[] split = foo.split(",");
        for (int k = 0; k < split.length; k++) {

            ans_array.add(split[k]);

        }
        companyRecyclerViewHolderr.left.setVisibility(View.GONE);
        companyRecyclerViewHolderr.right.setVisibility(View.GONE);
        companyRecyclerViewHolderr.r_text.setVisibility(View.GONE);
        companyRecyclerViewHolderr.l_text.setVisibility(View.GONE);
        companyRecyclerViewHolderr.c_text.setText("Total Projects : ");


        companyRecyclerViewHolderr.center.setText(ans_array.size()+"");
        companyRecyclerViewHolderr.company_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DProject.class);
                intent.putExtra("company_id","1");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CompanyRecyclerViewHolderr extends RecyclerView.ViewHolder {
        TextView title,center,left,right,l_text,r_text,c_text;
        CardView company_card;
        public CompanyRecyclerViewHolderr(@NonNull View itemView) {
            super(itemView);
            l_text = itemView.findViewById(R.id.l_text);
            c_text = itemView.findViewById(R.id.c_text);
            r_text = itemView.findViewById(R.id.r_text);
            title = itemView.findViewById(R.id.title);
            center = itemView.findViewById(R.id.center);
            left = itemView.findViewById(R.id.left);
            right = itemView.findViewById(R.id.right);
            company_card = itemView.findViewById(R.id.company_card);

        }
    }
}
