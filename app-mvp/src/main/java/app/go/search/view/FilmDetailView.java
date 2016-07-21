package app.go.search.view;

import android.content.Context;

import app.go.search.model.FilmDetail;

public interface FilmDetailView {

    void showFullEvento(final FilmDetail owner);

    void showProgressIndicator();

    Context getContext();
}
