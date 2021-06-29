package com.example.hiltditrials;

import javax.inject.Inject;

public class AnalyticsAdapter {

    private final AnalyticsService service;

    @Inject
    AnalyticsAdapter(AnalyticsService service) {
        this.service = service;
    }
}-