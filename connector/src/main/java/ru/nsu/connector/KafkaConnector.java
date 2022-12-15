package ru.nsu.connector;

import java.util.*;

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

    public static void main( String[] args ) { }

    private static final Log LOGGER = Log.getLog(KafkaConnector.class);

    public void init(KafkaConfiguration configuration) {

        if (configuration.isConsumer()) {
            System.out.println("[STATE] Connector is CONSUMER");
        } else if (configuration.isProducer()) {
            System.out.println("[STATE] Connector is PRODUCER");
        }
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public void init(Configuration configuration) {

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
