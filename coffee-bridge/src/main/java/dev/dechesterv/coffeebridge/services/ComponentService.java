package dev.dechesterv.coffeebridge.services;

import dev.dechesterv.coffeemodels.agent.ComponentState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ComponentService {

    private Map<Long, ComponentState> cache = new HashMap<>();

    @Value("${component.check.period:10000}") //10 sec
    private Long period;

    //@Value("${component.check.delay:5000}") //5 sec
    //private Long delay;

    public long addComponent(ComponentState componentState) {
        long key = new Date().getTime();
        if (!cache.values().contains(componentState) && componentState.getId() == null) {
            componentState.setId(key);
            cache.put(key, componentState);
            log.info("Register component: " + componentState.toString());
            log.info("Update state: " + cache);
        }
        else
            return -1;
        return key;
    }

    public void removeComponent(Long id) {
        cache.remove(id);
        log.info("Remove component with id: " + id);
        log.info("Update state: " + cache);
    }

    @Scheduled(fixedRate = 5000) //5 sec
    public void checkHealth() {
        List<Long> ids = showAllComponents().stream()
                .map(ComponentState::getId)
                .collect(Collectors.toList());
        ids.forEach(id -> {
            if (checkTime(id))
                removeComponent(id);
        });
    }

    private boolean checkTime(Long t) {
        return t + period < new Date().getTime();
    }

    public List<ComponentState> showAllComponents() {
        return new ArrayList<>(cache.values());
    }

    public ComponentState showComponent(Long id) {
        return cache.get(id);
    }
}
