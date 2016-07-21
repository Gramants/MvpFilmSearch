package app.go.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import app.go.search.model.Search;
import app.go.search.util.ProportionalImageView;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.RepositoryViewHolder> {

    private List<Search> repositories;
    private Callback callback;
    private Context ctx;

    public FilmListAdapter() {
        this.repositories = Collections.emptyList();
    }

    public FilmListAdapter(List<Search> repositories) {
        this.repositories = repositories;
    }

    public void setRepositories(List<Search> repositories) {
        this.repositories = repositories;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setContext(Context applicationContext) {
        this.ctx = applicationContext;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        final RepositoryViewHolder viewHolder = new RepositoryViewHolder(itemView);
        viewHolder.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onItemClick(viewHolder.repository);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Search repository = repositories.get(position);
        Context context = holder.titleTextView.getContext();
        holder.repository = repository;

        holder.titleTextView.setText(repository.getTitle());
        holder.dataTextView.setText(repository.getYear());
        holder.typeTextView.setText(repository.getType());
        Picasso.with(ctx)
                .load(repository.getPoster())
                .placeholder(R.drawable.nopreview)
                .into(holder.posterImageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }


    public interface Callback {
        void onItemClick(Search repository);
    }

    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        public View contentLayout;

        public ProportionalImageView posterImageView;
        public TextView titleTextView;
        public TextView dataTextView;
        public TextView typeTextView;
        public Search repository;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            contentLayout = itemView.findViewById(R.id.layout_content);
            posterImageView = (ProportionalImageView) itemView.findViewById(R.id.poster);
            titleTextView = (TextView) itemView.findViewById(R.id.evento_title);
            dataTextView = (TextView) itemView.findViewById(R.id.evento_data);
            typeTextView = (TextView) itemView.findViewById(R.id.evento_type);

        }
    }
}
