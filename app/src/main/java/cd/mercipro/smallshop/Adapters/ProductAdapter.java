package cd.mercipro.smallshop.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cd.mercipro.smallshop.Models.Product;
import cd.mercipro.smallshop.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private List<Product> products = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent,false);
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product currentProduct = products.get(position);
        holder.txtProductName.setText(currentProduct.getProductName());
        //holder.txtPA.setText(String.valueOf(currentProduct.getPu_achat()));
        holder.txtPV.setText(String.valueOf(currentProduct.getPu_vente()));
        holder.txtQuantity.setText(String.valueOf(currentProduct.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }

    //get position of product in recyclerview
    public Product getProductAt(int position) {
        return products.get(position);
    }

    class ProductHolder extends RecyclerView.ViewHolder{
        private TextView txtProductName;
        //private TextView txtPA; //prix d'achat
        private TextView txtPV; //prix de vente
        private TextView txtQuantity; //quantité

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            //txtPA = itemView.findViewById(R.id.txtPA);
            txtPV = itemView.findViewById(R.id.txtPV);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(products.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Product product);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
