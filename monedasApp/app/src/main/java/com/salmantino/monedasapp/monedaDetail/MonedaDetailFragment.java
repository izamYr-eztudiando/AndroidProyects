package com.salmantino.monedasapp.monedaDetail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.salmantino.monedasapp.R;
import com.salmantino.monedasapp.addeditmoneda.AddEditMonedaActivity;
import com.salmantino.monedasapp.data.Moneda;
import com.salmantino.monedasapp.data.MonedasDbHelper;
import com.salmantino.monedasapp.monedas.MonedasActivity;
import com.salmantino.monedasapp.monedas.MonedasFragment;

/**
 * Vista para el detalle de la moneda
 */
public class MonedaDetailFragment extends Fragment {
    private static final String ARG_MONEDA_ID = "monedaId";

    private String mMonedaId;

    private CollapsingToolbarLayout mCollapsingView;
    private ImageView mAvatar;
    private TextView mFecha;
    private TextView mPrecio;
    private TextView mMaterial;

    private MonedasDbHelper mMonedasDbHelper;


    public MonedaDetailFragment() {
        // Required empty public constructor
    }

    public static MonedaDetailFragment newInstance(String monedaId) {
        MonedaDetailFragment fragment = new MonedaDetailFragment();
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

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast.makeText(getActivity(),
                "Entra en oncreateview de detailfragment", Toast.LENGTH_SHORT).show();

        View root = inflater.inflate(R.layout.fragment_moneda_detail, container, false);
        mCollapsingView = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
        mAvatar = (ImageView) getActivity().findViewById(R.id.iv_avatar);
        mFecha = (TextView) root.findViewById(R.id.tv_phone_number);
        mPrecio = (TextView) root.findViewById(R.id.tv_specialty);
        mMaterial = (TextView) root.findViewById(R.id.tv_bio);

        mMonedasDbHelper = new MonedasDbHelper(getActivity());
        loadMoneda();
        return root;
    }

    private void loadMoneda() {
        new GetMonedaByIdTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
           //case R.id.action_edit:
            case 1:
                showEditScreen();
                break;
            //case R.id.action_delete:
            case 2:
                new DeleteMonedaTask().execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MonedasFragment.REQUEST_UPDATE_DELETE_MONEDA) {
            if (resultCode == Activity.RESULT_OK) {
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        }
    }

    private void showMoneda(Moneda moneda) {
        mCollapsingView.setTitle(moneda.getMoneda());
        Glide.with(getContext())
                .load(Uri.parse("file:///android_asset/" + moneda.getImagen()))
                .centerCrop()
                .into(mAvatar);
        mFecha.setText(moneda.getFecha());
        mPrecio.setText(moneda.getPrecio());
        mMaterial.setText(moneda.getMaterial());
    }

    private void showEditScreen() {
        Intent intent = new Intent(getActivity(), AddEditMonedaActivity.class);
        intent.putExtra(MonedasActivity.EXTRA_MONEDA_ID, mMonedaId);
        startActivityForResult(intent, MonedasFragment.REQUEST_UPDATE_DELETE_MONEDA);
    }

    private void showMonedasScreen(boolean requery) {
        if (!requery) {
            showDeleteError();
        }
        getActivity().setResult(requery ? Activity.RESULT_OK : Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al cargar información", Toast.LENGTH_SHORT).show();
    }

    private void showDeleteError() {
        Toast.makeText(getActivity(),
                "Error al eliminar moneda", Toast.LENGTH_SHORT).show();
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
            }
        }

    }

    private class DeleteMonedaTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            return mMonedasDbHelper.deleteMoneda(mMonedaId);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            showMonedasScreen(integer > 0);
        }
    }

}

