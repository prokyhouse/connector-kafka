package ru.nsu.connector;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class PublishCallback implements Callback {

//    private static final Logger LOG = LoggerFactory.getLogger(PublishCallback.class);

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e == null) {
//            LOG.debug("Record has been published successfully.");
        } else {
//            LOG.warn("Failed to publish the record.", e);
        }
    }

}
