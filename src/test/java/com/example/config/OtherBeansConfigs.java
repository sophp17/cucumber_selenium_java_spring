//package com.example.config;
//
//import com.example.annotations.LazyConfiguration;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Scope;
//
//@LazyConfiguration
//public class OtherBeansConfigs {
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public JavascriptExecutor javascriptExecutor(WebDriver driver) {
//        return (JavascriptExecutor) driver;
//    }
//}