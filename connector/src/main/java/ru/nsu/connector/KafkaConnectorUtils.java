package ru.nsu.connector;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.Name;
import org.identityconnectors.framework.common.objects.Uid;

import java.util.Set;

public class KafkaConnectorUtils {

    private static final Log LOGGER = Log.getLog(KafkaConnector.class);

    public static Attribute getUid(Set<Attribute> attributes) {
        return getAttr(attributes, Uid.NAME);
    }

    public static Attribute getAttr(Set<Attribute> attributes, String name) {
        Validate.notBlank(name);
        Validate.notEmpty(attributes);
        for (Attribute attribute : attributes) {
            if (attribute.getName().equals(name)) {
                return attribute;
            }
        }
        return null;
    }

    public static boolean isUniqueAndNameAttributeEqual( KafkaConfiguration configuration) {
        if (StringUtils.isBlank(configuration.getNameAttribute())) {
            return  true;
        }
        return configuration.getUniqueAttribute().equals(configuration.getNameAttribute());
    }

    public static Attribute getName(Set<Attribute> attributes) {
        return getAttr(attributes, Name.NAME);
    }
}
