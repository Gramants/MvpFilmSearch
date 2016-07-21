package app.go.search.di;


import javax.inject.Singleton;

import app.go.search.view.FilmDetailActivity;
import app.go.search.view.FilmListActivity;
import dagger.Component;

@Singleton
@Component(modules = {
        ServiceModule.class
})
public interface RootComponent {
    void inject(FilmListActivity filmListActivity);
    void inject(FilmDetailActivity filmDetailActivity);
}
