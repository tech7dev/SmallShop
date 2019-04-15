package cd.mercipro.smallshop.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import cd.mercipro.smallshop.Models.Product;
import cd.mercipro.smallshop.Models.ProductDao;
import cd.mercipro.smallshop.Models.ShopDatabase;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> allproducts;

    public ProductRepository(Application application){
        ShopDatabase database = ShopDatabase.getInstance(application);
        productDao = database.productDao();
        allproducts = productDao.getAllProduct();
    }

    public void insert(Product product){
        new InsertProductAsyncTask(productDao).execute(product);
    }

    public void update(Product product){
        new UpdateProductAsyncTask(productDao).execute(product);
    }
    public void delete(Product product){
        new DeleteProductAsyncTask(productDao).execute(product);
    }

    public void deleteAllProduct(){
        new DeleteAllProductAsyncTask(productDao).execute();
    }

    public LiveData<List<Product>> getAllproducts(){
        return allproducts;
    }

    public static class InsertProductAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDao productDao;

        private InsertProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products){
            productDao.insert(products[0]);
            return null;
        }
    }

    public static class UpdateProductAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDao productDao;

        private UpdateProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products){
            productDao.update(products[0]);
            return null;
        }
    }

    public static class DeleteProductAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDao productDao;

        private DeleteProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products){
            productDao.delete(products[0]);
            return null;
        }
    }

    public static class DeleteAllProductAsyncTask extends AsyncTask<Product,Void,Void>{
        private ProductDao productDao;

        private DeleteAllProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products){
            productDao.deleteAllProducts();
            return null;
        }
    }
}


