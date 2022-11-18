package ru.nsu.connector;

public class KafkaConfiguration {

    protected ConnectorUseCase connectorUseCase;

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
