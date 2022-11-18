package ru.nsu.connector;

import static org.junit.Assert.assertEquals;
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
        assertTrue(configuration.isConsumer());
    }

    protected KafkaConfiguration getProducerConfiguration() {
        KafkaConfiguration configuration = new KafkaConfiguration();
        configuration.connectorUseCase = KafkaConfiguration.ConnectorUseCase.CONSUMER;
        return configuration;
    }
}
