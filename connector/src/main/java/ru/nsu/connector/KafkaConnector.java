package ru.nsu.connector;

import java.io.IOException;
import java.util.*;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.InvalidAttributeValueException;
import org.identityconnectors.framework.common.objects.*;
import org.identityconnectors.framework.spi.Configuration;
import org.identityconnectors.framework.spi.Connector;
import org.identityconnectors.framework.spi.ConnectorClass;
import org.identityconnectors.framework.spi.operations.*;
@ConnectorClass(displayNameKey = "connector.kafka.display", configurationClass = KafkaConfiguration.class)
public class KafkaConnector implements TestOp, SchemaOp, Connector, SyncOp, CreateOp, DeleteOp {

    private static final Log LOGGER = Log.getLog(KafkaConnector.class);

    protected KafkaConfiguration configuration;
    private KafkaProducer<String, String> producer;
    public static void main( String[] args ) { }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public void init(Configuration configuration) {
        this.configuration = (KafkaConfiguration) configuration;
        this.configuration.validate();

        if (((KafkaConfiguration)configuration).isProducer()) {
            this.producer = this.initProducer();
        }
    }

    private KafkaProducer<String, String> initProducer() {
        Properties properties = new Properties();

        addCommonPropertiesForConsumerAndProducer(configuration, properties);

        if(properties.contains(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG)) {
            properties.remove(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG);
        }
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        if(properties.contains(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG)) {
            properties.remove(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG);
        }
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new KafkaProducer<>(properties);
    }

    private static void addCommonPropertiesForConsumerAndProducer(KafkaConfiguration configuration, Properties properties) {
        if(properties.contains(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG)) {
            properties.remove(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG);
        }
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, configuration.getBootstrapServers());

    }

    @Override
    public void dispose() {
        LOGGER.info("Configuration cleanup");
        configuration = null;
    }

    @Override
    public Uid create(ObjectClass objectClass, Set<Attribute> set, OperationOptions operationOptions) {
        if (!configuration.isProducer()) {
            throw new UnsupportedOperationException("This operation is unsupported, if you want use this connector as " +
                    "kafka producer please set 'PRODUCER' for configuration property useOfConnector.");
        }

        if (objectClass == null) {
            LOGGER.error("Attribute of type ObjectClass not provided.");
            throw new InvalidAttributeValueException("Attribute of type ObjectClass not provided.");
        }

        if (set == null) {
            LOGGER.error("Attribute of type Set<Attribute> not provided.");
            throw new InvalidAttributeValueException("Attribute of type Set<Attribute> not provided.");
        }

        if (operationOptions == null) {
            LOGGER.error("Parameter of type OperationOptions not provided.");
            throw new InvalidAttributeValueException("Parameter of type OperationOptions not provided.");
        }

        LOGGER.info("create on {0}, attributes: {1}, options: {2}", objectClass, set, operationOptions);

        ProducerOperations crateOrUpdateOp = new ProducerOperations(configuration, producer);

        Uid uid = null;
        try {
            uid = crateOrUpdateOp.create(set);
        } catch (IOException e) {
            LOGGER.error(e, "Couldn't open file " + configuration.getProducerPathToFileContainingSchema());
        }
        return uid;
    }

    @Override
    public void delete(ObjectClass objectClass, Uid uid, OperationOptions operationOptions) {
        LOGGER.info("Delete is not needed", objectClass, uid.getValue(), operationOptions);
    }

    @Override
    public Schema schema() {
        ObjectClassInfoBuilder objectClassBuilder = new ObjectClassInfoBuilder();
        objectClassBuilder.setType("myAccount");
        objectClassBuilder.addAttributeInfo(
                AttributeInfoBuilder.build("fullName", String.class));
        objectClassBuilder.addAttributeInfo(
                AttributeInfoBuilder.build("homeDir", String.class));

        SchemaBuilder schemaBuilder = new SchemaBuilder(KafkaConnector.class);
        schemaBuilder.defineObjectClass(objectClassBuilder.build());
        return schemaBuilder.build();
    }

    @Override
    public void sync(ObjectClass objectClass, SyncToken syncToken, SyncResultsHandler syncResultsHandler, OperationOptions operationOptions) {

        LOGGER.info("sync on {0}, token: {1}, options: {2}", objectClass, syncToken, operationOptions);

        if (!configuration.isConsumer()) {
            throw new UnsupportedOperationException("This operation is unsupported, if you want use this connector as " +
                    "kafka consumer please set 'CONSUMER' or 'CONSUMER_AND_PRODUCER' for configuration property useOfConnector.");
        }
    }

    @Override
    public SyncToken getLatestSyncToken(ObjectClass objectClass) {
        return null;
    }

    @Override
    public void test() {
        LOGGER.info("test");
        if (this.configuration.isProducer()){
            this.producer.partitionsFor(configuration.getProducerNameOfTopic());
        }
    }
}
