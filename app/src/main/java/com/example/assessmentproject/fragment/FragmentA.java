package com.example.assessmentproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.assessmentproject.R;
import com.example.assessmentproject.databinding.FragmentLayoutBinding;

public class FragmentA extends Fragment
{
    public PageViewModel pageViewModel;
    FragmentLayoutBinding binding;
    Context context;
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
        super.onCreate(savedInstanceState);
        // init ViewModel
        pageViewModel= new ViewModelProvider(requireActivity()).get(PageViewModel.class);
        context=this.getActivity();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         binding = FragmentLayoutBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();

        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_layout, container, false);
    }
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        binding.imageView.setImageResource(R.mipmap.ic_launcher_round);
        //binding.toolbarHome.setTitle("Hello");



    }

}
