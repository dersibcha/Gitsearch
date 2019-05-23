package com.example.gitsearch.Model;

import com.google.gson.annotations.SerializedName;

public class AccessToken {

    @SerializedName("access_token")
    private String accesToken;
    @SerializedName("token_type")
    private String tokenType;

    public String getAccesToken() {
        return accesToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
