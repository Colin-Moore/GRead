package com.colin.GoodGirlReads.ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.colin.GoodGirlReads.Repository.BookRepository;
import com.colin.GoodGirlReads.Models.Book;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

    private LiveData<List<Book>> bookList;
    private BookRepository bookRepository;

    public BookViewModel(@NonNull Application application){
        super(application);
        bookRepository = new BookRepository(application);
        bookList = bookRepository.getBookList();
    }

    public String searchByISBN(String ISBN) { return bookRepository.searchByISBN(ISBN);}
    public LiveData<List<Book>> getBooks(){return bookList;}
    public void insert(Book book){
        bookRepository.insert(book);
    }
    public void delete(Book book){ bookRepository.delete(book);}
    public void getBooksByAuthor(String author){bookRepository.getBooksByAuthor(author);}
    public void searchByCategory(String category) {bookRepository.UploadBooks(category);}


}
