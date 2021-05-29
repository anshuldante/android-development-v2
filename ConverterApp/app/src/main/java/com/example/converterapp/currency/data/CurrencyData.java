package com.example.converterapp.currency.data;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.example.converterapp.R;
import com.example.converterapp.currency.model.ConversionRatesModel;
import com.example.converterapp.currency.model.CurrencyModel;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static com.example.converterapp.currency.CurrencyConstants.CURRENCY_FLAGS;
import static com.example.converterapp.currency.CurrencyConstants.CURRENCY_LONGNAMES;
import static com.example.converterapp.currency.CurrencyConstants.CURRENCY_NAMES;
import static com.example.converterapp.currency.CurrencyConstants.CURRENCY_SYMBOLS;

public class CurrencyData {

    private final CurrencyModelFactory currencyModelFactory;
    private final ConversionRatesTask conversionRatesTask;
    private RequestQueue requestQueue;
    private final Context context;

    public CurrencyData(Context context) {
        this.context = context;
        currencyModelFactory = new CurrencyModelFactory();
        conversionRatesTask = new ConversionRatesTask();
    }

    public List<CurrencyModel> getCurrencyModels() {
        return currencyModelFactory.models;
    }

    public ConversionRatesModel getConversionRates() {
        return conversionRatesTask.conversionRatesModel;
    }


    private static class CurrencyModelFactory {
        private final List<CurrencyModel> models;

        private CurrencyModelFactory() {
            models = new ArrayList<>();
            int length = CURRENCY_FLAGS.length;
            CurrencyModel currencyModel;

            for (int i = 0; i < length; i++) {
                currencyModel = new CurrencyModel();
                currencyModel.setCurrencyName(CURRENCY_NAMES[i]);
                currencyModel.setCurrencyLongName(CURRENCY_LONGNAMES[i]);
                currencyModel.setCurrencySymbol(CURRENCY_SYMBOLS[i]);
                currencyModel.setFlagImage(CURRENCY_FLAGS[i]);
                currencyModel.setCurrencyAmount(0.0);

                models.add(currencyModel);
            }
        }
    }

    private class ConversionRatesTask {

        private ConversionRatesModel conversionRatesModel;

        private ConversionRatesTask() {
            buildConversionRatesModel();
        }

        private void buildConversionRatesModel() {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, context.getResources().getString(R.string.ebc_daily_url),
                    this::parseResponse, error ->
                    Log.e("Currency Data Error", "exception while parsing conversion rates {}", error));
            getRequestQueue().add(stringRequest);
        }

        private void parseResponse(String response) {
            conversionRatesModel = new ConversionRatesModel();
            try {
                Handler handler = new Handler(conversionRatesModel);
                Xml.parse(new ByteArrayInputStream(response.getBytes()), Xml.Encoding.UTF_8, handler);
            } catch (Exception e) {
                Log.e("Currency Data Error", "exception while fetching conversion rates {}", e);
            }
            Log.i("Happy Tag", "conversion rates pulled: " + conversionRatesModel.toString());
        }

        private RequestQueue getRequestQueue() {
            if (requestQueue == null) {
                Cache cache = new DiskBasedCache(new File("/"), 1024 * 1024); // 1MB cap
                Network network = new BasicNetwork(new HurlStack());
                requestQueue = new RequestQueue(cache, network);
                requestQueue.start();
            }
            return requestQueue;
        }
    }

    private static class Handler extends DefaultHandler {
        private final MathContext MATH_CONTEXT = new MathContext(4, RoundingMode.CEILING);
        private final ConversionRatesModel model;

        private Handler(ConversionRatesModel model) {
            this.model = model;
        }

        // Start element
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) {
            String name = "EUR";
            double rate;

            if (localName.equals("Cube")) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    switch (attributes.getLocalName(i)) {
                        case "time":
                            model.setUpdatedDate(attributes.getValue(i));
                            break;

                        case "currency":
                            name = attributes.getValue(i);
                            break;

                        case "rate":
                            try {
                                rate = Double.parseDouble(attributes.getValue(i));
                            } catch (Exception e) {
                                rate = 1.0;
                            }
                            model.getConversionRates().put(name, new BigDecimal(rate, MATH_CONTEXT));
                            break;
                    }
                }
            }
        }
    }
}
