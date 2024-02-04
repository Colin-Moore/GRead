package com.colin.GoodGirlReads.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.colin.GoodGirlReads.Dao.BookDao;
import com.colin.GoodGirlReads.Models.Book;

import java.util.List;

public class BookRepository {
    private final BookDao bookDao;

    private LiveData<List<Book>> bookList;
    public BookRepository(Context context){
        bookDao = BookDao.getInstance();
        bookDao.getBooks();
        bookList = bookDao.getBookList();
    }

    public void insert(Book book){
        bookDao.insert(book);
    }

    public void delete(Book book){ bookDao.delete(book);}

    public String searchByISBN(String ISBN) { return bookDao.fetchBookDetailsByISBN(ISBN);}
    public LiveData<List<Book>> getBookList(){return bookList;}
    public void getBooksByAuthor(String author){bookDao.getBooksByAuthor(author);}
    public void UploadBooks(String category) { bookDao.getBooksByCategory(category);}
}