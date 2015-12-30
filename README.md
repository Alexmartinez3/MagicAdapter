# MagicAdapter

MagicAdapter has been created to facilitate the creation and management of adapters thereby reducing the amount of code and reuse it.

How use it:

  1. Create your row layout. Something like this (You should name the layout elements with tag field, after we identify these fields through the tag field):

row_person.xml  

    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:orientation="vertical"
                android:background="@android:color/white"
                android:layout_width="match_parent"
                android:padding="8dp"
                android:layout_height="match_parent">

        <ImageView
            android:id="@+id/ivPerson"
            android:tag="ivPerson"
            android:layout_width="80dp"
            android:layout_height="80dp"/>


        <LinearLayout android:layout_width="match_parent"
                  android:orientation="vertical"
                  android:layout_toRightOf="@id/ivPerson"
                  android:paddingLeft="10dp"
                  android:layout_centerVertical="true"
                  android:layout_height="wrap_content">


            <TextView
                android:id="@+id/tvNombre"
                android:tag="tvNombre"
                android:layout_width="match_parent"
                android:textColor="@android:color/black"
                android:layout_height="wrap_content"/>

            <TextView
                android:id="@+id/tvDescripcion"
                android:tag="tvDescripcion"
                android:layout_width="match_parent"
                android:textColor="@android:color/black"
                android:layout_height="wrap_content"/>

        </LinearLayout>


      <View android:layout_width="match_parent" android:layout_height="1dp" android:layout_alignParentBottom="true"
          android:layout_marginTop="6dp"
          android:background="@color/grey200"/>

</RelativeLayout>

2.- Create your custom Adapter. Your adapter simply extends of "MagicAdapter" and implement the two methods. In the getLayoutName you should indicate the name of your row layout. In the setInfoToViews you get the views across the field tag and set the value on the views.

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



Finally, Just initialize the adapter with the values and indicate if you want it to be animated or not.

    MyPersonAdapter adapter = new MyPersonAdapter();
    adapter.initializeMagicAdapter(this, listaPersonas, false);
