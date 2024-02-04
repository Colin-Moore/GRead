package com.colin.GoodGirlReads.Dao;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.colin.GoodGirlReads.Models.Book;
import com.colin.GoodGirlReads.Models.BookResponse;
import com.colin.GoodGirlReads.Services.GoogleBooksAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookDao {

    private static volatile BookDao INSTANCE;
    FirebaseFirestore db;
    FirebaseAuth    mAuth;
    private MutableLiveData<List<Book>> bookList = new MutableLiveData<List<Book>>();
    private BookDao(){
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public static BookDao getInstance(){
        if(INSTANCE == null){
            INSTANCE = new BookDao();
        }
        return INSTANCE;

    }

    //add book to database
    public void insert(Book book){
        try {
            Log.i ("TAG", "Not set up yet... - Insert");
        }
        catch (Exception e){
            Log.i("TAG", e.toString());
        }
    }

    public LiveData<List<Book>> getBookList(){ return bookList;}
    //remove book from database
    public void delete(Book book){
        try {
          Log.i("TAG", "not set up yet... - Delete");
        }
        catch (Exception e){
            Log.i("TAG", e.toString());
        }
    }

    public void getBooks(){
        Log.i("TEST", "getting books?");
        FirebaseUser user = mAuth.getCurrentUser();
        List<Book> books = new ArrayList<>();
        if(user != null){
            db.collection("book")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(QueryDocumentSnapshot document : task.getResult()){
                                    books.add(document.toObject(Book.class));
                                    Log.i("BOOK", document.toObject(Book.class).title);
                                }
                            }
                            bookList.postValue(books);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("OHNO", "Unable to get books - " + e.toString());
                        }
                    });
        }
    }

    public String fetchBookDetailsByISBN(final String isbn) {
        final String[] imageURL = {""};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String apiKey = "AIzaSyBtAgM8wgfYaRIZ2_UoskXT6fRjl25aRNw"; // Replace with your actual API key
                    String urlString = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn + "&key=" + apiKey;
                    URL url = new URL(urlString);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();
                        imageURL[0] = parseBookData(stringBuilder.toString()).toString();
                        Log.i("ggggggg", imageURL[0]);

                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        return imageURL[0];
    }

//    private String parseBookData(String jsonData) {
//        List<Book> parsedBooks = new ArrayList<>();
//        String thumbnailUrl = "";
//        try {
//            JSONObject jsonObject = new JSONObject(jsonData);
//            JSONArray itemsArray = jsonObject.getJSONArray("items");
//            if (itemsArray.length() > 0) {
//                Book book = new Book();
//                JSONObject jsonBook = itemsArray.getJSONObject(0);
//                JSONObject volumeInfo = jsonBook.getJSONObject("volumeInfo");
//
//                book.title = volumeInfo.getString("title");
//                JSONArray authorsArray = volumeInfo.getJSONArray("authors");
//                book.authors = Collections.singletonList(authorsArray.join(", ").replaceAll("\"", ""));
//                book.publishedDate = volumeInfo.getString("publishedDate");
//                book.pages = volumeInfo.getInt("pageCount");
//                JSONArray categoriesArray = volumeInfo.optJSONArray("categories");
//                book.categories = categoriesArray != null ? categoriesArray.join(", ").replaceAll("\"", "") : "N/A";
//                if (volumeInfo.has("imageLinks")) {
//                    JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
//                    thumbnailUrl = imageLinks.optString("thumbnail", "");
//                    // Use optString to avoid JSONException if the key is not present
//                }
//                parsedBooks.add(book);
//                    // Now you have title, authors, publishedDate, pageCount, and categories
//                // Update your UI or handle data accordingly
//            }
//            bookList.postValue(parsedBooks);
//            Log.i("BOOK", jsonObject.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return thumbnailUrl;
//    }
    public void getBooksByCategory(String category) {
        new Thread(() -> {
            try {
                String apiKey = "AIzaSyBtAgM8wgfYaRIZ2_UoskXT6fRjl25aRNw"; // Use your actual API key
                String urlString = "https://www.googleapis.com/books/v1/volumes?q=subject:" + URLEncoder.encode(category, "UTF-8") + "&key=" + apiKey;
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                StringBuilder stringBuilder = new StringBuilder();
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    List<Book> books = parseBookData(stringBuilder.toString());
                    // Here, handle the list of books, for example, upload to Firestore
                    uploadBooksToFirestore(books);
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    public void getBooksByAuthor(String author) {
        new Thread(() -> {
            try {
                String apiKey = "AIzaSyBtAgM8wgfYaRIZ2_UoskXT6fRjl25aRNw"; // Use your actual API key
                String urlString = "https://www.googleapis.com/books/v1/volumes?q=inauthor:" + URLEncoder.encode(author, "UTF-8") + "&key=" + apiKey;
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                StringBuilder stringBuilder = new StringBuilder();
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    List<Book> books = parseBookData(stringBuilder.toString());
                    // Here, handle the list of books, for example, upload to Firestore
                    uploadBooksToFirestore(books);
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    private List<Book> parseBookData(String jsonData) {
        List<Book> books = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < 500; i++) {
                JSONObject jsonBook = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = jsonBook.getJSONObject("volumeInfo");
                Book book = new Book();
                book.title = volumeInfo.getString("title");
                book.authors = new ArrayList<>(); // Assuming you change the authors to a List<String>
                if (volumeInfo.has("authors")) {
                    JSONArray authorsArray = volumeInfo.getJSONArray("authors");
                    for (int j = 0; j < authorsArray.length(); j++) {
                        book.authors.add(authorsArray.getString(j));
                    }
                }
                book.pages = volumeInfo.optInt("pageCount", 0); // Use optInt to provide a default value
                book.categories = volumeInfo.getJSONArray("categories").getString(0); // Assuming one category per book
                if (volumeInfo.has("imageLinks")) {
                    JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                    book.imageURL = imageLinks.optString("thumbnail", "");
                }
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    private void uploadBooksToFirestore(List<Book> books) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (Book book : books) {
            db.collection("book").add(book)
                    .addOnSuccessListener(documentReference -> Log.d("Firestore", "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w("Firestore", "Error adding document", e));
        }
    }
}
