package com.salmantino.monedasapp.monedas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.salmantino.monedasapp.R;
import com.salmantino.monedasapp.data.MonedasContract.MonedaEntry;

/**
 * Adaptador de monedas
 */

public class MonedasCursorAdapter extends CursorAdapter {

    public MonedasCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_moneda, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        // Referencias UI.
        TextView nameText = (TextView) view.findViewById(R.id.tv_name);
        final ImageView avatarImage = (ImageView) view.findViewById(R.id.iv_avatar);

        // Get valores.
        //String name = cursor.getString(cursor.getColumnIndex(MonedaEntry.MONEDA));
        //String avatarUri = cursor.getString(cursor.getColumnIndex(MonedaEntry.IMAGEN));

        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(MonedaEntry.MONEDA));
        @SuppressLint("Range") String avatarUri = cursor.getString(cursor.getColumnIndex(MonedaEntry.IMAGEN));

        // Setup.
        nameText.setText(name);
        Glide
                .with(context)
                .asBitmap()
                .load(Uri.parse("file:///android_asset/" + avatarUri))
                .error(R.drawable.ic_account_circle)
                .centerCrop()
                .into(new BitmapImageViewTarget(avatarImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable
                                = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        drawable.setCircular(true);
                        avatarImage.setImageDrawable(drawable);
                    }
                });

    }

}
