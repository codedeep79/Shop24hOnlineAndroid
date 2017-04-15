package com.example.trungnguyenhau.shoponline24h.Model;

import java.io.Serializable;

/**
 * Created by TRUNGNGUYENHAU on 4/12/2017.
 */

public class ProductIntro implements Serializable {
    private String title;
    private String description;
    private int idImage;

    public ProductIntro(String title, String description, int idImage) {
        this.title = title;
        this.description = description;
        this.idImage = idImage;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getIdImage() {
        return idImage;
    }
}
