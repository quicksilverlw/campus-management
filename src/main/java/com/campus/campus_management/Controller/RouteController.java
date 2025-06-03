package com.campus.campus_management.Controller;

import com.campus.campus_management.service.MapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    @Autowired
    private MapApiService mapApiService;

    /**
     * 获取步行路线规划
     * @param startLat 起点纬度
     * @param startLng 起点经度
     * @param endLat 终点纬度
     * @param endLng 终点经度
     * @return 高德地图步行路径规划的JSON响应
     */
    @GetMapping(value = "/walking", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> getWalkingRoute(
            @RequestParam double startLat, @RequestParam double startLng,
            @RequestParam double endLat, @RequestParam double endLng) {
        String origin = startLng + "," + startLat; // 高德API要求经度在前
        String destination = endLng + "," + endLat; // 高德API要求经度在前

        return mapApiService.getWalkingRoute(origin, destination)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}