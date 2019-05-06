package com.example.recyclerviewah;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.recyclerviewah.classes.ClassFour;
import com.example.recyclerviewah.classes.ClassOne;
import com.example.recyclerviewah.classes.ClassThree;
import com.example.recyclerviewah.classes.ClassTwo;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;
    private AlbumsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            cardView = view.findViewById(R.id.card_view);
        }
    }


    public AlbumsAdapter(Context mContext, List<Album> albumList, AlbumsAdapterListener listener) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + " Books");

        // loading album cover using Glide library
        /*loading album cover using Glide library*/
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow, position);
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCardSelected(position, holder.thumbnail);
                Toast.makeText(mContext, "done", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(AlbumsAdapter.this,AllClass.class);

            }
        });
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent;
                // listener.onCardSelected(position, holder.thumbnail);
//                Toast.makeText(mContext, position + " ", Toast.LENGTH_LONG).show();
//                Intent intent=new Intent(mContext, AllClass.class);
               // intent.putExtra("url",results.get(position).getUrl().toString());
//                mContext.startActivity(intent);
                /*if (position == 0){
                    intent=new Intent(mContext, AllClass.class);
                    // intent.putExtra("url",results.get(position).getUrl().toString());
                    mContext.startActivity(intent);
                }
                if (position == 1){
                    Toast.makeText(mContext, position + " Class ", Toast.LENGTH_LONG).show();
                }*/
                switch (position){
                    case 0:
                        Toast.makeText(mContext, "case " + position, Toast.LENGTH_SHORT).show();
                        intent = new Intent(mContext, ClassOne.class);
                        mContext.startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(mContext, ClassTwo.class);
                        mContext.startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(mContext, ClassThree.class);
                        mContext.startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(mContext, ClassFour.class);
                        mContext.startActivity(intent);
                        break;
                }
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        int position;

        public MyMenuItemClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    listener.onAddToFavoriteSelected(position);
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    listener.onPlayNextSelected(position);
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    interface AlbumsAdapterListener {
        void onAddToFavoriteSelected(int position);

        void onPlayNextSelected(int position);

        void onCardSelected(int position, ImageView thumbnail);
    }
}
