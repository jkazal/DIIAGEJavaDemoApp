package diiage.potherat.demo.demoapp3.ui.home;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.model.Quote;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Integer> nbDistinctSourceMLD = new MutableLiveData<>();
    private MutableLiveData<Integer> nbQuoteCountMLD = new MutableLiveData<>();
    private MutableLiveData<Quote> finalQuoteMLD = new MutableLiveData<>();
    QuoteRepository repo;
    @Inject
    @ViewModelInject
    public HomeViewModel(
            QuoteRepository qr
    ) {
        repo = qr;
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void fetchDataFromDb() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                finalQuoteMLD.postValue(repo.getLastQuote());
                nbDistinctSourceMLD.postValue(repo.getDistinctSourceCount());
                nbQuoteCountMLD.postValue(repo.getQuoteCount());
            }
        });
        thread.start();
    }

    public MutableLiveData<Integer> getNbDistinctSourceMLD() {
        return nbDistinctSourceMLD;
    }

    public void setNbDistinctSourceMLD(MutableLiveData<Integer> nbDistinctSourceMLD) {
        this.nbDistinctSourceMLD = nbDistinctSourceMLD;
    }

    public MutableLiveData<Integer> getNbQuoteCountMLD() {
        return nbQuoteCountMLD;
    }

    public void setNbQuoteCountMLD(MutableLiveData<Integer> nbQuoteCountMLD) {
        this.nbQuoteCountMLD = nbQuoteCountMLD;
    }

    public MutableLiveData<Quote> getFinalQuoteMLD() {
        return finalQuoteMLD;
    }

    public void setFinalQuoteMLD(MutableLiveData<Quote> finalQuoteMLD) {
        this.finalQuoteMLD = finalQuoteMLD;
    }
}