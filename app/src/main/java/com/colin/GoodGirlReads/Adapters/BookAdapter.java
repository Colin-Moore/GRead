package com.colin.GoodGirlReads.Adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.GoodGirlReads.Models.Book;
import com.colin.GoodGirlReads.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    Context context;
    List<Book> books = new ArrayList<>(); // Initialize the list here

    public BookAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder holder, int position) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        Log.i("ADAPTER", "Binding?");
        Book book = books.get(position);
        holder.title.setText(book.title);
        holder.author.setText(book.authors.get(0)); // Assuming getAuthors returns a String
        holder.category.setText(book.categories);
//        if (book.imageURL != null && !book.imageURL.isEmpty()) {
//            Glide.with(holder.itemView.getContext())
//                    .load(book.imageURL)
//                    .placeholder(R.drawable.bike_v2) // optional placeholder
//                    .into(holder.img_cover);
//        } else {
//            // Set a default image or clear the previous one if imageURL is null or empty
//            holder.img_cover.setImageResource(R.drawable.bike_v2); // Set a default image or clear the imageView
//        }
    }

    @Override
    public int getItemCount() {
        return books.size(); // Return the size of the books list
    }

    public void submitList(List<Book> books) {
        Log.i("ADAPTER", "Getting list of books?");
        this.books = books;
        notifyDataSetChanged(); // Notify the adapter of the data change
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, author, category;
        ImageView img_cover;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cover = itemView.findViewById(R.id.bookimage);
            title = itemView.findViewById(R.id.txtTitle);
            author = itemView.findViewById(R.id.txtAuthor);
            category = itemView.findViewById(R.id.txtCategory);
        }
    }
}
