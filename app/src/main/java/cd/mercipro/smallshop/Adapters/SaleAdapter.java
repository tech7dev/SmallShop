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
import cd.mercipro.smallshop.Models.Sale;
import cd.mercipro.smallshop.R;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.SaleHolder> {
    private List<Sale> sales = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public SaleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sales_view_item, parent,false);
        return new SaleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleHolder holder, int position) {
        Sale currentSale = sales.get(position);
        holder.txtProductName.setText(currentSale.getProductID());
        holder.txtPT.setText(String.valueOf(currentSale.getPT()));
        holder.txtQuantity.setText(String.valueOf(currentSale.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return sales.size();
    }

    public void setSales(List<Sale> sales){
        this.sales = sales;
        notifyDataSetChanged();
    }

    //get position of sale in recyclerview
    public Sale getSaleAt(int position) {
        return sales.get(position);
    }

    class SaleHolder extends RecyclerView.ViewHolder{
        private TextView txtProductName;
        //private TextView txtPA; //prix d'achat
        private TextView txtPT; //prix de vente
        private TextView txtQuantity; //quantité

        public SaleHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtPT = itemView.findViewById(R.id.txtPT);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(sales.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Sale sale);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
