package com.example.cachedemo.service;

import com.example.cachedemo.model.Airport;
import com.example.cachedemo.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportService {

    @Autowired
    private AirportRepository repository;

    @Autowired
    private final CacheManager cacheManager;

    @Cacheable("AirportCache")
    public Airport getCode(String id){
        return fetchAirportFromCache(id);
    }
    private Airport fetchAirportFromCache(String id){
        Cache cache = cacheManager.getCache("AirportCache");
        Airport cachedAirport = cache.get(id, Airport.class);

        if(cachedAirport != null){
            return cachedAirport;
        } else {
            Airport dbAirport = fetchAirportFromDB(id);

            return dbAirport;
        }
    }

    private Airport fetchAirportFromDB(String id){
        return repository.findById(id).get();
    }

//    @Cacheable(value = "AirportName", key = "name")
//    public String getName(String id){
//        return fetchNameFromCache(id);
//    }
//
//    private String fetchNameFromCache(String id){
//        Cache cache = cacheManager.getCache("AirportName");
//        String cachedCode = cache.get(id, String.class);
//
//        if(cachedCode != null){
//            return cachedCode;
//        } else {
//            String dbCode = fetchCodeFromDB(id);
//            cache.put(id, dbCode);
//
//            return dbCode;
//        }
//    }
//
//    private String fetchNameFromDB(String id){
//        return repository.findById(id).get().getName();
//    }

    public Airport createNew(Airport airport){
        return repository.save(airport);
    }
}
