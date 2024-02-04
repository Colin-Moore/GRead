package com.colin.GoodGirlReads.Services;

import com.colin.GoodGirlReads.Models.Book;
import com.colin.GoodGirlReads.Models.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksAPI {


    @GET("volumes")
    Call<BookResponse> searchByISBN(@Query("q=") String ISBN);

}
