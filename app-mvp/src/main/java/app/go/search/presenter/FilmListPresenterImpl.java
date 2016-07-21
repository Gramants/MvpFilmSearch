package app.go.search.presenter;

import app.go.search.R;
import app.go.search.service.MovieService;
import app.go.search.model.Response;
import app.go.search.view.FilmListView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FilmListPresenterImpl implements FilmListPresenter {

    public static String TAG = "FilmListPresenterImpl";

    // questa è l'interfaccia
    private FilmListView eventifiltratiView;
    // questa è la sottoscrizione
    private Subscription subscription;
    // lista delle collezioni di oggetti FilmList, il risultato
    private Response repositories;

    private MovieService movieservice;

    public FilmListPresenterImpl(FilmListView eventifiltratiView, MovieService mService) {
        this.eventifiltratiView = eventifiltratiView;
        this.movieservice = mService;
    }



    @Override
    public void detachView() {
        // chiudo il funzionamento dello stream rx se l'app viene chiusa
        this.eventifiltratiView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void loadRepositories(String titleEntered) {
        String title = titleEntered.trim();
        if (title.isEmpty()) return;

        // accendo la rotella che carica nella view
        eventifiltratiView.showProgressIndicator();
        // chiudo il funzionamento dello stream se ce n'era uno
        if (subscription != null) subscription.unsubscribe();


        // chiamo il metodo del service che mi restituisce un observable con una sottoscrizione il cui risultato lo devo fare vedere nel main UI
        subscription = movieservice.getFilmList(title, "json")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Response>() {


                    @Override
                    public void onCompleted() {

                        if (repositories.getResponse().equals("True")) {
                            eventifiltratiView.showRepositories(repositories.getSearch());
                        } else {
                            eventifiltratiView.showMessage(R.string.text_empty_repos);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {

                            eventifiltratiView.showMessage(R.string.error_loading_repos);

                    }

                    @Override
                    public void onNext(Response response) {

                        FilmListPresenterImpl.this.repositories = response;


                    }


                });
    }

}
