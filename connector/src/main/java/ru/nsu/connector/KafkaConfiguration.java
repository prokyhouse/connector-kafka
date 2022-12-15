package ru.nsu.connector;

import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.framework.spi.StatefulConfiguration;

public class KafkaConfiguration extends AbstractConfiguration implements StatefulConfiguration {

    protected ConnectorUseCase connectorUseCase;

    @Override
    public void validate() {

    }

    @Override
    public void release() {

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
}
