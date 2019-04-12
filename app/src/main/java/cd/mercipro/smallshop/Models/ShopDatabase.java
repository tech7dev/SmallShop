package cd.mercipro.smallshop.Models;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {Product.class, Stock.class}, version = 1)
public abstract class ShopDatabase extends RoomDatabase {
    private static ShopDatabase instance;
    private static final String DB_NAME = "ShopDatabase";

    public abstract ProductDao productDao();
    public abstract StockDao stockDao();

    public static synchronized ShopDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (ShopDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            ShopDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
//                            .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
//                            .addCallback(new RoomDatabase.Callback() {
//                                @Override
//                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                                    super.onCreate(db);
//                                    Log.d("ShopDatabase", "Creating database...");}})
//                            .build();
                }
            }
        }

        return instance;
    }

}
