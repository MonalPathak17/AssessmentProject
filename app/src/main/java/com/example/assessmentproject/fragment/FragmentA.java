package com.example.assessmentproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.assessmentproject.R;
import com.example.assessmentproject.activity.MainActivity;
import com.example.assessmentproject.databinding.FragmentLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class FragmentA extends Fragment
{
    public PageViewModel pageViewModel;
    FragmentLayoutBinding binding;
    Context context;
    String title,desc,img;
    MainActivity activity;
    public FragmentA()
    {
        // Required empty public constructor
    }
    /**
     * Create a new instance of this fragment
     * @return A new instance of fragment FirstFragment.
     */
    public static FragmentA newInstance() {
        return new FragmentA();
    }
    @Override public void onCreate(@Nullable Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
        // init ViewModel
        pageViewModel= new ViewModelProvider(requireActivity()).get(PageViewModel.class);
        context=this.getActivity();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

         title=getArguments().getString("title");
         desc=getArguments().getString("des");
         img=getArguments().getString("image");
         activity = (MainActivity) getActivity();

        binding = FragmentLayoutBinding.inflate(getLayoutInflater(), container, false);
         return binding.getRoot();


        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_layout, container, false);
    }
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binding.text2.setText(desc);
        Picasso.get().load(img).placeholder(R.mipmap.ic_launcher_round).into(binding.imageView);
        activity.actionBar.setTitle(title);



    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        //inflate menu
        inflater.inflate(R.menu.menu, menu);
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home)
        {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
