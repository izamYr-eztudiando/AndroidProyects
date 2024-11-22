package com.salmantino.monedasapp.monedas;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.salmantino.monedasapp.R;
import com.salmantino.monedasapp.addeditmoneda.AddEditMonedaActivity;
import com.salmantino.monedasapp.data.MonedasDbHelper;
import com.salmantino.monedasapp.monedaDetail.MonedaDetailActivity;
import static com.salmantino.monedasapp.data.MonedasContract.MonedaEntry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonedasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Vista para la lista de monedas del album
 */

public class MonedasFragment extends Fragment {

        public static final int REQUEST_UPDATE_DELETE_MONEDA = 2;

        private MonedasDbHelper mMonedasDbHelper;

        private ListView mMonedasList;
        private MonedasCursorAdapter mMonedasAdapter;
        private FloatingActionButton mAddButton;


        public MonedasFragment() {
            // Required empty public constructor
        }

        public static MonedasFragment newInstance() {
            return new MonedasFragment();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_monedas, container, false);

            Toast.makeText(getActivity(),"PASA 4 aqui se queda", Toast.LENGTH_SHORT).show();
            // Referencias UI
            mMonedasList = root.findViewById(R.id.monedas_list);
            Toast.makeText(getActivity(),"PASA 5", Toast.LENGTH_SHORT).show();
            mMonedasAdapter = new MonedasCursorAdapter(getActivity(), null);
            Toast.makeText(getActivity(),"PASA 6", Toast.LENGTH_SHORT).show();
            mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
            Toast.makeText(getActivity(),"PASA 7", Toast.LENGTH_SHORT).show();

            // Setup
            mMonedasList.setAdapter(mMonedasAdapter);
            Toast.makeText(getActivity(),"PASA 8", Toast.LENGTH_SHORT).show();


            // Eventos
            mMonedasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Cursor currentItem = (Cursor) mMonedasAdapter.getItem(i);
                    String currentMonedaId = currentItem.getString(currentItem.getColumnIndex(MonedaEntry.ID));
                    showDetailScreen(currentMonedaId);
                }
            });
            mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAddScreen();
                }
            });


            getActivity().deleteDatabase(MonedasDbHelper.DATABASE_NAME);

            // Instancia de helper
            mMonedasDbHelper = new MonedasDbHelper(getActivity());

            // Carga de datos
            loadMonedas();

            return root;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (Activity.RESULT_OK == resultCode) {
                switch (requestCode) {
                    case AddEditMonedaActivity.REQUEST_ADD_MONEDA:
                        showSuccessfullSavedMessage();
                        loadMonedas();
                        break;
                    case REQUEST_UPDATE_DELETE_MONEDA:
                        loadMonedas();
                        break;
                }
            }
        }
        private void loadMonedas() {
            new MonedasLoadTask().execute();
        }

        private void showSuccessfullSavedMessage() {
            Toast.makeText(getActivity(),
                    "Moneda guardada correctamente", Toast.LENGTH_SHORT).show();
        }
        private void showAddScreen() {
            Intent intent = new Intent(getActivity(), AddEditMonedaActivity.class);
            startActivityForResult(intent, AddEditMonedaActivity.REQUEST_ADD_MONEDA);
        }
        private void showDetailScreen(String monedaId) {
            Intent intent = new Intent(getActivity(), MonedaDetailActivity.class);
            intent.putExtra(MonedasActivity.EXTRA_MONEDA_ID, monedaId);
            startActivityForResult(intent, REQUEST_UPDATE_DELETE_MONEDA);
        }
        private class MonedasLoadTask extends AsyncTask<Void, Void, Cursor> {

            @Override
            protected Cursor doInBackground(Void... voids) {
                return mMonedasDbHelper.getAllMonedas();
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                if (cursor != null && cursor.getCount() > 0) {
                    mMonedasAdapter.swapCursor(cursor);
                } else {
                    // Mostrar el estado empty
                }
            }
        }
    }
