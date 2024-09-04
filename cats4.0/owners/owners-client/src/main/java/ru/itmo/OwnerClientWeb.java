package ru.itmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ru.itmo.models.OwnerDto;

import java.util.List;

public class OwnerClientWeb implements OwnerClient {
    private final RestTemplate restTemplate;
    private final String address;

    @Autowired
    public OwnerClientWeb(@Value("${serviceOwner.url}")String address,
                          @Qualifier("provideOwnerRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.address = address;
    }

    public OwnerDto findById(int id) {
        return restTemplate.getForEntity("/" + id, OwnerDto.class).getBody();
    }
    public List<OwnerDto> getAll() {
        return exchangeAsList(address, new ParameterizedTypeReference<>() {});
    }

    private  <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
    }
}