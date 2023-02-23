package com.example.alarm.API;

import com.example.alarm.POJO.Verse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/* Interface to defines requests & their EndPoints*/
/* It manage Api usage*/
public interface Api {

    String BASE_URL="https://api.alquran.cloud/v1/ayah/"; /* BaseURL used in every Api request */

    /* GET request (rootThatWillBeAddedToBaseURL) */
    @GET("{verseNumber}")
    Call<Verse> getVerse(@Path("verseNumber") int verseNumber);

}
