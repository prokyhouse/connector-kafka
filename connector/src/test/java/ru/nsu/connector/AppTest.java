package ru.nsu.connector;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for Connector.
 */
public class AppTest 
{
    @Test
    public void basicTest() {
        KafkaConfiguration configuration = getProducerConfiguration();
        KafkaConnector connector = new KafkaConnector();
        connector.init(configuration);
        assertTrue(configuration.isProducer());
    }

    protected KafkaConfiguration getProducerConfiguration() {
        KafkaConfiguration configuration = new KafkaConfiguration();
        configuration.connectorUseCase = KafkaConfiguration.ConnectorUseCase.PRODUCER;
        configuration.setUniqueAttribute("12345");
        configuration.setUseOfConnector("consumer");
        configuration.setBootstrapServers("localhost:9092");
        configuration.setNameOfSchema("schema");
        configuration.setProducerNameOfTopic("topic");
        configuration.setProducerPathToFileContainingSchema("path");
        return configuration;
    }
}
