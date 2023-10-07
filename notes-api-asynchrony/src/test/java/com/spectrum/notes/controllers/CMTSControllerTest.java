package com.spectrum.notes.controllers;

import com.spectrum.notes.exception.NotUniqueResultException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

//@RunWith(SpringRunner.class)
//@WebMvcTest
public class CMTSControllerTest {

//    @Autowired
    private MockMvc mockMvc;
//    @Mock
//    private InfluxDBClient mockInfluxDBClient;

    private CMTSController testObject;

//    @Before
    public void before() {
//        reset(mockInfluxDBClient);
//        testObject = new CMTSController(mockInfluxDBClient);
    }

//    @Test
    public void getCmtsDailyByIdReturns200AndBody() {

//        CmtsDaily mockCmts = mock(CmtsDaily.class);
//        when(mockInfluxDBClient.getCmtsDailyById("id")).thenReturn(CompletableFuture.completedFuture(Optional.of(mockCmts)));
//
//        try {
//            ResponseEntity<CmtsDaily> responseEntity = testObject.getCmtsDailyById("id");
//
//            assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
//            assertThat(responseEntity.getBody()).isEqualTo(mockCmts);
//        } catch (Exception e) {
//
//        }
    }

//    @Test
    public void getCmtsDailyByIdReturns204() {
//        when(mockInfluxDBClient.getCmtsDailyById("id")).thenReturn(CompletableFuture.completedFuture(Optional.empty()));
//
//        try {
//            ResponseEntity<CmtsDaily> responseEntity = testObject.getCmtsDailyById("id");
//            assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
//        } catch (Exception e) {
//            fail("Should not have thrown an exception");
//        }
    }

//    @Test(expected = NotUniqueResultException.class)
    public void getCmtsDailyByIdThrows() {
//        when(mockInfluxDBClient.getCmtsDailyById("id")).thenThrow(new NotUniqueResultException());
//
//        try {
//            ResponseEntity<CmtsDaily> responseEntity = testObject.getCmtsDailyById("id");
//            assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
//        } catch (InterruptedException ie) {
//            fail("Should not have thrown an interrupted exception");
//        } catch (ExecutionException ee) {
//            fail("Should not have thrown an execution exception");
//        }
        throw new NotUniqueResultException();
    }

//    @Test
    public void getCableModemFiveMinByCmtsId() {
//        CableModem5Min mockData = mock(CableModem5Min.class);
//        when(mockInfluxDBClient.getCableModem5MinByCmts("id")).thenReturn(CompletableFuture.completedFuture(Optional.of(Collections.singletonList(mockData))));
//
//        try {
//            ResponseEntity<Collection<CableModem5Min>> responseEntity = testObject.getCableModemFiveMinByCmtsId("id");
//            assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
//            assertThat(responseEntity.getBody()).contains(mockData);
//        } catch (Exception e) {
//            fail("Should not have thrown an exception");
//        }
    }

//    @Test
    public void getCableModemDailyByCmtsId() {
//        CableModemDaily mockData = mock(CableModemDaily.class);
//        when(mockInfluxDBClient.getCableModemDailyByCmts("id")).thenReturn(CompletableFuture.completedFuture(Optional.of(Collections.singletonList(mockData))));
//
//        try {
//            ResponseEntity<Collection<CableModemDaily>> responseEntity = testObject.getCableModemDailyByCmtsId("id");
//            assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
//            assertThat(responseEntity.getBody()).contains(mockData);
//        } catch (Exception e) {
//            fail("Should not have thrown an exception");
//        }
    }

//    @Test
    public void getCableModemByCmtsId() {
//        CableModem mockData = mock(CableModem.class);
//        when(mockInfluxDBClient.getCableModemByCmts("id")).thenReturn(CompletableFuture.completedFuture(Optional.of(Collections.singleton(mockData))));
//
//        try {
//            ResponseEntity<Collection<CableModem>> responseEntity = testObject.getCableModemByCmtsId("id");
//            assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
//            assertThat(responseEntity.getBody()).contains(mockData);
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Should not have thrown an exception");
//        }
    }
}
