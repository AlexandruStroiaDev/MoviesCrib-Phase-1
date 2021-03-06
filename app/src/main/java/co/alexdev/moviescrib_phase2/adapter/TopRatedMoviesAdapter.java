package co.alexdev.moviescrib_phase2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.alexdev.moviescrib_phase2.R;
import co.alexdev.moviescrib_phase2.model.Movie;

/*Adapter used to populate TopRatedFragment*/
public class TopRatedMoviesAdapter extends RecyclerView.Adapter<TopRatedMoviesAdapter.TopRatedMoviesViewHolder> {

    private List<Movie> mMovieList;
    private static onTopRatedMovieClick mMovieClickListener;
    private final Context mContext;
    final String tmdb_image_url;

    /*Listener used to detect the position of the Movie that is clicked in the Adapter*/
    public interface onTopRatedMovieClick {
        void onMovieClick(int position);
    }

    public TopRatedMoviesAdapter(Context context, List<Movie> mMovieList, TopRatedMoviesAdapter.onTopRatedMovieClick movieClickListener) {
        this.mContext = context;
        this.mMovieList = mMovieList;
        this.mMovieClickListener = movieClickListener;
        tmdb_image_url = context.getString(R.string.tmdb_image_url_large);
    }

    @Override
    public TopRatedMoviesAdapter.TopRatedMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final Context context = mContext;
        final View rootView = LayoutInflater.from(context).inflate(R.layout.item_movie_list, viewGroup, false);
        return new TopRatedMoviesViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopRatedMoviesAdapter.TopRatedMoviesViewHolder topRatedMoviesViewHolder, int i) {
        final String title = mMovieList.get(i).getTitle();
        final String imagePath = mMovieList.get(i).getPoster_path();
        final String imageUri = buildImageUri(imagePath);

        topRatedMoviesViewHolder.tv_movie_title.setText(title);
        Picasso.get().load(imageUri)
                .into(topRatedMoviesViewHolder.iv_movie);
    }

    @Override
    public int getItemCount() {
        return (mMovieList != null && mMovieList.size() > 0 ? mMovieList.size() : 0);
    }

    /*Function used to set the movie list with the movie data that came from the API*/
    public void setMovieList(List<Movie> mMovieList) {
        this.mMovieList = mMovieList;
        notifyDataSetChanged();
    }

    /*Helper function that help us to build the URI for our image
     * pathForLargeImage - the basic path that every image has
     * imageUri - the unique URI that every image has */
    private String buildImageUri(final String imagePath) {
        String imageString = new StringBuilder().append(tmdb_image_url).append(imagePath).toString();
        return imageString;
    }

    /*Implemented View.OnClickListener to detect when a item is tapped in the RecyclerView*/
    static class TopRatedMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv_movie;
        TextView tv_movie_title;

        public TopRatedMoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_movie = itemView.findViewById(R.id.iv_poster);
            tv_movie_title = itemView.findViewById(R.id.tv_movie_name);
            itemView.setOnClickListener(this);
        }

        /*Override the function by getting the adapter position that is clicked and passing it to our listener*/
        @Override
        public void onClick(View view) {
            final int position = getAdapterPosition();
            mMovieClickListener.onMovieClick(position);
        }
    }
}