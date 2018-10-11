package com.example.exchange.configuration;

import com.example.exchange.service.interfaces.CurrencyConverterService;
import com.jlupin.impl.client.util.JLupinClientUtil;
import com.jlupin.interfaces.client.delegator.JLupinDelegator;
import com.jlupin.interfaces.common.enums.PortType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
    "com.example.exchange",
    "com.jlupin.servlet.monitor.configuration"
})
public class ExchangeSpringConfiguration {
    @Bean
    public JLupinDelegator getJLupinDelegator() {
        return JLupinClientUtil.generateInnerMicroserviceLoadBalancerDelegator(PortType.JLRMC);
    }

    @Bean(name = "currencyConverterService")
    public CurrencyConverterService getCurrencyConverterService() {
        return JLupinClientUtil.generateRemote(getJLupinDelegator(), "currency-converter", CurrencyConverterService.class);
    }
}