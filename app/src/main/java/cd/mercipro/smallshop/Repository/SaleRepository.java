package cd.mercipro.smallshop.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import cd.mercipro.smallshop.Models.Product;
import cd.mercipro.smallshop.Models.ProductDao;
import cd.mercipro.smallshop.Models.Sale;
import cd.mercipro.smallshop.Models.SaleDao;
import cd.mercipro.smallshop.Models.SmallShopDatabase;

public class SaleRepository {
    private SaleDao saleDao;
    private LiveData<List<Sale>> allsales;

    public SaleRepository(Application application){
        SmallShopDatabase database = SmallShopDatabase.getInstance(application);
        saleDao = database.saleDao();
        allsales = saleDao.getAllSales();
    }

    public void insert(Sale sale){
        new InsertProductAsyncTask(saleDao).execute(sale);
    }

    public void update(Sale sale){
        new UpdateSaleAsyncTask(saleDao).execute(sale);
    }
    public void delete(Sale sale){
        new DeleteSaleAsyncTask(saleDao).execute(sale);
    }

    public void deleteAllSales(){
        new DeleteAllSalesAsyncTask(saleDao).execute();
    }

    public LiveData<List<Sale>> getAllsales(){
        return allsales;
    }

    public static class InsertProductAsyncTask extends AsyncTask<Sale,Void,Void>{
        private SaleDao saleDao;

        private InsertProductAsyncTask(SaleDao saleDao){
            this.saleDao = saleDao;
        }

        @Override
        protected Void doInBackground(Sale... sales){
            saleDao.insert(sales[0]);
            return null;
        }
    }

    public static class UpdateSaleAsyncTask extends AsyncTask<Sale,Void,Void>{
        private SaleDao saleDao;

        private UpdateSaleAsyncTask(SaleDao saleDao){
            this.saleDao = saleDao;
        }

        @Override
        protected Void doInBackground(Sale... sales){
            saleDao.update(sales[0]);
            return null;
        }
    }

    public static class DeleteSaleAsyncTask extends AsyncTask<Sale,Void,Void>{
        private SaleDao saleDao;

        private DeleteSaleAsyncTask(SaleDao saleDao){
            this.saleDao = saleDao;
        }

        @Override
        protected Void doInBackground(Sale... sales){
            saleDao.delete(sales[0]);
            return null;
        }
    }

    public static class DeleteAllSalesAsyncTask extends AsyncTask<Sale,Void,Void>{
        private SaleDao saleDao;

        private DeleteAllSalesAsyncTask(SaleDao saleDao){
            this.saleDao = saleDao;
        }

        @Override
        protected Void doInBackground(Sale... sales){
            saleDao.deleteAllSales();
            return null;
        }
    }
}


