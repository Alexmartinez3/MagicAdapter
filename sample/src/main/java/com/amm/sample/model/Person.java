package com.amm.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by amartinez on 30/12/2015.
 */
public class Person implements Parcelable {

    private String nombre;
    private String imageURL;
    private String descripcion;

    public Person(String nombre, String imageURL, String descripcion) {
        this.nombre = nombre;
        this.imageURL = imageURL;
        this.descripcion = descripcion;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.imageURL);
        dest.writeString(this.descripcion);
    }

    private Person(Parcel in) {
        this.nombre = in.readString();
        this.imageURL = in.readString();
        this.descripcion = in.readString();
    }

    public static Creator<Person> CREATOR = new Creator<Person>() {
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
