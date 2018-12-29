package dom.dechesterv.shadowdatabase.service;

import dom.dechesterv.coffeemodels.entity.Consumer;
import dom.dechesterv.shadowdatabase.dao.ConsumerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {
    private ConsumerDao consumerDao;

    public Long addConsumer(Consumer consumer) {
        return consumerDao.addConsumer(consumer).getId();
    }

    public Long updateConsumer(Consumer consumer) {
        return consumerDao.updateConsumer(consumer).getId();
    }

    public void removeConsumer(Long id) {
        consumerDao.removeConsumer(id);
    }

    public Consumer getConsumer(Long id) {
        return consumerDao.getConsumer(id);
    }


    public List<Consumer> getAllConsumers() {
        return consumerDao.getAllConsumers();
    }

    @Autowired
    public void setArticleDao(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }
}
