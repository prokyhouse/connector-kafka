package ru.nsu.connector;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.ConnectorException;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.Uid;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class ProducerOperations {

    private static final Log LOGGER = Log.getLog(ProducerOperations.class);

    private KafkaConfiguration configuration;
    private KafkaProducer<String, String> producer;
    private final Callback callback = new PublishCallback();

    public ProducerOperations(KafkaConfiguration configuration, KafkaProducer<String, String> producer){
        this.configuration = configuration;
        this.producer = producer;
    }

    public Uid create(Set<Attribute> attributes) throws IOException {
        Attribute uniqueAttr = KafkaConnectorUtils.getUid(attributes); // берем Uid из аргумента
        if (uniqueAttr == null && KafkaConnectorUtils.isUniqueAndNameAttributeEqual(configuration)) {
            uniqueAttr = KafkaConnectorUtils.getName(attributes);
        }
        if (uniqueAttr == null) {
            uniqueAttr = KafkaConnectorUtils.getAttr(attributes, configuration.getUniqueAttribute());
        }
        if (uniqueAttr == null) {
            throw new ConnectorException("Couldn't find unique attribute " + configuration.getUniqueAttribute() + " in contains attributes " + attributes);
        }
        if (!(uniqueAttr.getValue().get(0) instanceof String)) {
            throw new ConnectorException("Type of uniqueAttribute have to be string.");
        }

        String account = uniqueAttr.getValue().get(0).toString();

        sendRecord(uniqueAttr, account);

        return new Uid(uniqueAttr.getValue().get(0).toString());
    }

    private void sendRecord(Attribute uniqueAttr, String account) {
        String key = uniqueAttr.getValue().get(0).toString();
        ProducerRecord<String, String> record = new ProducerRecord<>(configuration.getProducerNameOfTopic(), key, account);

        try {
            producer.send(record, callback).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            LOGGER.error("Failed to publish the record.", e);
        }
    }
}
