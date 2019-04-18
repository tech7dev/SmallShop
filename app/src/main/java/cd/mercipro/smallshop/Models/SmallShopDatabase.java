package cd.mercipro.smallshop.Models;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Product.class}, version = 1)
public abstract class SmallShopDatabase extends RoomDatabase {
    private static SmallShopDatabase instance;
    private static final String DB_NAME = "SmallShopDatabase";

    public abstract ProductDao productDao();

    public static synchronized SmallShopDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (SmallShopDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            SmallShopDatabase.class, DB_NAME)
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

        private PopulateDbAsyncTask(SmallShopDatabase db){
            productDao = db.productDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.insert(new Product("Orange", 100,150,12));
            productDao.insert(new Product("Citron", 100,150,4));
            productDao.insert(new Product("Avocat", 200,400,8));
            return null;
        }
    }
}
