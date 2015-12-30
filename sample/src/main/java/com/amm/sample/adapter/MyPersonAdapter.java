package com.amm.sample.adapter;

import android.widget.ImageView;
import android.widget.TextView;
import com.amm.magicadapter.lib.MagicAdapter;
import com.amm.sample.helper.BitmapHelper;
import com.amm.sample.model.Person;

/**
 * Created by amartinez on 30/12/2015.
 */
public class MyPersonAdapter extends MagicAdapter {
    @Override
    public String getLayoutName() {
        return "row_person";
    }

    @Override
    public void setInfoToViews(Object object, int position, MagicViewHolder magicViewHolder) {
        try {
            Person person = (Person) object;

            ImageView ivPerson = (ImageView) magicViewHolder.getViewByName("ivPerson");
            TextView tvNombre = (TextView) magicViewHolder.getViewByName("tvNombre");
            TextView tvDescripcion = (TextView) magicViewHolder.getViewByName("tvDescripcion");

            if (person != null) {
                BitmapHelper.loadImagen(context, ivPerson, person.getImageURL(), null);
                tvNombre.setText(person.getNombre());
                tvDescripcion.setText(person.getDescripcion());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
