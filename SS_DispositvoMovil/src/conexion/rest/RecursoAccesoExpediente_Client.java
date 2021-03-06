/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion.rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import negocio.AccesoExpediente;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientProperties;

/**
 * Jersey REST client generated for REST resource:RecursoAccesoExpediente
 * [accesoexpediente]<br>
 * USAGE:
 * <pre>
 *        RecursoAccesoExpediente_Client client = new RecursoAccesoExpediente_Client();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Usuario
 */
public class RecursoAccesoExpediente_Client {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://localhost:8443/SS_BDLocal/webresources";

    public RecursoAccesoExpediente_Client() {
        System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, "lib/certs/keystore.jks");
        System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, "lib/certs/keystore.jks");
        System.getProperties().put(SSLContextConfigurator.KEY_STORE_PASSWORD, "secretaria");
        System.getProperties().put(SSLContextConfigurator.TRUST_STORE_PASSWORD, "secretaria");

        final SSLContextConfigurator defaultConfig = new SSLContextConfigurator();
        defaultConfig.retrieve(System.getProperties());

        SSLEngineConfigurator sslEngineConfigurator = new SSLEngineConfigurator(defaultConfig, true, false, false);

        client = javax.ws.rs.client.ClientBuilder.newClient().property(ClientProperties.SSL_ENGINE_CONFIGURATOR, sslEngineConfigurator);
        webTarget = client.target(BASE_URI).path("accesoexpediente");
    }

    public void putAccesoExpediente(Object requestEntity, String token) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).header("login", token).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public String getAccesoExpediente(String id, String token) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).header("login", token).get(String.class);
    }

    public void close() {
        client.close();
    }
}
