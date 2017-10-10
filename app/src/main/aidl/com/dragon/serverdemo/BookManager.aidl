// BookManager.aidl
package com.dragon.serverdemo;

// Declare any non-default types here with import statements
import com.dragon.serverdemo.Book;
interface BookManager {
   List<Book> getBooks();

   void addBook(in Book book);
}
