package com.example.hiltditrials.dependency;

import javax.inject.Inject;

public class TextMaker {

    @Inject
    public TextMaker() {
    }

    public String getNiceText() {
        return "What a wonderful world!";
    }

}
