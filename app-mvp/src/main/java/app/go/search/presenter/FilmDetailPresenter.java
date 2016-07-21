package app.go.search.presenter;

public interface FilmDetailPresenter {


    void detachView();

    void loadFullEvento(String s, String json, String full);
}
