package cd.mercipro.smallshop.Models;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import java.sql.Date;

@Database(entities = {Product.class, Sale.class}, version = 2)
public abstract class SmallShopDatabase extends RoomDatabase {
    private static SmallShopDatabase instance;
    private static final String DB_NAME = "SmallShopDatabase";

    public abstract ProductDao productDao();
    public abstract SaleDao saleDao();

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
        private SaleDao saleDao;

        private PopulateDbAsyncTask(SmallShopDatabase db){
            productDao = db.productDao();
            saleDao = db.saleDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            //insert default data into "Product" table
            productDao.insert(new Product("Orange", 100,150,12));
            productDao.insert(new Product("Citron", 100,150,4));
            productDao.insert(new Product("Avocat", 200,400,8));

            //insert default data into "Sale" table
            saleDao.insert(new Sale(1,200,4, "2019-04-18"));
            saleDao.insert(new Sale(2,300,6, "2019-04-18"));
            saleDao.insert(new Sale(3,300,8, "2019-04-19"));

            return null;
        }
    }
}
