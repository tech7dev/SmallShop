package cd.mercipro.smallshop.Activities;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import  android.content.Intent;

import cd.mercipro.smallshop.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView buyProduct, addProduct, stock, expense;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Navigation drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //defining Cards
        buyProduct = (CardView) findViewById(R.id.buyProduct);
        addProduct = (CardView) findViewById(R.id.addProduct);
        stock = (CardView) findViewById(R.id.stock);
        expense = (CardView) findViewById(R.id.expense);

        //add Click listener to the cards
        buyProduct.setOnClickListener(this);
        addProduct.setOnClickListener(this);
        stock.setOnClickListener(this);
        expense.setOnClickListener(this);
    }
    public void onClick(View v){
        Intent i;
        switch (v.getId()){
            case R.id.buyProduct : i = new Intent(this, BuyProductActivity.class); startActivity(i); break;
            case R.id.addProduct : i = new Intent(this, AddProductActivity.class); startActivity(i); break;
            case R.id.stock : i = new Intent(this, ProductActivity.class); startActivity(i); break;
            case R.id.expense : i = new Intent(this, RegisterActivity.class); startActivity(i); break;
            default:break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
