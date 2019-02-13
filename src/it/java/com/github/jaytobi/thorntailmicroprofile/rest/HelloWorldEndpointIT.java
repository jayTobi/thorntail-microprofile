package com.github.jaytobi.thorntailmicroprofile.rest;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(Arquillian.class)
@DefaultDeployment(type = DefaultDeployment.Type.JAR)
public class HelloWorldEndpointIT {
    @Inject
    private HelloWorldEndpoint endpoint;

    @Test
    public void testCDI() {
        Response response = endpoint.doGet();
        assertNotNull(response);
        String greeting = (String) response.getEntity();
        System.out.println("++++++ got greeting: '" + greeting + "'");
        assertEquals("Hello from Thorntail!", greeting);
    }
}
