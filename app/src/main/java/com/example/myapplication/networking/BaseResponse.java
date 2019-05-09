package com.example.myapplication.networking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class BaseResponse<T> implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;

    @SerializedName("num_results")
    @Expose
    private String numResults;


    public T getResults() {
        return results;
    }
    @Expose
    @SerializedName("results")
    private T results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getNumResults() {
        return numResults;
    }

    public void setNumResults(String numResults) {
        this.numResults = numResults;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}

