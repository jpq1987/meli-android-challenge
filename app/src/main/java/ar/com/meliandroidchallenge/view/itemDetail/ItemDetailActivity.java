package ar.com.meliandroidchallenge.view.itemDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import ar.com.meliandroidchallenge.R;
import ar.com.meliandroidchallenge.service.model.Item;
import ar.com.meliandroidchallenge.utils.StringUtils;
import ar.com.meliandroidchallenge.viewmodel.ItemDetailViewModel;

import static ar.com.meliandroidchallenge.utils.Constants.LOCALE_AR;

public class ItemDetailActivity extends AppCompatActivity implements View.OnClickListener{
    // tag used for debugging
    private static final String TAG = ItemDetailActivity.class.getSimpleName();

    private ItemDetailViewModel itemDetailViewModel;
    private Item item;

    private TextView titleItemTextView;
    private TextView conditionItemTextView;
    private TextView salesTotalItemTextView;
    private ImageView thumbnailImageView;
    private TextView originalPriceTextView;
    private TextView priceTextView;
    private TextView discountPercentTextView;
    private TextView installmentsTextView;
    private TextView shippingModeTextView;
    private TextView deliveryModeTextView;
    private TextView sellerNameTextView;
    private TextView sellerAddressTextView;
    private TextView sellerTotalSalesTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        // Gets item or closes the activity
        Intent intent = getIntent();
        if (intent.hasExtra("item")){
            item = intent.getParcelableExtra("item");
        } else {
            //TODO: in the next version an error message should be displayed
            finish();
        }

        renderUI();

        itemDetailViewModel = new ViewModelProvider(this).get(ItemDetailViewModel.class);
        observeViewModel(itemDetailViewModel);
    }

    private void observeViewModel(ItemDetailViewModel itemDetailViewModel) {
        itemDetailViewModel.loadItemDetail(item.getId());
        itemDetailViewModel.getItemLiveData().observe(this, item -> {
            if (item != null){
                this.item = item;
                updateDataUI();
            } else{
                Log.d(TAG,"item is null");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_itemdetail_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.favorite_item:
                displayToastMessage("Próximamente vas a poder agregar el producto a tus favoritos");;
                break;
            case R.id.cart_item:
                displayToastMessage("Próximamente vas a poder agregar el producto al carrito");;
                break;
        }
        return true;
    }

    private void renderUI() {
        // Sets up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_itemdetail);
        toolbar.setTitle("Producto");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Sets up the header section
        conditionItemTextView = findViewById(R.id.textview_itemdetail_condition);
        conditionItemTextView.setText(StringUtils.getLabelForCondition(item.getCondition()));
        salesTotalItemTextView = findViewById(R.id.textview_itemdetail_totalsales);
        salesTotalItemTextView.setText(item.getSoldQuantity()+" vendidos");
        titleItemTextView = findViewById(R.id.textview_itemdetail_title);
        titleItemTextView.setText(item.getTitle());

        // Sets up the pictures section
        // TODO: in the next version image slider should be rendered to display all pictures
        thumbnailImageView = findViewById(R.id.imageview_itemdetail_thumbnail);
        String imageUrl = item.getThumbnail().replace("http://", "https://");;
        Glide.with(this)
                .load(imageUrl)
                .into(thumbnailImageView);

        // Sets up the prices & installments section
        originalPriceTextView = findViewById(R.id.textview_itemdetail_originalprice);
        priceTextView = findViewById(R.id.textview_itemdetail_price);
        discountPercentTextView = findViewById(R.id.textview_itemdetail_discountpercent);
        installmentsTextView = findViewById(R.id.textview_itemdetail_installments);
        if (item.getOriginalPrice() > 0) {
            originalPriceTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            originalPriceTextView.setText(StringUtils.formatPriceWithLocalizedCurrency(item.getOriginalPrice(), item.getCurrencyID(), LOCALE_AR));
            originalPriceTextView.setVisibility(View.VISIBLE);
            discountPercentTextView.setText(StringUtils.getDiscountLabelBetweenPrices(item.getOriginalPrice(), item.getPrice()));
            discountPercentTextView.setVisibility(View.VISIBLE);
        }
        priceTextView.setText(StringUtils.formatPriceWithLocalizedCurrency(item.getPrice(), item.getCurrencyID(), LOCALE_AR));
        installmentsTextView.setText(StringUtils.getInstallmentsLabel(item.getInstallments()));

        // Sets up the shipping section
        shippingModeTextView = findViewById(R.id.textview_itemdetail_shippingmode);
        deliveryModeTextView = findViewById(R.id.textview_itemdetail_delivery);
        shippingModeTextView.setText(StringUtils.getShippingModeLabel(item.getShipping()));
        deliveryModeTextView.setText(StringUtils.getDeliveryModeLabel(item.getShipping().getMode()));

        // Sets up the seller section
        sellerNameTextView = findViewById(R.id.textview_itemdetail_seller_name);
        sellerAddressTextView = findViewById(R.id.textview_itemdetail_seller_address);
        sellerTotalSalesTextView = findViewById(R.id.textview_itemdetail_seller_totalsales);
        sellerNameTextView.setText(StringUtils.getSellerNameLabel(item.getOfficialStoreID(), item.getSeller()));
        sellerAddressTextView.setText(StringUtils.getSellerAddress(item.getAddress()));
        sellerTotalSalesTextView.setText(StringUtils.getTotalSalesLabel(item.getSeller().getSellerReputation()));

        // TODO: in the next version rating section should be rendered
        // TODO: in the next version extra information section should be rendered
        // TODO: in the nex version fav & share buttons section should be rendered
    }

    /**
     * Updates the UI with information provided by {@link ItemDetailViewModel}
     *
     */
    private void updateDataUI(){
        String imageUrl = item.getPictures().get(0).getSecureURL();
        Glide.with(this)
                .load(imageUrl)
                .into(thumbnailImageView);
        // TODO: in the next version new sections should be rendered (ratings, comments and more)
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textview_itemdetail_paymentmethods:
                displayToastMessage("Próximamente vas a poder consultar los medios de pago disponibles");
                break;
            case R.id.textview_itemdetail_shippingcosts:
                displayToastMessage("Próximamente vas a poder consultar los costos de envío");
                break;
            case R.id.textview_itemdetail_deliverymap:
                displayToastMessage("Próximamente vas a poder consultar el mapa para retirar tu compra");
                break;
            case R.id.textview_itemdetail_seller_name:
                displayToastMessage("Próximamente vas a poder consultar el perfil del vendedor");
                break;
            case R.id.button_itemdetail_buy:
                displayToastMessage("Próximamente vas a poder comprar este producto");
                break;
        }
    }

    /**
     * Displays a simple message on a {@link Toast}
     *
     * @param message The message to display
     */
    private void displayToastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}