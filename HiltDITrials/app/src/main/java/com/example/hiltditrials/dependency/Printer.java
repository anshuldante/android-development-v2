package com.example.hiltditrials.dependency;

public abstract class Printer {

    private TextMaker textMaker;

    public Printer(TextMaker textMaker) {
        this.textMaker = textMaker;
    }

    public abstract void print();

    protected String getText() {
        return textMaker.getNiceText();
    }
}
