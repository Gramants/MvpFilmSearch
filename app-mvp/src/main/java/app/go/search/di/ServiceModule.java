package app.go.search.di;

import javax.inject.Singleton;

import app.go.search.service.MovieService;
import app.go.search.service.RetrofitService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class ServiceModule {


    @Provides
    @Singleton
    public RetrofitService providesRetrofitService() {
        return new RetrofitService();
    }


    @Provides
    @Singleton
    public MovieService providesApiService(RetrofitService retrofitService) {
        Retrofit retrofit = retrofitService.buildRetrofitIstance();
        return retrofit.create(MovieService.class);
    }
}
