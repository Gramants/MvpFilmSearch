package app.go.search.presenter;

import android.annotation.SuppressLint;

import app.go.search.model.FilmDetail;
import app.go.search.service.MovieService;
import app.go.search.view.FilmDetailView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class FilmDetailPresenterImpl implements FilmDetailPresenter {

    private static final String TAG = "FilmDetailPresenterImpl";

    private FilmDetailView filmDetailView;
    private Subscription subscription;
    private MovieService movieservice;

    public FilmDetailPresenterImpl(FilmDetailView filmDetailView, MovieService mService) {
        this.filmDetailView = filmDetailView;
        this.movieservice = mService;
    }


    @Override
    public void detachView() {
        this.filmDetailView = null;
        if (subscription != null) subscription.unsubscribe();
    }


    public void loadFullEvento(String id, String output, String type) {
        filmDetailView.showProgressIndicator();



        subscription = movieservice.getFilmDetail(id, type, output)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<FilmDetail>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void call(FilmDetail filmDetail) {
                        filmDetailView.showFullEvento(filmDetail);
                    }
                });
    }
}
