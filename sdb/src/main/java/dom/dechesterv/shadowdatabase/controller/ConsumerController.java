package dom.dechesterv.shadowdatabase.controller;

import dom.dechesterv.coffeemodels.entity.Consumer;
import dom.dechesterv.shadowdatabase.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsumerController {
    private ConsumerService consumerService;

    @PostMapping("/add")
    public Long addConsumer(@RequestBody Consumer consumer) {
        return consumer.getId() != null ? consumerService.updateConsumer(consumer) : consumerService.addConsumer(consumer);
    }

    @GetMapping("/remove/{id}")
    public void removeConsumer(@PathVariable Long id) {
        consumerService.removeConsumer(id);
    }

    @GetMapping("/get/{id}")
    public Consumer getConsumer(@PathVariable Long id) {
        return consumerService.getConsumer(id);
    }

    @GetMapping("/get/all")
    public List<Consumer> getAllConsumers() {
        return consumerService.getAllConsumers();
    }

    @Autowired
    private void setConsumerService(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }
}
