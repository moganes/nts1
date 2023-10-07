package com.spectrum.notes.repository;

import com.spectrum.notes.model.CMTS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CMTSRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CMTSRepository cmtsRepository;

    @Test
    public void cmtsQueryReturnsLastInsertedCmts() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minus(1L, ChronoUnit.DAYS);
        CMTS cmts1 = CMTS.builder()
                .docsisNatural("test_clli")
                .modifiedtime(yesterday.toString())
                .build();
        entityManager.persist(cmts1);
        entityManager.flush();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CMTS cmts2 = CMTS.builder()
                .docsisNatural("test_clli")
                .modifiedtime(now.toString())
                .build();
        entityManager.persist(cmts2);
        entityManager.flush();

        CMTS result = cmtsRepository.findTopByDocsisNaturalOrderByInsertDateTimeDesc("test_clli");

        assertEquals(cmts2.getModifiedtime(), result.getModifiedtime());
    }

}