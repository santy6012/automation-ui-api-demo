package com.org.utils;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

public class Properties {
    private static final EnvironmentVariables enVars = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();

    public static String getWebDriverBaseUrl(){
        return EnvironmentSpecificConfiguration.from(enVars).getProperty("webdriver.base.url");
    }

    public static String getApiBaseUrl(){
        return EnvironmentSpecificConfiguration.from(enVars).getProperty("api.base.url");
    }

}
