package dev.dechesterv.coffeebridge.controllers;

import dev.dechesterv.coffeebridge.services.ComponentService;
import dev.dechesterv.coffeemodels.agent.ComponentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/component")
public class ComponentController {

    private ComponentService componentService;

    @PostMapping("/add")
    public long add(@RequestBody ComponentState componentState) {
        return componentService.addComponent(componentState);
    }

    @GetMapping("/remove/{id}")
    public void remove(@PathVariable Long id) {
        componentService.removeComponent(id);
    }

    @GetMapping("/show/all")
    public List<ComponentState> showAllComponents() {
        return componentService.showAllComponents();
    }

    @GetMapping("/show/{id}")
    public ComponentState showAllComponents(@PathVariable Long id) {
        return componentService.showComponent(id);
    }

    @Autowired
    public void setComponentService(ComponentService componentService) {
        this.componentService = componentService;
    }

}
