package dom.dechesterv.shadowdatabase.dao;

import dom.dechesterv.coffeemodels.entity.Consumer;
import dom.dechesterv.shadowdatabase.config.DumbState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Repository
public class ConsumerDao {

    private DumbState dumbState;

    public Consumer addConsumer(Consumer consumer) {
        if (dumbState != null) {
            consumer.setId(new Random().nextLong());
            dumbState.getDumbCacheConsumers().add(consumer);
        } else {
            //TODO:add connection to db
        }
        return null;
    }

    public Consumer updateConsumer(Consumer consumer) {
        if (dumbState != null) {
            dumbState.setDumbCacheConsumers(dumbState.getDumbCacheConsumers().stream()
                    .filter(a -> !a.getId().equals(consumer.getId()))
                    .collect(Collectors.toList()));
            dumbState.getDumbCacheConsumers().add(consumer);
            return consumer;
        } else {
            //TODO:add connection to db
        }
        return null;
    }

    @Autowired
    public void setDumbState(DumbState dumbState) {
        this.dumbState = dumbState;
    }

    public void removeConsumer(Long id) {
        if (dumbState != null) {
            dumbState.setDumbCacheConsumers(dumbState.getDumbCacheConsumers().stream()
                    .filter(a -> !a.getId().equals(id))
                    .collect(Collectors.toList()));
        } else {
            //TODO:add connection to db
        }
    }

    public Consumer getConsumer(Long id) {
        if (dumbState != null) {
            return dumbState.getDumbCacheConsumers().stream()
                    .filter(a -> a.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        } else {
            //TODO:add connection to db
        }
        return null;
    }

    public List<Consumer> getAllConsumers() {
        if (dumbState != null) {
            return dumbState.getDumbCacheConsumers();
        } else {
            //TODO:add connection to db
        }
        return null;
    }
}
