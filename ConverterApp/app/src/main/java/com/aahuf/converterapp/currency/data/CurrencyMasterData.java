package com.aahuf.converterapp.currency.data;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.aahuf.converterapp.R;
import com.aahuf.converterapp.currency.CurrencyConstants;
import com.aahuf.converterapp.currency.model.CurrencyModel;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class CurrencyMasterData {

    private final CurrencyModelFactory currencyModelFactory;
    private final Consumer<BigDecimal> consumer;
    private RequestQueue requestQueue;
    private final Context context;
    private String updatedDate;
    private final int scale;

    public CurrencyMasterData(Context context, Consumer<BigDecimal> consumer, int scale) {
        this.context = context;
        this.consumer = consumer;
        this.scale = scale;
        currencyModelFactory = new CurrencyModelFactory();
    }

    public Map<String, CurrencyModel> getCurrencyModelMap() {
        return currencyModelFactory.currencyModelMap;
    }

    private class CurrencyModelFactory {
        private final Map<String, CurrencyModel> currencyModelMap;

        private CurrencyModelFactory() {
            int length = CurrencyConstants.CURRENCY_FLAGS.length;
            currencyModelMap = new HashMap<>(length, 1);
            CurrencyModel currencyModel;

            for (int i = 0; i < length; i++) {
                currencyModel = new CurrencyModel();
                currencyModel.setCurrencyName(CurrencyConstants.CURRENCY_NAMES[i]);
                currencyModel.setCurrencyLongName(CurrencyConstants.CURRENCY_LONG_NAMES[i]);
                currencyModel.setCurrencySymbol(CurrencyConstants.CURRENCY_SYMBOLS[i]);
                currencyModel.setFlagImage(CurrencyConstants.CURRENCY_FLAGS[i]);
                currencyModel.setCurrencyAmount(new BigDecimal("1.0").setScale(scale, RoundingMode.HALF_UP));
                currencyModel.setCurrencyRate(new BigDecimal("1.0").setScale(scale, RoundingMode.HALF_UP));
                currencyModelMap.put(CurrencyConstants.CURRENCY_NAMES[i], currencyModel);
            }
            new ConversionRatesTask();
        }
    }

    private class ConversionRatesTask {

        private ConversionRatesTask() {
            populateConversionRates();
        }

        private void populateConversionRates() {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, context.getResources().getString(R.string.ebc_daily_url),
                    this::parseResponse, error ->
                    Log.e("Currency Data Error", "exception while parsing conversion rates {}", error));
            getRequestQueue().add(stringRequest);
        }

        private void parseResponse(String response) {
            try {
                Handler handler = new Handler();
                Xml.parse(new ByteArrayInputStream(response.getBytes()), Xml.Encoding.UTF_8, handler);
                consumer.accept(new BigDecimal("1.0").setScale(scale, RoundingMode.HALF_UP));
            } catch (Exception e) {
                Log.e("Currency Data Error", "exception while fetching conversion rates {}", e);
            }
            Log.e("Currency Data Error", "conversion rates pulled at: " + updatedDate + " with values: " + getCurrencyModelMap().values().stream().map(CurrencyModel::getCurrencyRate));
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

    private class Handler extends DefaultHandler {
        private Handler() {
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
                            updatedDate = attributes.getValue(i);
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
                            Objects.requireNonNull(getCurrencyModelMap().get(name)).setCurrencyRate(new BigDecimal(rate).setScale(scale, RoundingMode.HALF_UP));
                            break;
                    }
                }
            }
        }
    }
}
