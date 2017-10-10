package com.dragon.serverdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class AIDLService extends Service{

    public final String TAG=this.getClass().getSimpleName();
    private List<Book> mBookList=new ArrayList<>();

    private final BookManager.Stub mBookManager=new BookManager.Stub(){

        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this){
                Log.e(TAG,"Server Client the getBook method is invoked "+mBookList.toString());
                if(mBookList!=null){
                    return mBookList;
                }else {
                    return new ArrayList<>();
                }
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this){
                if(mBookList==null){
                    mBookList=new ArrayList<>();
                }
                if(book==null){
                    Log.e(TAG,"the Book is null from client");
                }else{

                    mBookList.add(book);
                    Log.e(TAG,"the add Book is "+book.toString()+"\n"+"the books list are "+mBookList.toString());
                }
            }
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBookManager;
    }


}
