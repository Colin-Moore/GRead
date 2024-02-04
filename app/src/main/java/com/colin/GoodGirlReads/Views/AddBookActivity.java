package com.colin.GoodGirlReads.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.colin.GoodGirlReads.Models.Book;
import com.colin.GoodGirlReads.R;
import com.colin.GoodGirlReads.ViewModels.BookViewModel;

public class AddBookActivity extends AppCompatActivity {

    Book book;
    int errors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        setTitle("Add Book");

        BookViewModel bookViewModel = new ViewModelProvider(this).get(BookViewModel.class); //instantiate bookViewModel

        EditText txtBookName = findViewById(R.id.editTextBookName);
        EditText txtCategory = findViewById(R.id.editTextCategory);
        EditText txtBrand = findViewById(R.id.editTextBrand);
        EditText txtCost = findViewById(R.id.editTextCost);

        Button btn = findViewById(R.id.btnAddBook);

        btn.setOnClickListener(new View.OnClickListener() { //handle clicking on Add Book button
            @Override
            public void onClick(View view) {
                errors = 0;
                //validate input fields
                validate(txtBookName);
                validate(txtBrand);
                validate(txtCost);
                validate(txtCategory);

                //if any fields aren't filled out, display a message informing the user
                if (errors > 0) {
                    Toast.makeText(AddBookActivity.this, R.string.fieldError, Toast.LENGTH_SHORT).show();
                }
                //if there are no blank fields, add the book to the database
                else {
                    String bookName = txtBookName.getText().toString();
                    String category = txtCategory.getText().toString();
                    String brand = txtBrand.getText().toString();
                    double cost = Double.parseDouble(txtCost.getText().toString());
                    try {
//                        book = new Book(title, brand, category, cost);
                        book = new Book();
                        bookViewModel.insert(book);
                        Toast.makeText(AddBookActivity.this, bookName + " Added!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.i("TAG", e.toString());
                    }
                    finish(); //return to parent activity
                }
            }
        });
    }

    //method to validate input fields
    public void validate(EditText editText) {
        //if the field is empty, turn the background red and increase the error counter.
        if (editText.getText().toString().isEmpty()) {
            editText.setError(getString(R.string.fieldErrorMessage));
            errors++;
        }
        //if the field is not empty, remove background colour (if any)
        else {
            editText.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}