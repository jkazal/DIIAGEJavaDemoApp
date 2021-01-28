package diiage.potherat.demo.demoapp3.ui.starwars;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.dal.repository.SWRepository;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiErrorResponse;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiSuccessResponse;
import diiage.potherat.demo.demoapp3.model.Quote;
import diiage.potherat.demo.demoapp3.model.sw.People;
import diiage.potherat.demo.demoapp3.model.sw.SWModelList;
import diiage.potherat.demo.demoapp3.model.sw.Vehicle;

public class StarwarsViewModel extends ViewModel {

    private MutableLiveData<String> userInput = new MutableLiveData<>();
    private LiveData<String> output = new MutableLiveData<>();
    SWRepository repo;

    public MutableLiveData<String> getUserInput() {
        return userInput;
    }

    public LiveData<String> getOutput() {
        return output;
    }

    public void setOutput(LiveData<String> output) {
        this.output = output;
    }

    @Inject
    @ViewModelInject
    public StarwarsViewModel(
            SWRepository swr
    ) {
        repo = swr;
    }

    public void onEditInput (String id) {

        int iId = Integer.parseInt(id);
         output = Transformations.map(repo.getVehicleById(iId), input ->
            {
                if(input instanceof ApiSuccessResponse) {
                    Log.d("test", ((ApiSuccessResponse<Vehicle>) input).getBody().name);
                    return ((ApiSuccessResponse<Vehicle>) input).getBody().name + " - " + ((ApiSuccessResponse<Vehicle>) input).getBody().model;
                } else {
                    Log.d("here!", "hi");
                    return "error!";
                }
            }
        );
    }
}