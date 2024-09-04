package ru.itmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(CatClientWeb.class)
public class CatClientConfiguration {

    private final RestTemplate restTemplate;

    @Autowired
    public CatClientConfiguration(@Value("${serviceCat.url}")String address, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri(address).build();
    }

    @Bean
    public RestTemplate provideCatRestTemplate() {
        return restTemplate;
    }
}