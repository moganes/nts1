package com.spectrum.notes.repository;

import com.spectrum.notes.model.CableModem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Manoj Gilla
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("asy")
@Transactional
public class CableModemRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CableModemRepository cableModemRepository;

    @Test
    public void cableModemQueryReturnsLastInsertedCableModem() {

        Stream.of(1, 2, 3, 4, 5)
            .map(i -> CableModem.builder()
                    .cmts_name("test-cmts.spectrum.com")
                    //.cableModemSid(UUID.randomUUID())
                    .cmts_id("test-first-modem" + i)
                    .build())
                .forEach(entityManager::persist);
        entityManager.flush();

        Stream.of(1, 2, 3, 4, 5)
                .map(i -> CableModem.builder()
                        .cmts_name("test-cmts2.spectrum.com")
                        //.cableModemSid(UUID.randomUUID())
                        .cmts_id("test-second-modem" + (i + 5))
                        .build())
                .forEach(entityManager::persist);
        entityManager.flush();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stream.of(1, 2, 3, 4, 5)
                .map(i -> CableModem.builder()
                        .cmts_name("test-cmts.spectrum.com")
                        //.cableModemSid(UUID.randomUUID())
                        .cmts_id("test-first-modem" + i)
                        .build())
                .forEach(entityManager::persist);
        Stream.of(1, 2, 3, 4, 5)
                .map(i -> CableModem.builder()
                        .cmts_name("test-cmts2.spectrum.com")
                        //.cableModemSid(UUID.randomUUID())
                        .cmts_id("test-second-modem" + (i + 5))
                        .build())
                .forEach(entityManager::persist);
        entityManager.flush();

        List<CableModem> results = cableModemRepository.findTopNByCmtsId("test-cmts.%");

        assertEquals(1, results.size());
    }
}