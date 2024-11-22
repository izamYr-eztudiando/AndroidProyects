package com.salmantino.monedasapp.addeditmoneda;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.salmantino.monedasapp.R;
import com.salmantino.monedasapp.monedas.MonedasActivity;

public class AddEditMonedaActivity extends AppCompatActivity {
   public static final int REQUEST_ADD_MONEDA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String monedaId = getIntent().getStringExtra(MonedasActivity.EXTRA_MONEDA_ID);
        setTitle(monedaId == null ? "Añadir moneda" : "Editar moneda");
        AddEditMonedaFragment addEditLawyerFragment = (AddEditMonedaFragment)
                getSupportFragmentManager().findFragmentById(R.id.add_edit_moneda_container);
        if (addEditLawyerFragment == null) {
            addEditLawyerFragment = AddEditMonedaFragment.newInstance(monedaId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_edit_moneda_container, addEditLawyerFragment)
                    .commit();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
