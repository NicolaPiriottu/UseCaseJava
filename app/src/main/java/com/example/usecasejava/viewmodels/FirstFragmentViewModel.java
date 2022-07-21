package com.example.usecasejava.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.usecasejava.api.ApiWorker;
import com.example.usecasejava.models.PostModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FirstFragmentViewModel extends ViewModel {

    public MutableLiveData<List<PostModel>> liveData = new MutableLiveData<List<PostModel>>();

    public void loadPosts(){
        try {
            ApiWorker.getInstance().getPostService().listPosts().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        Log.d("niKo", "ViewModel : Chiamata con successo  result :" + result.get(0).getBody());
                        liveData.setValue(result);
                    }, error -> {
                        Log.d("niKo", "ViewModel : Chiamata in errore :" + error);
                    });
        } catch (Exception e) {
            Log.d("niKo", "ViewModel : Chiamata in errore :" + e);
        }
    }
}
