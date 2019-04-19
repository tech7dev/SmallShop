package cd.mercipro.smallshop.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;
import cd.mercipro.smallshop.Models.Product;
import cd.mercipro.smallshop.Models.Sale;
import cd.mercipro.smallshop.Repository.ProductRepository;
import cd.mercipro.smallshop.Repository.SaleRepository;

public class SaleViewModel extends AndroidViewModel {

    private SaleRepository repository;
    private LiveData<List<Sale>> allsales;

    public SaleViewModel(@NonNull Application application) {
        super(application);
        repository = new SaleRepository(application);
        allsales = repository.getAllsales();
    }

    public void insert(Sale sale){
        repository.insert(sale);
    }

    public void update(Sale sale){
        repository.update(sale);
    }

    public void delete(Sale sale){
        repository.delete(sale);
    }

    public void deleteAll(){
        repository.deleteAllSales();
    }

    public LiveData<List<Sale>> getAllsales(){
        return allsales;
    }
}
