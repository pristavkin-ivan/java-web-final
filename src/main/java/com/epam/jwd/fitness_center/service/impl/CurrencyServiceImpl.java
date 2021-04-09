package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.service.api.CurrencyService;

import javax.servlet.http.Cookie;

public enum CurrencyServiceImpl implements CurrencyService {
    INSTANCE;

    private final static String BY = "be_BY";

    private final static String RU = "ru_RU";

    @Override
    public Double convert(Cookie locale, Double dollarValue) {
        switch (locale.getValue()) {
            case RU: {
                return dollarValue * 77.1;
            }
            case BY: {
                return dollarValue * 2.66;
            }
        }
        return dollarValue;
    }
}
