package com.example.khadijeh_project.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {

    public Boolean ok ;
    @SerializedName("result")
    public ArrayList<Post> result ;
}
