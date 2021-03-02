package com.nik.foodorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nik.foodorder.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static  String DBNAME = "mydatabase.db";
    final static int DBVERSION = 1;
    int Tprice;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table orders "+
                        "(id integer primary key autoincrement,"+
                        "price int,"+
                        "finPrice int,"+
                        "image int,"+
                        "quantity int,"+
                        "description text,"+
                        "foodname text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists orders");
        onCreate(db);
    }

    public boolean insertOrder(int price,int finPrice,int image,int quantity,String foodName,String desc){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /* indexes
        id = 0;
        name = 1
        phone = 2
        price = 3
        image = 4
        desc = 6
        foodname = 7
        quantity = 5
        */
        values.put("price",price);
        values.put("finPrice",finPrice);
        values.put("image",image);
        values.put("quantity",quantity);
        values.put("foodName",foodName);
        values.put("description",desc);


        long id= database.insert("orders",null,values);
        if(id<=0){
            return false;
        }else {
            return true;
        }

    }

    public ArrayList<OrdersModel> getOrders(){
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,foodName,image,price,finPrice from orders",null);
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                model.setFinPrice(cursor.getInt(4)+"");
                orders.add(model);
                Tprice += cursor.getInt(3);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id="+id,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }


        return cursor;

    }

    public boolean updateOrder(int price,int finPrice,int image,String desc,String foodName,int quantity,int id){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /* indexes
        id = 0;
        name = 1
        phone = 2
        price = 3
        image = 4
        desc = 6
        foodname = 7
        quantity = 5
        */
        values.put("price",price);
        values.put("finPrice",finPrice);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodName",foodName);
        values.put("quantity",quantity);

        long row= database.update("orders",values,"id="+id,null);
        if(row<=0){
            return false;
        }else {
            return true;
        }

    }

    public int deleteOrder(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }

    public int totalPrice(){
        return Tprice;
    }

}
