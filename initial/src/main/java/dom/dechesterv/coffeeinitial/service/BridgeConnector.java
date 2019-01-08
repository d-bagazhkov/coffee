package dom.dechesterv.coffeeinitial.service;

import dom.dechesterv.coffeemodels.agent.ComponentState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.Inet4Address;
import java.net.UnknownHostException;

@Service
public class BridgeConnector {

    @Value("${server.port:8080}")
    private int portApp;

    @Value("${application.name:unknown}")
    private String nameApp;

    @Value("${bridge.host:127.0.0.1}")
    private String bridgeHost;

    @Value("${bridge.port:7700}")
    private int bridgePort;

    private RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    private void initApp() {
        ComponentState componentState = new ComponentState();
        try {
            componentState.setHost(Inet4Address.getLocalHost().getHostAddress());
            componentState.setName(nameApp);
            componentState.setPort(portApp);
            restTemplate.postForEntity(absoluteUri(bridgeHost, bridgePort) + "/component/add", componentState, String.class);
        } catch (UnknownHostException e) {
            throw new IllegalStateException("Param bridge.host not correct! (" + bridgeHost + ")");
        }
    }

    private String absoluteUri(String host, Integer port) {
        return "http://" + host + ":" + port + "/";
    }

}
