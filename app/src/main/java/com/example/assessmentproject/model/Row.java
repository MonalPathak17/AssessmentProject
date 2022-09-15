package com.example.assessmentproject.model;

import android.widget.ImageView;


import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import com.example.assessmentproject.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;


public class Row
{
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageHref")
    @Expose
    private String imageHref;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref)
    {
        this.imageHref = imageHref;
    }

    // important code for loading image here
    @BindingAdapter({ "imageHref" })
    public static void loadImage(ImageView imageView, String imageURL)
    {
        Picasso.get().load(imageURL).fit().placeholder(R.mipmap.ic_launcher_round).into(imageView);
        /*try {

            Glide.with(imageView.getContext()).load(new URL(imageURL)).placeholder(R.mipmap.ic_launcher_round).into(imageView);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }*/


    }

}
