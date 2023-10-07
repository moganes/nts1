package com.spectrum.notes.notesDataConsumer.deserializer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Slf4j
public class PollerDataDeserializer implements Deserializer<Object> {

    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {

        // @TODO: make the topic names configurable
        try {
            if (data == null)
                return null;

            else if (topic.startsWith("dirty"))
                return data;
            else if (topic.startsWith("properties") || topic.startsWith("snmpmon")) {
                return new String(data, encoding);
            } else
                return null;
        } catch (UnsupportedEncodingException e) {

            log.error("status = Error while deserializing the record = {} for topic = {} Exception = {}",
                    data,
                    topic,
                    e);

            throw new SerializationException("Exception when deserializing byte[] to string due to unsupported encoding " + encoding);
        }
    }

    @Override
    public void close() {
    }
}
