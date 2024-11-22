package com.salmantino.monedasapp.addeditmoneda;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.salmantino.monedasapp.R;
import com.salmantino.monedasapp.data.Moneda;
import com.salmantino.monedasapp.data.MonedasDbHelper;
import androidx.fragment.app.Fragment;

/**
 * Vista para creación/edición de una moneda
 */

public class AddEditMonedaFragment extends Fragment {

    private static final String ARG_MONEDA_ID = "arg_moneda_id";

    private String mMonedaId;

    private MonedasDbHelper mMonedasDbHelper;

    private FloatingActionButton mSaveButton;
    private TextInputEditText mMonedaField;
    private TextInputEditText mFechaField;
    private TextInputEditText mPrecioField;
    private TextInputEditText mMaterialField;
    private TextInputLayout mMonedaLabel;
    private TextInputLayout mFechaLabel;
    private TextInputLayout mPrecioLabel;
    private TextInputLayout mMaterialLabel;


    public AddEditMonedaFragment() {
        // Required empty public constructor
    }

    public static AddEditMonedaFragment newInstance(String monedaId) {
        AddEditMonedaFragment fragment = new AddEditMonedaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MONEDA_ID, monedaId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMonedaId = getArguments().getString(ARG_MONEDA_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_moneda, container, false);

        // Referencias UI
        mSaveButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mMonedaField = (TextInputEditText) root.findViewById(R.id.et_name);
        mFechaField = (TextInputEditText) root.findViewById(R.id.et_fecha);
        mPrecioField = (TextInputEditText) root.findViewById(R.id.et_precio);
        mMaterialField = (TextInputEditText) root.findViewById(R.id.et_material);
        mMonedaLabel = (TextInputLayout) root.findViewById(R.id.til_name);
        mFechaLabel = (TextInputLayout) root.findViewById(R.id.til_fecha);
        mPrecioLabel = (TextInputLayout) root.findViewById(R.id.til_precio);
        mMaterialLabel = (TextInputLayout) root.findViewById(R.id.til_material);

        // Eventos
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditMoneda();
            }
        });

        mMonedasDbHelper = new MonedasDbHelper(getActivity());

        // Carga de datos
        if (mMonedaId != null) {
            loadMoneda();
        }

        return root;
    }

    private void loadMoneda() {
        new GetMonedaByIdTask().execute();
    }

    private void addEditMoneda() {
        boolean error = false;

        String moneda = mMonedaField.getText().toString();
        String fecha = mFechaField.getText().toString();
        String precio = mPrecioField.getText().toString();
        String material = mMaterialField.getText().toString();

        if (TextUtils.isEmpty(moneda)) {
            mMonedaLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(fecha)) {
            mFechaLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(precio)) {
            mPrecioLabel.setError(getString(R.string.field_error));
            error = true;
        }


        if (TextUtils.isEmpty(material)) {
            mMaterialLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (error) {
            return;
        }

        Moneda oMoneda = new Moneda(moneda, precio, fecha, material, "");
        new AddEditMonedaTask().execute((Runnable) oMoneda);
    }

    private void showMonedasScreen(Boolean requery) {
        if (!requery) {
            showAddEditError();
            getActivity().setResult(Activity.RESULT_CANCELED);
        } else {
            getActivity().setResult(Activity.RESULT_OK);
        }
        getActivity().finish();
    }

    private void showAddEditError() {
        Toast.makeText(getActivity(),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

    private void showMoneda(Moneda moneda) {
        mMonedaField.setText(moneda.getMoneda());
        mFechaField.setText(moneda.getFecha());
        mPrecioField.setText(moneda.getPrecio());
        mMaterialField.setText(moneda.getMaterial());
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al editar una moneda", Toast.LENGTH_SHORT).show();
    }

    private class GetMonedaByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mMonedasDbHelper.getMonedaById(mMonedaId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showMoneda(new Moneda(cursor));
            } else {
                showLoadError();
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
        }
    }

    private class AddEditMonedaTask extends AsyncTask<Moneda, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Moneda... monedas) {
            if (mMonedaId != null) {
                return mMonedasDbHelper.updateMoneda(monedas[0], mMonedaId) > 0;

            } else {
                return mMonedasDbHelper.saveMoneda(monedas[0]) > 0;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            showMonedasScreen(result);
        }
      }
}
