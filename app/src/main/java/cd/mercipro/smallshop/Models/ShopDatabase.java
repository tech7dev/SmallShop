package cd.mercipro.smallshop.Models;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {Product.class}, version = 1)
public abstract class ShopDatabase extends RoomDatabase {
    private static ShopDatabase instance;
    private static final String DB_NAME = "ShopDatabase";

    public abstract ProductDao productDao();
    //public abstract StockDao stockDao();

    public static synchronized ShopDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (ShopDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            ShopDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }

        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }

    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private ProductDao productDao;
        //private StockDao stockDao;

        private PopulateDbAsyncTask(ShopDatabase db){
            productDao = db.productDao();
            //stockDao = db.stockDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.insert(new Product("Orange", 100,150));
            productDao.insert(new Product("Citron", 100,150));
            productDao.insert(new Product("Avocat", 200,400));
            return null;
        }
    }
}
