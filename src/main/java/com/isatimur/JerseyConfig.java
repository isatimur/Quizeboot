package com.isatimur;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tisachenko on 07.05.16.
 */
@Configuration
public class JerseyConfig extends ResourceConfig {
    JerseyConfig(){
        register(QuizeController.class);
    }
}
