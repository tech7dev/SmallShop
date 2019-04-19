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

public class SelectProductAdapter extends RecyclerView.Adapter<SelectProductAdapter.SelectProductHolder> {
    private List<Product> products = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public SelectProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_item_product, parent,false);
        return new SelectProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectProductHolder holder, int position) {
        Product currentProduct = products.get(position);
        holder.txtShowProductName.setText(currentProduct.getProductName());
        holder.txtShowQuantity.setText(String.valueOf(currentProduct.getQuantity()));
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

    class SelectProductHolder extends RecyclerView.ViewHolder{
        private TextView txtShowProductName;
        private TextView txtShowQuantity;

        public SelectProductHolder(@NonNull View itemView) {
            super(itemView);
            txtShowProductName = itemView.findViewById(R.id.txtShowProductName);
            txtShowQuantity = itemView.findViewById(R.id.txtShowQuantity);

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
