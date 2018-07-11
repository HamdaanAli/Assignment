package com.example.assignment.assignment.network;

import com.example.assignment.assignment.model.ParentModel;
import com.example.assignment.assignment.model.Worldpopulation;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by User on 11-Jul-18.
 */

public interface RequestInterface {
    @GET("tutorial/jsonparsetutorial.txt")
    Observable<ParentModel> register();
}
