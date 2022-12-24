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
    private String producerPathToFileContainingSchema; // needed
    private String producerNameOfTopic; // needed
    private String pathToMorePropertiesForProducer;

    public String getPathToMorePropertiesForProducer() {
        return pathToMorePropertiesForProducer;
    }

    public void setPathToMorePropertiesForProducer(String pathToMorePropertiesForProducer) {
        this.pathToMorePropertiesForProducer = pathToMorePropertiesForProducer;
    }

    public String getNameOfSchema() {
        return nameOfSchema;
    }

    public String getUseOfConnector() {
        return useOfConnector;
    }

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

    public void setNameOfSchema(String nameOfSchema) {
        this.nameOfSchema = nameOfSchema;
    }

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

    @Override
    public void validate() {
        LOGGER.info("Processing trough configuration validation procedure.");

        if (StringUtil.isBlank(uniqueAttribute)) {
            throw new ConfigurationException("Unique attribute cannot be empty.");
        }
        if (bootstrapServers == null || bootstrapServers.isEmpty()) {
            throw new ConfigurationException("Consumer bootstrap server cannot be empty.");
        }
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
        this.producerNameOfTopic = null;
        this.producerPathToFileContainingSchema = null;
        this.pathToMorePropertiesForProducer = null;
    }
}
