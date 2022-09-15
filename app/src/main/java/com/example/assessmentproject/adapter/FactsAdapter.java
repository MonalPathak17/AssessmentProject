package com.example.assessmentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assessmentproject.R;
import com.example.assessmentproject.databinding.FactsItemRowBinding;
import com.example.assessmentproject.fragment.FragmentA;
import com.example.assessmentproject.model.Row;

import java.util.ArrayList;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.FactsViewHolder>
{
    private ArrayList<Row> employees;
    Context context;
    @NonNull
    @Override
    public FactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        FactsItemRowBinding employeeListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
        R.layout.facts_item_row, viewGroup, false);

        // for dynamic views

       /* DisplayMetrics displayMetrics=new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int devicewidth= displayMetrics.widthPixels/3;
        int deviceheight=displayMetrics.heightPixels/3;

        employeeListItemBinding.ivPic.getLayoutParams().width=devicewidth;
        employeeListItemBinding.ivPic.getLayoutParams().height=deviceheight;

        employeeListItemBinding.tvFullName.getLayoutParams().width=devicewidth;
        employeeListItemBinding.tvFullName.getLayoutParams().height=deviceheight;

        employeeListItemBinding.tvEmail.getLayoutParams().width=devicewidth;
        employeeListItemBinding.tvEmail.getLayoutParams().height=deviceheight;
*/
        return new FactsViewHolder(employeeListItemBinding);
        }
    @Override
    public void onBindViewHolder(@NonNull FactsViewHolder employeeViewHolder, int i)
    {
        Row currentStudent = employees.get(i);
        employeeViewHolder.employeeListItemBinding.setRow(currentStudent);

        //for intent Fragment on item click
/*
        employeeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FragmentA fragmentA=new FragmentA();
                Bundle bundle=new Bundle();
                bundle.putString("title",currentStudent.getTitle());
                bundle.putString("des",currentStudent.getDescription());
                bundle.putString("image",currentStudent.getImageHref());
                fragmentA.setArguments(bundle);
             //   if (fragmentA != null) {
                    FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();;
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentA).commit();
              //  }

            }
        });
*/

        }
    @Override
    public int getItemCount() {
            if (employees != null)
            {
            return employees.size();
            } else {
            return 0;
            }
            }
    @SuppressLint("NotifyDataSetChanged")
    public void setEmployeeList(ArrayList<Row> employees, Context context) {
        this.employees = employees;
        this.context=context;
        notifyDataSetChanged();
        }
     public class FactsViewHolder extends RecyclerView.ViewHolder
{
    private FactsItemRowBinding employeeListItemBinding;
    public FactsViewHolder(@NonNull FactsItemRowBinding employeetListItemBinding)
    {
        super(employeetListItemBinding.getRoot());
        this.employeeListItemBinding = employeetListItemBinding;

    }

}
}
