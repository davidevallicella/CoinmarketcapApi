package com.coinmarketcup.comparators;

import com.coinmarketcup.models.Currency;

import java.util.Comparator;

/**
 * Created by Davide Vallicella on 05.08.2017.
 */

public class ComparatorByPercentage implements Comparator<Currency> {
    @Override
    public int compare(Currency currency_1, Currency currency_2) {
        if (currency_1.percentChange24h > currency_2.percentChange24h) {
            return -1;
        } else if (currency_1.percentChange24h < currency_2.percentChange24h) {
            return 1;
        } else {
            return 0;
        }
    }
}