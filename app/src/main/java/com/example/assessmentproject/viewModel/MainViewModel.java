package com.example.assessmentproject.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assessmentproject.model.Row;
import com.example.assessmentproject.networking.FactsRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
  private FactsRepository employeeRepository;

  public MainViewModel(@NonNull Application application) {
    super(application);
    employeeRepository = new FactsRepository();
  }

  public LiveData<List<Row>> getAllEmployee() {
    return employeeRepository.getMutableLiveData();
  }
}