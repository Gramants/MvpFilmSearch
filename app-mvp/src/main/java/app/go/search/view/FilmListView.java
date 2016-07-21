package app.go.search.view;

import app.go.search.model.Search;

public interface FilmListView extends GetContext {

    void showRepositories(Search[] repositories);

    void showMessage(int stringId);

    void showProgressIndicator();

}
