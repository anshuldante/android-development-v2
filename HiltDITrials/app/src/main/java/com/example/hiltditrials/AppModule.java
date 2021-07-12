package com.example.hiltditrials;

import android.content.Context;

import com.example.hiltditrials.dependency.LogPrinter;
import com.example.hiltditrials.dependency.Printer;
import com.example.hiltditrials.dependency.TextMaker;
import com.example.hiltditrials.dependency.ToastPrinter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ActivityComponent.class)
public class AppModule {

    @Provides
    @Named("log")
    public Printer getLogPrinter(TextMaker textMaker) {
        return new LogPrinter(textMaker);
    }

    @Provides
    @Named("toast")
    public Printer getToastPrinter(TextMaker textMaker, @ApplicationContext Context context) {
        return new ToastPrinter(textMaker, context);
    }
}
