package app.go.search.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.internal.ThemeSingleton;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import app.go.search.FilmListAdapter;
import app.go.search.R;
import app.go.search.di.InjectHelper;
import app.go.search.service.MovieService;
import app.go.search.model.Search;
import app.go.search.presenter.FilmListPresenter;
import app.go.search.presenter.FilmListPresenterImpl;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FilmListActivity extends AppCompatActivity implements FilmListView {
    @Inject
    MovieService mService;

    @InjectView(R.id.repos_recycler_view)
    RecyclerView reposRecycleView;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.edit_text_username)
    EditText editTextUsername;
    @InjectView(R.id.progress)
    ProgressBar progressBar;
    @InjectView(R.id.text_info)
    TextView infoTextView;
    @InjectView(R.id.button_search)
    ImageButton searchButton;
    @InjectView(R.id.htab_collapse_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @InjectView(R.id.fab)
    FloatingActionButton floatingActionButton;
    private FilmListPresenter presenter;
    private Boolean cansearch = true;
    private TextWatcher mHideShowButtonTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            searchButton.setVisibility(charSequence.length() > 0 ? View.VISIBLE : View.INVISIBLE);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    // FilmListView interface methods implementation

    private void resolveDependency() {
        InjectHelper.getRootComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resolveDependency();



        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);


        //Set up ToolBar

        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Set up RecyclerView

        setupRecyclerView(reposRecycleView);
        // Set up search button

        //Set up username EditText

        editTextUsername.addTextChangedListener(mHideShowButtonTextWatcher);



        collapsingToolbarLayout.setTitle(getResources().getString(R.string.titapp));
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.searchbar));

    }

    // click con butterknife
    @OnClick(R.id.button_search)
    protected void onClickSearch() {
        if (cansearch) {
            presenter.loadRepositories(editTextUsername.getText().toString());
            cansearch = false;
        }
    }

    @OnClick(R.id.fab)
    protected void onClickFab() {
        Scrivici();
    }

    @Override
    protected void onPause() {
        presenter.detachView();
        super.onPause();
    }

    @Override
    protected void onResume() {

        if (presenter == null)
            presenter = new FilmListPresenterImpl(this,mService);

        editTextUsername.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // passo al presenter la variabile e il comando di caricare
                    presenter.loadRepositories(editTextUsername.getText().toString());
                    return true;
                }
                return false;
            }
        });


        super.onResume();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showRepositories(Search[] repositories) {
        FilmListAdapter adapter = (FilmListAdapter) reposRecycleView.getAdapter();


        List<Search> results = Arrays.asList(repositories);
        if (results.size() > 0) {
            Collections.sort(results, new Comparator<Search>() {
                @Override
                public int compare(Search lhs, Search rhs) {
                    return lhs.getTitle().compareTo(rhs.getTitle());
                }

            });
        }

        adapter.setRepositories(results);
        adapter.setContext(getApplicationContext());
        adapter.notifyDataSetChanged();
        reposRecycleView.requestFocus();
        hideSoftKeyboard();
        progressBar.setVisibility(View.INVISIBLE);
        infoTextView.setVisibility(View.INVISIBLE);
        reposRecycleView.setVisibility(View.VISIBLE);
        cansearch = true;
    }

    @Override
    public void showMessage(int stringId) {
        progressBar.setVisibility(View.INVISIBLE);
        infoTextView.setVisibility(View.VISIBLE);
        reposRecycleView.setVisibility(View.INVISIBLE);
        infoTextView.setText(getString(stringId));
        cansearch = true;
    }

    @Override
    public void showProgressIndicator() {
        progressBar.setVisibility(View.VISIBLE);
        infoTextView.setVisibility(View.INVISIBLE);
        reposRecycleView.setVisibility(View.INVISIBLE);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        FilmListAdapter adapter = new FilmListAdapter();
        adapter.setCallback(new FilmListAdapter.Callback() {
            @Override
            public void onItemClick(Search repository) {
                startActivity(FilmDetailActivity.newIntent(FilmListActivity.this, repository));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_mycv:
                showCV();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public void Scrivici() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getResources().getString(R.string.email), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject));
        emailIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.body));
        startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.sendmemail)));
    }


    public void showCV() {
        int accentColor = ThemeSingleton.get().widgetColor;
        if (accentColor == 0)
            accentColor = ContextCompat.getColor(this, R.color.black);
        WebViewDialogMyCV.create(false, accentColor)
                .show(getSupportFragmentManager(), "mycv");
    }

}
