package app.go.search;

import android.app.Application;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class FilmApplication extends Application {



    private Scheduler defaultSubscribeScheduler;

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }





}
