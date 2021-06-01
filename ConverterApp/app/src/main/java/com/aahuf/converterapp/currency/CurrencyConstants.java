package com.aahuf.converterapp.currency;


import com.aahuf.converterapp.R;

public class CurrencyConstants {

    public static final String DEFAULT_CURRENCY_PREFERENCE = "default_currency_preference";
    public static final String SELECTED_CURRENCIES_PREFERENCE = "selected_currencies_preference";
    public static final String DECIMAL_PLACES_PREFERENCE = "decimal_places_preference";

    public static final String[] CURRENCY_NAMES =
            {
                    "EUR", "USD", "JPY", "BGN",
                    "CZK", "DKK", "GBP", "HUF",
                    "PLN", "RON", "SEK", "CHF",
                    "NOK", "HRK", "RUB", "TRY",
                    "AUD", "BRL", "CAD", "CNY",
                    "HKD", "IDR", "ILS", "INR",
                    "ISK", "KRW", "MXN", "MYR",
                    "NZD", "PHP", "SGD", "THB",
                    "ZAR", "EXT"
            };

    public static final String[] CURRENCY_SYMBOLS =
            {
                    "€", "$", "¥", "лв",
                    "Kč", "kr", "£", "Ft",
                    "zł", "lei", "kr", "",
                    "kr", "kn", "₽", "₺",
                    "$", "R$", "$", "¥",
                    "$", "Rp", "₪", "₹",
                    "kr", "₩", "$", "RM",
                    "$", "₱", "$", "฿",
                    "S", ""
            };

    public static final Integer[] CURRENCY_LONG_NAMES =
            {
                    R.string.long_eur, R.string.long_usd, R.string.long_jpy,
                    R.string.long_bgn, R.string.long_czk, R.string.long_dkk,
                    R.string.long_gbp, R.string.long_huf, R.string.long_pln,
                    R.string.long_ron, R.string.long_sek, R.string.long_chf,
                    R.string.long_nok, R.string.long_hrk, R.string.long_rub,
                    R.string.long_try, R.string.long_aud, R.string.long_brl,
                    R.string.long_cad, R.string.long_cny, R.string.long_hkd,
                    R.string.long_idr, R.string.long_ils, R.string.long_inr,
                    R.string.long_isk, R.string.long_krw, R.string.long_mxn,
                    R.string.long_myr, R.string.long_nzd, R.string.long_php,
                    R.string.long_sgd, R.string.long_thb, R.string.long_zar,
                    R.string.long_ext
            };

    // Currency flags
    public static final Integer[] CURRENCY_FLAGS =
            {
                    R.drawable.flag_eur, R.drawable.flag_usd, R.drawable.flag_jpy,
                    R.drawable.flag_bgn, R.drawable.flag_czk, R.drawable.flag_dkk,
                    R.drawable.flag_gbp, R.drawable.flag_huf, R.drawable.flag_pln,
                    R.drawable.flag_ron, R.drawable.flag_sek, R.drawable.flag_chf,
                    R.drawable.flag_nok, R.drawable.flag_hrk, R.drawable.flag_rub,
                    R.drawable.flag_try, R.drawable.flag_aud, R.drawable.flag_brl,
                    R.drawable.flag_cad, R.drawable.flag_cny, R.drawable.flag_hkd,
                    R.drawable.flag_idr, R.drawable.flag_ils, R.drawable.flag_inr,
                    R.drawable.flag_isk, R.drawable.flag_kpw, R.drawable.flag_mxn,
                    R.drawable.flag_myr, R.drawable.flag_nzd, R.drawable.flag_php,
                    R.drawable.flag_sgd, R.drawable.flag_thb, R.drawable.flag_zar,
                    R.drawable.flag_ext
            };
}
