package se.vgregion.portal.ldap;

import com.liferay.portal.security.ldap.AttributesTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import java.util.UUID;

/**
 * @author Patrik Björk
 */
public class CustomAttributesTransformer implements AttributesTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAttributesTransformer.class);

    @Override
    public Attributes transformGroup(Attributes attributes) {
        return attributes;
    }

    @Override
    public Attributes transformUser(Attributes attributes) {

        LOGGER.info("Potientially transforming LDAP attributes.");

        Attribute mail = attributes.get("mail");

        if (mail != null) {

            try {
                LOGGER.info("LDAP user mail attribute: \"" + mail.get() + "\"");

                if (mail.get().equals("-")) {
                    attributes.put("mail", "unknown-" + UUID.randomUUID().toString() + "@jyfgfdkjfghusswre.org");
                }
            } catch (NamingException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return attributes;
    }
}
