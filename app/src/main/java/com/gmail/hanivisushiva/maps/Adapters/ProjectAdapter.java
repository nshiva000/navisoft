package com.gmail.hanivisushiva.maps.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.hanivisushiva.maps.MapsActivity;
import com.gmail.hanivisushiva.maps.Models.Database.DatabaseProject;
import com.gmail.hanivisushiva.maps.R;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {


    private Context context;
    private List<DatabaseProject> project;

    public ProjectAdapter(Context context, List<DatabaseProject> project) {
        this.context = context;
        this.project = project;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.company_card,viewGroup,false);
        final ProjectViewHolder mViewHolder = new ProjectViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder projectViewHolder, int i) {

        final String p_center = project.get(i).getPCENTER();
        projectViewHolder.left.setText(project.get(i).getT_AVAILABLE());
        projectViewHolder.center.setText(project.get(i).getT_PLOT_SOLD());
        projectViewHolder.right.setText(project.get(i).getT_PLOT_AREA());
        projectViewHolder.title.setText(project.get(i).getNAME());

        final String pid = project.get(i).getPID();




        projectViewHolder.company_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MapsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("pid",pid);
                intent.putExtra("center_map",p_center);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return project.size();
    }


    public class ProjectViewHolder extends RecyclerView.ViewHolder {

        TextView title,center,left,right;
        CardView company_card;
        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            center = itemView.findViewById(R.id.center);
            left = itemView.findViewById(R.id.left);
            right = itemView.findViewById(R.id.right);
            company_card = itemView.findViewById(R.id.company_card);
        }
    }
}
