package ru.nsu.connector;

import org.identityconnectors.common.StringUtil;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.framework.spi.ConfigurationProperty;
import org.identityconnectors.framework.spi.StatefulConfiguration;

public class KafkaConfiguration extends AbstractConfiguration implements StatefulConfiguration {

    private static final Log LOGGER = Log.getLog(KafkaConnector.class);

    protected ConnectorUseCase connectorUseCase;

    private String useOfConnector;
    private String uniqueAttribute; // needed
    private String nameAttribute; // needed
    private String passwordAttribute;
    private String bootstrapServers; //needed
    private String nameOfSchema;

//    private String consumerNameOfTopic;
//    private Integer consumerVersionOfSchema;
//    private String consumerGroupId;
//    private String consumerPartitionOfTopic;
//    private Integer consumerDurationIfFail;
//    private Integer consumerMaxRecords;
//    private String pathToMorePropertiesForConsumer;

    private String producerPathToFileContainingSchema; // needed
    private String producerNameOfTopic; // needed
    private String pathToMorePropertiesForProducer;


    protected enum ConnectorUseCase {
        CONSUMER,
        PRODUCER
    }

    public boolean isConsumer() {
        return (ConnectorUseCase.CONSUMER == connectorUseCase);
    }

    public boolean isProducer() {
        return (ConnectorUseCase.PRODUCER == connectorUseCase);
    }

//    @ConfigurationProperty(order = 38, displayMessageKey = "useOfConnector.display",
//            helpMessageKey = "useOfConnector.help", required = true, confidential = false)
    public String getUseOfConnector() {
        return useOfConnector;
    }

    public void setUseOfConnector(String useOfConnector) {
        this.useOfConnector = useOfConnector;
    }

    @ConfigurationProperty(order = 39, displayMessageKey = "uniqueAttribute.display",
            helpMessageKey = "uniqueAttribute.help", required = true)
    public String getUniqueAttribute() {
        return uniqueAttribute;
    }

    public void setUniqueAttribute(String uniqueAttribute) {
        this.uniqueAttribute = uniqueAttribute;
    }

    @ConfigurationProperty(order = 40, displayMessageKey = "bootstrapServers.display",
            helpMessageKey = "bootstrapServers.help", required = true)
    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    //    @ConfigurationProperty(order = 41, displayMessageKey = "nameOfSchema.display",
//            helpMessageKey = "nameOfSchema.help", required = true, confidential = false)
    public String getNameOfSchema() {
        return nameOfSchema;
    }

    public void setNameOfSchema(String nameOfSchema) {
        this.nameOfSchema = nameOfSchema;
    }


//    @ConfigurationProperty(order = 43, displayMessageKey = "passwordAttribute.display",
//            helpMessageKey = "passwordAttribute.help", required = false, confidential = false)
    public String getPasswordAttribute() {
        return passwordAttribute;
    }

    public void setPasswordAttribute(String passwordAttribute) {
        this.passwordAttribute = passwordAttribute;
    }

    @ConfigurationProperty(order = 43, displayMessageKey = "nameAttribute.display",
            helpMessageKey = "nameAttribute.help", required = true)
    public String getNameAttribute() {
        return nameAttribute;
    }

    public void setNameAttribute(String nameAttribute) {
        this.nameAttribute = nameAttribute;
    }


    // consumer properties

//    @ConfigurationProperty(order = 79, displayMessageKey = "consumerNameOfTopic.display",
//            helpMessageKey = "consumerNameOfTopic.help", required = false, confidential = false)
//    public String getConsumerNameOfTopic() {
//        return consumerNameOfTopic;
//    }
//
//    public void setConsumerNameOfTopic(String consumerNameOfTopic) {
//        this.consumerNameOfTopic = consumerNameOfTopic;
//    }
//
//    @ConfigurationProperty(order = 80, displayMessageKey = "consumerVersionOfSchema.display",
//            helpMessageKey = "consumerVersionOfSchema.help", required = false, confidential = false)
//    public Integer getConsumerVersionOfSchema() {
//        return consumerVersionOfSchema;
//    }
//
//    public void setConsumerVersionOfSchema(Integer consumerVersionOfSchema) {
//        this.consumerVersionOfSchema = consumerVersionOfSchema;
//    }
//
//    @ConfigurationProperty(order = 81, displayMessageKey = "consumerGroupId.display",
//            helpMessageKey = "consumerGroupId.help", required = false, confidential = false)
//    public String getConsumerGroupId() {
//        return consumerGroupId;
//    }
//
//    public void setConsumerGroupId(String consumerGroupId) {
//        this.consumerGroupId = consumerGroupId;
//    }
//
//    @ConfigurationProperty(order = 83, displayMessageKey = "consumerPartitionOfTopic.display",
//            helpMessageKey = "consumerPartitionOfTopic.help", required = false, confidential = false)
//    public String getConsumerPartitionOfTopic() {
//        return consumerPartitionOfTopic;
//    }
//
//    public void setConsumerPartitionOfTopic(String partitionOfTopic) {
//        this.consumerPartitionOfTopic = partitionOfTopic;
//    }
//
//    @ConfigurationProperty(order = 87, displayMessageKey = "consumerDurationIfFail.display",
//            helpMessageKey = "consumerDurationIfFail.help", required = false, confidential = false)
//    public Integer getConsumerDurationIfFail() {
//        return consumerDurationIfFail;
//    }
//
//    public void setConsumerDurationIfFail(Integer consumerDurationIfFail) {
//        this.consumerDurationIfFail = consumerDurationIfFail;
//    }
//
//    @ConfigurationProperty(order = 88, displayMessageKey = "consumerMaxRecords.display",
//            helpMessageKey = "consumerMaxRecords.help", required = false, confidential = false)
//    public Integer getConsumerMaxRecords() {
//        return consumerMaxRecords;
//    }
//
//    public void setConsumerMaxRecords(Integer consumerMaxRecords) {
//        this.consumerMaxRecords = consumerMaxRecords;
//    }
//
//    @ConfigurationProperty(order = 89, displayMessageKey = "pathToMorePropertiesForConsumer.display",
//            helpMessageKey = "pathToMorePropertiesForConsumer.help", required = false, confidential = false)
//    public String getPathToMorePropertiesForConsumer() {
//        return pathToMorePropertiesForConsumer;
//    }
//
//    public void setPathToMorePropertiesForConsumer(String pathToMorePropertiesForConsumer) {
//        this.pathToMorePropertiesForConsumer = pathToMorePropertiesForConsumer;
//    }

    // producer properties

    @ConfigurationProperty(order = 99, displayMessageKey = "producerNameOfTopic.display",
            helpMessageKey = "producerNameOfTopic.help", required = true)
    public String getProducerNameOfTopic() {
        return producerNameOfTopic;
    }

    public void setProducerNameOfTopic(String producerNameOfTopic) {
        this.producerNameOfTopic = producerNameOfTopic;
    }

    @ConfigurationProperty(order = 100, displayMessageKey = "producerPathToFileContainingSchema.display",
            helpMessageKey = "producerPathToFileContainingSchema.help", required = true)
    public String getProducerPathToFileContainingSchema() {
        return producerPathToFileContainingSchema;
    }

    public void setProducerPathToFileContainingSchema(String producerPathToFileContainingSchema) {
        this.producerPathToFileContainingSchema = producerPathToFileContainingSchema;
    }

//    @ConfigurationProperty(order = 106, displayMessageKey = "pathToMorePropertiesForProducer.display",
//            helpMessageKey = "pathToMorePropertiesForProducer.help", required = false, confidential = false)
    public String getPathToMorePropertiesForProducer() {
        return pathToMorePropertiesForProducer;
    }

    public void setPathToMorePropertiesForProducer(String pathToMorePropertiesForProducer) {
        this.pathToMorePropertiesForProducer = pathToMorePropertiesForProducer;
    }

    @Override
    public void validate() {
        LOGGER.info("Processing trough configuration validation procedure.");

        if (StringUtil.isBlank(uniqueAttribute)) {
            throw new ConfigurationException("Unique attribute cannot be empty.");
        }

//        if (StringUtil.isBlank(useOfConnector)) {
//            throw new ConfigurationException("Use of connector attribute cannot be empty.");
//        }

        if (bootstrapServers == null || bootstrapServers.isEmpty()) {
            throw new ConfigurationException("Consumer bootstrap server cannot be empty.");
        }

//        if (StringUtil.isBlank(nameOfSchema)) {
//            throw new ConfigurationException("Consumer name of schema cannot be empty.");
//        }

//        if (isConsumer()) {
//            if (StringUtil.isBlank(consumerNameOfTopic)) {
//                throw new ConfigurationException("Consumer name of topic for consumer cannot be empty.");
//            }
//
//            if (consumerVersionOfSchema == null) {
//                throw new ConfigurationException("Consumer version of schema cannot be empty.");
//            }
//            if (StringUtil.isBlank(consumerGroupId)) {
//                throw new ConfigurationException("Consumer grouper id for consumer cannot be empty.");
//            }
//        }

        if (isProducer()) {

            if (StringUtil.isBlank(producerNameOfTopic)) {
                throw new ConfigurationException("Producer name of topic for consumer cannot be empty.");
            }

            if (StringUtil.isBlank(producerPathToFileContainingSchema)) {
                throw new ConfigurationException("Producer path to file containing schema cannot be empty.");
            }
        }


        LOGGER.info("Configuration valid");
    }

    @Override
    public void release() {
        LOGGER.info("The release of configuration resources is being performed");

        this.useOfConnector = null;
        this.uniqueAttribute = null;
        this.nameAttribute = null;
        this.passwordAttribute = null;
        this.bootstrapServers = null;
        this.nameOfSchema = null;

//        this.consumerNameOfTopic = null;
//        this.consumerVersionOfSchema = null;
//        this.consumerPartitionOfTopic = null;
//        this.consumerGroupId = null;
//        this.consumerDurationIfFail = null;
//        this.consumerMaxRecords = null;
//        this.pathToMorePropertiesForConsumer = null;

        this.producerNameOfTopic = null;
        this.producerPathToFileContainingSchema = null;
        this.pathToMorePropertiesForProducer = null;
    }

}
