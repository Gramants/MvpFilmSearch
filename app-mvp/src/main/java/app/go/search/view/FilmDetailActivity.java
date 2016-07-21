package app.go.search.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import app.go.search.R;
import app.go.search.di.InjectHelper;
import app.go.search.model.FilmDetail;
import app.go.search.service.MovieService;
import app.go.search.model.Search;
import app.go.search.presenter.FilmDetailPresenter;
import app.go.search.presenter.FilmDetailPresenterImpl;
import app.go.search.util.ProportionalImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FilmDetailActivity extends AppCompatActivity implements FilmDetailView {

    @Inject
    MovieService mService;

    private static final String EXTRA_REPOSITORY = "EXTRA_REPOSITORY";
    private static final String EXTRA_MAP = "EXTRA_MAP";
    private static final String TAG = "Dettaglio";
    Search repository;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.progress)
    ProgressBar progressBar;
    @InjectView(R.id.htab_collapse_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @InjectView(R.id.text_year)
    TextView yearText;
    @InjectView(R.id.text_type)
    TextView typeText;
    @InjectView(R.id.text_title)
    TextView titleText;
    @InjectView(R.id.text_actors)
    TextView actorsText;
    @InjectView(R.id.text_description)
    TextView plotText;
    @InjectView(R.id.immagine)
    ProportionalImageView immaginefiera;
    private FilmDetail filmdetail;
    private FilmDetailPresenter presenter;
    private TextView genreText;
    private TextView directorText;
    private TextView writerText;
    private TextView releasedText;
    private TextView runtimeText;
    private TextView languageText;
    private TextView countryText;
    private TextView awardsText;
    private TextView ratingText;


    public static Intent newIntent(Context context, Search repository) {
        Intent intent = new Intent(context, FilmDetailActivity.class);
        intent.putExtra(EXTRA_REPOSITORY, repository);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resolveDependency();



        setContentView(R.layout.activity_repository);
        ButterKnife.inject(this);


        collapsingToolbarLayout.setTitle(getResources().getString(R.string.titdett));
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.searchbar));

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (getIntent().getParcelableExtra(EXTRA_REPOSITORY) != null) {
            repository = getIntent().getParcelableExtra(EXTRA_REPOSITORY);
        } else {
            repository = savedInstanceState.getParcelable("repository");

        }


    }


    private void resolveDependency() {
        InjectHelper.getRootComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle(getResources().getString(R.string.dettaglioevento));
        bindRepositoryData(repository);

        if (presenter == null)
            presenter = new FilmDetailPresenterImpl(this,mService);

        presenter.loadFullEvento(repository.getImdbID().toString(), "json", "full");
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("repository", repository);
        repository = outState.getParcelable("repository");

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        repository = savedInstanceState.getParcelable("repository");


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick(R.id.btnmoreinfo)
    protected void onClickFab() {

        showCustomView();
    }

    @Override
    public void showFullEvento(final FilmDetail filmDetail) {


        plotText.setText(filmDetail.getPlot());
        actorsText.setText(filmDetail.getActors());
        this.filmdetail = filmDetail;

        Picasso.with(this)
                .load(filmDetail.getPoster())
                .placeholder(R.drawable.noposterbig)
                .noFade()
                .into(immaginefiera, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {


                    }
                });

        progressBar.setVisibility(View.INVISIBLE);

    }


    @Override
    public void showProgressIndicator() {
        progressBar.setVisibility(View.VISIBLE);

    }


    private void bindRepositoryData(final Search repository) {

        titleText.setText(repository.getTitle());
        typeText.setText(repository.getType());
        yearText.setText(repository.getYear());

    }


    public void showCustomView() {
        boolean wrapInScrollView = true;
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.labeladditional)
                .customView(R.layout.dialog_detail, wrapInScrollView)
                .negativeText(R.string.dialogcancel)
                .build();

        dialog.show();


        View view = dialog.getCustomView();

        genreText = (TextView) view.findViewById(R.id.text_genre);
        genreText.setText(filmdetail.getGenre());

        directorText = (TextView) view.findViewById(R.id.text_director);
        directorText.setText(filmdetail.getDirector());

        writerText = (TextView) view.findViewById(R.id.text_writer);
        writerText.setText(filmdetail.getWriter());

        releasedText = (TextView) view.findViewById(R.id.text_released);
        releasedText.setText(filmdetail.getReleased());

        runtimeText = (TextView) view.findViewById(R.id.text_runtime);
        runtimeText.setText(filmdetail.getRuntime());

        languageText = (TextView) view.findViewById(R.id.text_language);
        languageText.setText(filmdetail.getLanguage());


        countryText = (TextView) view.findViewById(R.id.text_country);
        countryText.setText(filmdetail.getCountry());

        awardsText = (TextView) view.findViewById(R.id.text_awards);
        awardsText.setText(filmdetail.getAwards());

        ratingText = (TextView) view.findViewById(R.id.text_rating);
        ratingText.setText(filmdetail.getImdbRating());
    }


}


