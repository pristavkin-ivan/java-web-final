package com.epam.jwd.fitness_center.service.api;

import javax.servlet.http.Cookie;

public interface CurrencyService {

    Double convert(Cookie locale, Double dollarValue);

}
