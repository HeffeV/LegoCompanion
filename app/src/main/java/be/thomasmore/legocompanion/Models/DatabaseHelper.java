package be.thomasmore.legocompanion.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="Lego";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SETS = "CREATE TABLE LastViewedItems ("+
                "itemID INTEGER,"+
                "setOrPart INTEGER,"+
                "name TEXT,"+
                "description TEXT,"+
                "imageURL TEXT)";
        db.execSQL(CREATE_TABLE_SETS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LastViewedItems");

        onCreate(sqLiteDatabase);
    }

    public void AddItem(Item item){

        SQLiteDatabase db = this.getWritableDatabase();

        int numrows = db.delete(
                "LastViewedItems",
                "itemID=? and setOrPart=?",
                new String[]{String.valueOf(item.getItemID()),String.valueOf(item.getSetOrPart())});

        ContentValues values = new ContentValues();

        values.put("itemID",item.getItemID());
        values.put("setOrPart",item.getSetOrPart());
        values.put("name",item.getName());
        values.put("description",item.getDescription());
        values.put("imageURL",item.getImageUrl());

        db.insert("LastViewedItems",null,values);
        db.close();
    }

    public List<Item> GetItems(){
        List<Item> items = new ArrayList<Item>();

        String query = "Select * from LastViewedItems";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Item item = new Item(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                items.add(item);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return items;
    }
}
