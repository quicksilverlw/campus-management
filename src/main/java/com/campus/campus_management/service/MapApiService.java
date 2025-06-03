package com.campus.campus_management.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MapApiService {

    @Value("${amap.web.api.key}")
    private String amapApiKey;

    private final WebClient webClient;

    public MapApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://restapi.amap.com/v3").build();
    }

    /**
     * 调用高德地图步行路径规划API
     * @param origin 规划起点，格式 "经度,纬度"
     * @param destination 规划终点，格式 "经度,纬度"
     * @return 步行路线规划的JSON字符串
     */
    public Mono<String> getWalkingRoute(String origin, String destination) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/direction/walking")
                        .queryParam("origin", origin)
                        .queryParam("destination", destination)
                        .queryParam("key", amapApiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    /**
     * 调用高德地图POI搜索API (可选，前端直接调用JS API更常见)
     * @param keywords 搜索关键词
     * @param city 城市，例如 "兰州"
     * @return POI搜索结果的JSON字符串
     */
    public Mono<String> searchPOIs(String keywords, String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/place/text")
                        .queryParam("keywords", keywords)
                        .queryParam("city", city)
                        .queryParam("output", "json")
                        .queryParam("key", amapApiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}