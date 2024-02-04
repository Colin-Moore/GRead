package com.colin.GoodGirlReads.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.colin.GoodGirlReads.Adapters.BookAdapter;
import com.colin.GoodGirlReads.ViewModels.BookViewModel;
import com.colin.GoodGirlReads.Models.Book;
import com.colin.GoodGirlReads.R;
import com.colin.GoodGirlReads.ViewModels.UserViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class ViewBooksActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BookViewModel bookViewModel;
    UserViewModel userViewModel;
    GoogleSignInClient gsc;
    BookAdapter bookAdapter;
    List<Book> bookList;
    ArrayList<String> categories;
    String imageURL;

    ImageView imageView;
    Button searchISBN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);
        setTitle(R.string.availableBooks);
        recyclerView = findViewById(R.id.rvBookList);
        searchISBN = findViewById(R.id.btnISBN);
        imageView = findViewById(R.id.bookimage);

        gsc = GoogleSignIn.getClient(getApplicationContext(), GoogleSignInOptions.DEFAULT_SIGN_IN);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookList = new ArrayList<>();

        searchISBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookViewModel.getBooksByAuthor("Sierra Simone");
//               imageURL = bookViewModel.searchByISBN("9781501175466");
//               if(imageURL != null){
//                   Glide.with(ViewBooksActivity.this)
//                       .load(imageURL) // The URL you got from the JSON parsing
//                       .into(imageView);
//               }
            }
        });

        categories = new ArrayList<>(); //initialize category list

        categories.add(0, getString(R.string.allCategories)); //add default element to the category list

        //set up the recyclerview to view books
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookAdapter = new BookAdapter(this);
        recyclerView.setAdapter(bookAdapter);

        bookViewModel.getBooks().observe(this, books -> bookAdapter.submitList(books));



        //set up the spinner to display book categories for filtering books
        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        //selection listener for book spinner - used to filter books by category
        //this also populates the recyclerview with the list of books
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setSelection(0); //when spinner is first created, select the first item
            }
        });

        //functionality for recyclerview on-swipe delete
//        set up itemTouchHelper, specifying which directions to listen for
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

//            Handle swipe on an item in the recyclerview
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewHolder.getAdapterPosition(); //get the position of the item
//                Book book = bookAdapter.getBookAtPosition(viewHolder.getAdapterPosition()); // get that book information

                //prompt the user to verify they want to delete the book
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewBooksActivity.this);
                builder.setMessage(R.string.confirmDelete).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        bookViewModel.delete(book); //delete the book
                        //remove the book from the list
//                        bookList.remove(book);
                        //check if the category still exists.  if it doesn't exist, go back to the "all categories" selection.
                        if(bookList.isEmpty() || categorySpinner.getSelectedItemPosition() == 0){
//                            categories.remove(book.getCategory());
                            categorySpinner.setSelection(0);
                            bookAdapter.notifyDataSetChanged();
                        }
                        Toast.makeText(ViewBooksActivity.this, R.string.bookDeleted, Toast.LENGTH_SHORT).show();
                    }
                    //if they select no, notify the adapter to repopulate the list (re-add the swiped-off book to the list)
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        bookAdapter.notifyDataSetChanged();
                    }
                });
                builder.show();
            };
        }).attachToRecyclerView(recyclerView); //attach the itemTouchHelper to the recyclerview
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;

        switch(item.getItemId()){
            case R.id.menuAddBook:
                intent = new Intent(ViewBooksActivity.this, AddBookActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuLogout:
                userViewModel.logout(); //logout user through firebase
                gsc.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(ViewBooksActivity.this, "Successfully signed out", Toast.LENGTH_SHORT).show();
                            }
                        });
                intent = new Intent(ViewBooksActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // kill this activity so that it can't be navigated back to after logging out
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}