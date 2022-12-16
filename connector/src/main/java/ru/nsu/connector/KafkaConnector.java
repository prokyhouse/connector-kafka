package ru.nsu.connector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.hortonworks.registries.schemaregistry.serdes.avro.AbstractAvroSnapshotDeserializer;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.record.Record;
import org.apache.kafka.common.serialization.StringSerializer;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.objects.*;
import org.identityconnectors.framework.spi.Configuration;
import org.identityconnectors.framework.spi.Connector;
import org.identityconnectors.framework.spi.ConnectorClass;
import org.identityconnectors.framework.spi.operations.*;


/**
 * Created by Kirill Prokofiev on 18.11.2022.
 * MIT License.
 */

@ConnectorClass(displayNameKey = "connector.kafka.display", configurationClass = KafkaConfiguration.class)
public class KafkaConnector implements TestOp, SchemaOp, Connector, SyncOp, CreateOp, UpdateDeltaOp, DeleteOp {

    protected KafkaConfiguration configuration;
    private KafkaProducer<String, Record> producer;
    public static void main( String[] args ) { }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public void init(Configuration configuration) {
        if (this.configuration.isConsumer()) {
            System.out.println("[STATE] Connector is CONSUMER");
        } else if (this.configuration.isProducer()) {
            System.out.println("[STATE] Connector is PRODUCER");
        }

        KafkaConfiguration kafkaConfig = (KafkaConfiguration) configuration;
        this.configuration = kafkaConfig;
        this.configuration.validate();

        if (((KafkaConfiguration)configuration).isProducer()) {
            this.producer = this.initProducer();
        }
    }

    private KafkaProducer<String, Record> initProducer() {
        Properties properties = new Properties();
        String pathToMorePropertiesForProducer = configuration.getPathToMorePropertiesForProducer();

        if(pathToMorePropertiesForProducer != null) {
            try (InputStream input = new FileInputStream(pathToMorePropertiesForProducer)) {
                properties.load(input);
            } catch (IOException ignored) { }
        }

        addCommonPropertiesForConsumerAndProducer(configuration, properties);

        if(properties.contains(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG)) {
            properties.remove(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG);
        }
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        if(properties.contains(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG)) {
            properties.remove(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG);
        }
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, MidpointKafkaAvroSerializer.class);

        return new KafkaProducer(properties);
    }

    private static void addCommonPropertiesForConsumerAndProducer(KafkaConfiguration configuration, Properties properties) {
        if(properties.contains(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG)) {
            properties.remove(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG);
        }
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, configuration.getBootstrapServers());

//        if(properties.contains(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG)) {
//            if(configuration.getKafkaSecurityProtocol() != null) {
//                properties.remove(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG);
//                properties.put(properties, CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, configuration.getKafkaSecurityProtocol());
//            }
//        } else {
//            properties.put(properties, CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, configuration.getKafkaSecurityProtocol());
//        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public Uid create(ObjectClass objectClass, Set<Attribute> set, OperationOptions operationOptions) {
        return null;
    }

    @Override
    public void delete(ObjectClass objectClass, Uid uid, OperationOptions operationOptions) {

    }

    @Override
    public Schema schema() {
        return null;
    }

    @Override
    public void sync(ObjectClass objectClass, SyncToken syncToken, SyncResultsHandler syncResultsHandler, OperationOptions operationOptions) {

    }

    @Override
    public SyncToken getLatestSyncToken(ObjectClass objectClass) {
        return null;
    }

    @Override
    public void test() {

    }

    @Override
    public Set<AttributeDelta> updateDelta(ObjectClass objectClass, Uid uid, Set<AttributeDelta> set, OperationOptions operationOptions) {
        return null;
    }
}
