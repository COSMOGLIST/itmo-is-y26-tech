package ru.itmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.itmo.models.CatDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CatClientWeb implements CatClient {
    private final String address;

    private final RestTemplate restTemplate;

    @Autowired
    public CatClientWeb(@Value("${serviceCat.url}")String address,
                        @Qualifier("provideCatRestTemplate") RestTemplate restTemplate) {
        this.address = address;
        this.restTemplate = restTemplate;
    }

    public CatDto findById(int id) {
        return restTemplate.getForEntity("/" + id, CatDto.class).getBody();
    }

    public List<CatDto> getCatsByCriteria(String color, String breed, String id, String ownerId) {
        return exchangeAsList(addressCreation(color, breed, id, ownerId), new ParameterizedTypeReference<>() {});
    }

    private  <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
    }

    private String addressCreation(String color, String breed, String id, String ownerId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(address)
                .queryParamIfPresent("color", Optional.ofNullable(color))
                .queryParamIfPresent("breed", Optional.ofNullable(breed))
                .queryParamIfPresent("id", Optional.ofNullable(id))
                .queryParamIfPresent("ownerId", Optional.ofNullable(ownerId));
        return builder.buildAndExpand().toString();
    }
}