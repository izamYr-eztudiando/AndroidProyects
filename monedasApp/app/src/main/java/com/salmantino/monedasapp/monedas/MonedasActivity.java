package com.salmantino.monedasapp.monedas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.salmantino.monedasapp.R;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.salmantino.monedasapp.databinding.ActivityMonedasBinding;
public class MonedasActivity extends AppCompatActivity {

        public static final String EXTRA_MONEDA_ID = "extra_moneda_id";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_monedas);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);



            MonedasFragment fragment = (MonedasFragment)
                    getSupportFragmentManager().findFragmentById(R.id.monedas_container);

            Toast.makeText(this,"PASA 1", Toast.LENGTH_SHORT).show();

            if (fragment == null) {
                fragment = MonedasFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.monedas_container, fragment)
                        .commit();
                Toast.makeText(this,"PASA 2", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this,"PASA 3", Toast.LENGTH_SHORT).show();
        }
    }
