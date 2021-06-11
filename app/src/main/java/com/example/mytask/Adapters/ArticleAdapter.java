package com.example.mytask.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytask.R;
import com.example.mytask.model.ArticlesItem;
import com.example.mytask.model.MediaItem;
import com.example.mytask.model.MediaMetadataItem;
import com.example.mytask.ui.DetailsActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    List<ArticlesItem> articleList;
    private Context context;


    public ArticleAdapter(Context context,List<ArticlesItem> newsList) {
        this.articleList = newsList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.article_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        ArticlesItem article = articleList.get(pos);
        viewHolder.title.setText(article.getTitle());
        viewHolder.date.setText(article.getPublishedDate());
        viewHolder.publishedBy.setText(article.getByline());


        String url="";
        if (article.getMedia()!= null && article.getMedia().size()>0)
        {

            MediaItem mediaItem=article.getMedia().get(0);
            if (mediaItem.getMediaMetadata() != null && mediaItem.getMediaMetadata().size()>0)
            {

                for (MediaMetadataItem media: mediaItem.getMediaMetadata()) {
                    if (media.getFormat().equals("Standard Thumbnail")){
                        url=media.getUrl();

                    }
                }
            }

        }
        Glide.with(context)
                .load(url).placeholder(R.drawable.profile).into(viewHolder.imageView);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsActivityIntent=new  Intent(context, DetailsActivity.class);
                detailsActivityIntent.putExtra("title",article.getTitle());
                detailsActivityIntent.putExtra("date",article.getPublishedDate());
                detailsActivityIntent.putExtra("publishedBy",article.getByline());
                detailsActivityIntent.putExtra("description",article.getBriefSummary());


                String url="";
                if (article.getMedia()!= null && article.getMedia().size()>0)
                {

                    MediaItem mediaItem=article.getMedia().get(0);
                    if (mediaItem.getMediaMetadata() != null && mediaItem.getMediaMetadata().size()>0)
                    {

                        for (MediaMetadataItem media: mediaItem.getMediaMetadata()) {
                            if (media.getFormat().equals("mediumThreeByTwo440")){
                                url=media.getUrl();

                            }
                        }
                    }

                }

                detailsActivityIntent.putExtra("imageURL",url);
                context.startActivity(detailsActivityIntent);
            }
        });
    }

    public void changeData(List<ArticlesItem> newItems){
        this.articleList =newItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(articleList ==null)return 0;
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private CircleImageView imageView;
        private TextView title,date, publishedBy;
        private CardView cardView;

        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            publishedBy = view.findViewById(R.id.published_by);
            cardView = view.findViewById(R.id.card_view);
        }

    }
}
