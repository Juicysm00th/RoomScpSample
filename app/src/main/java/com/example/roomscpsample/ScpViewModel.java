package com.example.roomscpsample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ScpViewModel extends AndroidViewModel {
    private ScpRepository mRepository;

    private LiveData<List<Scp>> mAllWords;

    public ScpViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ScpRepository(application);
        mAllWords = mRepository.getmAllScps();
    }

    public LiveData<List<Scp>> getAllWords() { return mAllWords; }

    public void insert(Scp scp){ mRepository.insert(scp); }
    
    public void deleteAll() {mRepository.deleteAll();}

    public void deleteScp(Scp scp) {mRepository.deleteScp(scp);}

}
