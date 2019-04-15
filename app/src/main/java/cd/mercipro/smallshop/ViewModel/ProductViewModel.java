package cd.mercipro.smallshop.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import cd.mercipro.smallshop.Models.Product;
import cd.mercipro.smallshop.Repository.ProductRepository;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository repository;
    private LiveData<List<Product>> allproducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        allproducts = repository.getAllproducts();
    }

    public void insert(Product product){
        repository.insert(product);
    }

    public void update(Product product){
        repository.update(product);
    }

    public void delete(Product product){
        repository.delete(product);
    }

    public void deleteAll(){
        repository.deleteAllProduct();
    }

    public LiveData<List<Product>> getAllproducts(){
        return allproducts;
    }
}
