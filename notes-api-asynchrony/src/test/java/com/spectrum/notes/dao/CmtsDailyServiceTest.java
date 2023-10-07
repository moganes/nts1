package com.spectrum.notes.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class CmtsDailyServiceTest {
//    @Mock
//    private InfluxDB mockInfluxDB;
    private CmtsDailyService testObject;

    @Before
    public void before() {
//        Mockito.reset(mockInfluxDB);
//        testObject = new InfluxDBClient(mockInfluxDB);
    }

    @Test
    public void getCmtsDailyByIdReturnsEmptyOptionalWhenNoResult() {
//        QueryResult queryResult = Mockito.mock(QueryResult.class);
//        Mockito.when(mockInfluxDB.query(Mockito.any())).thenReturn(queryResult);
//
//        CompletableFuture<Optional<CmtsDaily>> result = testObject.getCmtsDailyById("id");
//        try {
//            Assertions.assertThat(result.get().isPresent()).isEqualTo(false);
//        } catch (Exception e) {
//            fail();
//        }
    }

//    @Test
//    public void getCmtsDailyByIdReturnsNonEmptyOptionalWhenOneResult() {
//        QueryResult.Result uniqueResult = Mockito.mock(QueryResult.Result.class, Mockito.RETURNS_DEEP_STUBS);
//        QueryResult queryResult = Mockito.mock(QueryResult.class, Mockito.CALLS_REAL_METHODS);
//        queryResult.setResults(Collections.singletonList(uniqueResult));
//        Mockito.when(mockInfluxDB.query(Mockito.any())).thenReturn(queryResult);
//
//        CompletableFuture<Optional<CmtsDaily>> result = testObject.getCmtsDailyById("id");
//        try {
//            Assertions.assertThat(result.get().isPresent()).isEqualTo(true);
//        } catch (Exception e) {
//            fail();
//        }
//    }

    @Test
    public void getCableModem5MinByCmtsReturnsEmptyOptionalWhenNoResult() {
//        QueryResult queryResult = Mockito.mock(QueryResult.class);
//        Mockito.when(mockInfluxDB.query(Mockito.any())).thenReturn(queryResult);
//
//        CompletableFuture<Optional<Collection<CableModem5Min>>> result = testObject.getCableModem5MinByCmts("id");
//        try {
//            Assertions.assertThat(result.get().isPresent()).isEqualTo(false);
//        } catch (Exception e) {
//            fail();
//        }
    }

    @Test
    public void getCmtsHash() {
//        String test = "cleanse, fold, manipulate";
//
//        int result = testObject.getCmtsHash(test, 10);
//
//        int expected = 6;
//        assertEquals(expected, result);
    }
}