package ru.nsu.connector;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class PublishCallback implements Callback {

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) { }

}
