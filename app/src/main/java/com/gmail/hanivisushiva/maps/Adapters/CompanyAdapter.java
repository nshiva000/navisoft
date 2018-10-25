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

import com.gmail.hanivisushiva.maps.ChildCompany;
import com.gmail.hanivisushiva.maps.R;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyRecyclerViewHolderr>{

    private Context context;
    private List<DCompany> list;
    String m_company_id;

    public CompanyAdapter(Context context, List<DCompany> list) {
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

   companyRecyclerViewHolderr.title.setText(list.get(i).getC_NAME());
        companyRecyclerViewHolderr.des.setText(list.get(i).getC_NAME());
        m_company_id = list.get(i).getM_COMPANY();




        companyRecyclerViewHolderr.company_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChildCompany.class);
                intent.putExtra("company_id",m_company_id);
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
        TextView title,des;
        CardView company_card;
        public CompanyRecyclerViewHolderr(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            des = itemView.findViewById(R.id.des);
            company_card = itemView.findViewById(R.id.company_card);

        }
    }
}
