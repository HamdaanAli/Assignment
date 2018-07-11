package com.example.assignment.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.assignment.assignment.Adapter.DataAdapter;
import com.example.assignment.assignment.model.ParentModel;
import com.example.assignment.assignment.model.Worldpopulation;
import com.example.assignment.assignment.network.RequestInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://www.androidbegin.com/";
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private CompositeDisposable compositeDisposable;
    private ArrayList<Worldpopulation> modelArrayList;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compositeDisposable=new CompositeDisposable();
        initRecyclerView();
        loadJason();
    }
    private void initRecyclerView()
    {
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }
private void loadJason()
{
    RequestInterface requestInterface=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RequestInterface.class);
     compositeDisposable.add(requestInterface.register()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse,this::handleError));
}
    private void handleResponse(ParentModel mdlParent) {

        modelArrayList = new ArrayList<>(mdlParent.worldpopulation);
        dataAdapter = new DataAdapter(modelArrayList);
        recyclerView.setAdapter(dataAdapter);
    }

    private void handleError(Throwable error) {

        Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        Log.e("Null",error.getLocalizedMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
