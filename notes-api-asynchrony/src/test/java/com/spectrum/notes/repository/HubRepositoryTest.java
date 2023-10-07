package com.spectrum.notes.repository;

import com.spectrum.notes.model.Hub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HubRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HubRepository hubRepository;

    @Test
    public void cmtsQueryReturnsLastInsertedCmts() {
       // UUID uuid1 = UUID.randomUUID();
       // UUID uuid2 = UUID.randomUUID();
        Hub hub1 = Hub.builder()
                .hubName("UNION CITY:TEST")
         //       .hubSid(uuid1)
                .build();
        entityManager.persist(hub1);
        entityManager.flush();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Hub hub2 = Hub.builder()
            //    .hubSid(uuid2)
                .hubName("GRIFFIN:TEST")
                .build();
        entityManager.persist(hub2);
        entityManager.flush();

        Hub result = hubRepository.findByHubNameEquals("GRIFFIN:TEST");
        List<Hub> hubs = hubRepository.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC, "insertDateTime")));
        assertEquals(result.getInsertDateTime(), hubs.get(0).getInsertDateTime());
    }
}