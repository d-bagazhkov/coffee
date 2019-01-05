package dom.dechesterv.coffeebridge.services;

import dom.dechesterv.coffeemodels.agent.ComponentState;
import dom.dechesterv.coffeemodels.agent.KeywordHealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static dom.dechesterv.coffeemodels.agent.KeywordHealthCheck.ENABLED;

@Service
public class ComponentService {

    private List<ComponentState> cache = new ArrayList<>();
    private List<ComponentState> listenerUnstableState = new ArrayList<>();

    private RestTemplate restTemplate;

    @Value("${component.garbageRemoval.delay:12000}")
    private Long garbageRemovalDelay;

    public void addComponent(ComponentState componentState) {
        if (!cache.contains(componentState) && componentState.getId() == null) {
            componentState.setId(cache.size());
            componentState.setDate(new Date());
            componentState.setLastStatusChange(componentState.getDate());
            cache.add(componentState);
        }
    }

    public void removeComponent(Integer id) {
        removeComponentFromCache(id);
    }

    @Scheduled(fixedDelayString = "${component.check.delay:1500}")
    public void checkHealth() {
        cache.forEach(cs -> {
            KeywordHealthCheck check;
            if ((check = getComponentStatus(cs.getHost(), cs.getPort())) != null
                    && ENABLED.getKeyword().equals(check.getKeyword()))
                removeComponentFromListener(cs.getId());
            else if (listenerUnstableState.contains(cs)) {
                if (cs.getLastStatusChange().getTime() + garbageRemovalDelay < new Date().getTime()) {
                    removeComponentFromCache(cs.getId());
                    removeComponentFromListener(cs.getId());
                }
            } else {
                cs.setLastStatusChange(new Date());
                listenerUnstableState.add(cs);
            }
        });
    }

    private void removeComponentFromListener(Integer idComponent) {
        listenerUnstableState = listenerUnstableState.stream()
                .filter(l -> !l.getId().equals(idComponent))
                .collect(Collectors.toList());
    }

    private void removeComponentFromCache(Integer idComponent) {
        cache = cache.stream()
                .filter(l -> !l.getId().equals(idComponent))
                .collect(Collectors.toList());
    }

    public List<ComponentState> getAllComponents() {
        return cache;
    }

    public ComponentState getComponent(Integer id) {
        return getComponentState(id);
    }

    private ComponentState getComponentState(Integer id) {
        return cache.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private KeywordHealthCheck getComponentStatus(String host, Integer port) {
        return getComponentStatus(host, port, "/health/check");
    }

    private KeywordHealthCheck getComponentStatus(String host, Integer port, String path) {
        KeywordHealthCheck keyword = null;
        try {
            keyword = restTemplate.postForObject(getAbsoluteUrl(host, port, path), KeywordHealthCheck.HEALTH_CHECK, KeywordHealthCheck.class);
        } catch (Exception ignored) {
        }
        return keyword;
    }

    private String getAbsoluteUrl(String host, Integer port, String path) {
        return "http://" + host + ":" + port + (path.startsWith("/") ? path : ("/" + path));
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
