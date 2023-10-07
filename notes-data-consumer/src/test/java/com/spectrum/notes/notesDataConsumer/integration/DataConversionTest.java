package com.spectrum.notes.notesDataConsumer.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spectrum.notes.notesDataConsumer.model.complexEntities.SNMPMonMetrics;
import com.spectrum.notes.notesDataConsumer.model.edge.CableModemEdge;
import com.spectrum.notes.notesDataConsumer.model.protJava.AddressBookProtos;
import grpc.Equipment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DataConversionTest {


    @Test
    public void jsonTest() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String fullMessage = "{\"metric\":\"cmts.interface.admin\"}";

        SNMPMonMetrics cmts = mapper.readValue(fullMessage, SNMPMonMetrics.class);
        System.out.println();

    }

    @Test
    public void test() throws IOException {
        Equipment.CableModem cableModem
                = Equipment.CableModem.newBuilder()
                .mergeFrom(
                        new FileInputStream("./data/errors/dirty.cablemodem-0da7d3a4c838fdc58e6ab95e697a39df7625f959")
                ).build();
        System.out.println();
    }


    @Test
    public void edgeToNotesDirtyEquipmentModule_test() throws IOException {
        Equipment.Entity entity
                = Equipment.Entity.newBuilder()
                .mergeFrom(
                        new FileInputStream("./data/errors/dirty.equipmentchassis-1d87e04ef68c83d2026aa27207f602e8816eb111")
                ).build();

        System.out.println(entity.getEClass().name());

    }


    @Test
    public void edgeToNotesCableModeMConversation_test() throws IOException {
        Equipment.CableModem cableModem
                = Equipment.CableModem.newBuilder()
                .mergeFrom(
                        new FileInputStream("./cableModem")
                ).build();

        CableModemEdge cableModemEdge = new CableModemEdge();
        cableModemEdge = cableModemEdge.convertTo(cableModem, UUID.randomUUID().toString());
        assertEquals(cableModem.getIdx(), cableModemEdge.getIdx());
        assertEquals(cableModem.getUptime(), cableModemEdge.getUptime());
        assertEquals(cableModem.getStatus(), cableModemEdge.getStatus());
        assertEquals(cableModem.getSgidx(), cableModemEdge.getSgidx());
        assertEquals(cableModem.getMdidx(), cableModemEdge.getMdidx());
        //TODO: add the list entities as well
    }

    @Test
    public void edgeToNotesInterfaceConversation_test() throws IOException {
        Equipment.Interface anInterface
                = Equipment.Interface.newBuilder()
                .mergeFrom(
                        new FileInputStream("./data/dirtyInterface/dirty.interface-0a91eb1d771e48fcb4bc314bfb3d0b157e27d1db")
                ).build();
    }

    @Test
    public void protBufTest() throws IOException {

        String email = "j@baeldung.com";
        int id = new Random().nextInt();
        String name = "Michael Program";
        String number = "01234567890";
        AddressBookProtos.Person person =
                AddressBookProtos.Person.newBuilder()
                        .setId(id)
                        .setName(name)
                        .setEmail(email)
                        .addNumbers(number)
                        .build();

        assertEquals(person.getEmail(), email);
        assertEquals(person.getId(), id);
        assertEquals(person.getName(), name);
        assertEquals(person.getNumbers(0), number);


        AddressBookProtos.AddressBook addressBook
                = AddressBookProtos.AddressBook.newBuilder().addPeople(person).build();
        FileOutputStream fos = new FileOutputStream("./addressBook");
        addressBook.writeTo(fos);

        AddressBookProtos.AddressBook deserialized
                = AddressBookProtos.AddressBook.newBuilder()
                .mergeFrom(new FileInputStream("./addressBook")).build();

        assertEquals(deserialized.getPeople(0).getEmail(), email);
        assertEquals(deserialized.getPeople(0).getId(), id);
        assertEquals(deserialized.getPeople(0).getName(), name);
        assertEquals(deserialized.getPeople(0).getNumbers(0), number);

    }

    @Test
    public void dirtyCablelModemDeseliazieTest() throws IOException {


        System.out.println();
        Equipment.CableModem.Builder builder
                = Equipment.CableModem.newBuilder()
                .mergeFrom(
                        new FileInputStream("./data/cable")
                );

        boolean aa = builder.isInitialized();

        Equipment.CableModem des = builder.build();


        //  Equipment.CableModem deserialized = Equipment.CableModem.parseFrom(new FileInputStream("./data/cable"));
        System.out.println();
        //  Equipment.CableModem.parseFrom()

    }

    @Test
    public void write() throws IOException {

        Equipment.CableModem cb = Equipment.CableModem.newBuilder()
                .setBase(Equipment.Base.newBuilder().build())
                .build();
        FileOutputStream fos = new FileOutputStream("./cb");
        cb.writeTo(fos);

        Equipment.CableModem.Builder builder
                = Equipment.CableModem.newBuilder()
                .mergeFrom(
                        new FileInputStream("./cb")
                );

        boolean aa = builder.isInitialized();

        Equipment.CableModem des = builder.build();

    }


    @Test
    public void cmts() throws IOException {
        System.out.println();

        byte[] fileContent = Files.readAllBytes(new File("./dirty.cmts-7e7014b848cb2998d581c5618ccdf922deb2e25b").toPath());

       // Equipment.CableModem cc = Equipment.CableModem.newBuilder().mergeFrom(fileContent).build();

        Equipment.CMTS aa = Equipment.CMTS.newBuilder().mergeFrom(fileContent).build();
        System.out.println();
        // Equipment.CMTS.newBuilder().

       /* Equipment.CMTS des =  Equipment.CMTS.parseFrom(
                        new FileInputStream("./dirty.cmts.msg")
                );*/

        //  boolean aa = builder.isInitialized();

        // Equipment.CMTS des = builder.build();
        // System.out.println();


        //  Equipment.CMTS.

    }

}
