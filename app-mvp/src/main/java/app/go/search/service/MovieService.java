package app.go.search.service;

import app.go.search.model.FilmDetail;
import app.go.search.model.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;



public interface MovieService {


    @GET("/")
    Observable<Response> getFilmList(@Query("s") String ins, @Query("r") String type);

    @GET("/")
    Observable<FilmDetail> getFilmDetail(@Query("i") String id, @Query("plot") String type, @Query("r") String output);


}
