package com.atc.rldd.service.config;

import com.atc.rldd.service.domain.address.Address;
import com.atc.rldd.service.domain.claim.Claim;
import com.atc.rldd.service.domain.claim.ClaimStatus;
import com.atc.rldd.service.domain.department.Dept;
import com.atc.rldd.service.domain.department.DeptCalendar;
import com.atc.rldd.service.domain.department.DeptOperator;
import com.atc.rldd.service.domain.department.Oktmo;
import com.atc.rldd.service.domain.document.*;
import com.atc.rldd.service.domain.email.Email;
import com.atc.rldd.service.domain.exchange.ExchangeLog;
import com.atc.rldd.service.domain.person.Person;
import com.atc.rldd.service.domain.validators.ClaimValidator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;

/**
 * Created by mtsygan on 26.05.15.
 */

@Configuration
public class RestMvcConfig extends RepositoryRestMvcConfiguration {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    @Autowired
    private Validator mvcValidator;

    @Autowired
    private Environment environment;

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBaseUri("/api");
        config.setReturnBodyOnCreate(true);
        config.exposeIdsFor(
                Claim.class,
                Person.class,
                Address.class,
                Document.class,
                ClaimStatus.class,
                FileMetadata.class,
                DeptOperator.class,
                DeptCalendar.class,
                DocumentType.class,
                MVDocMetadata.class,
                MVRunMetadata.class,
                Email.class,
                Oktmo.class,
                MvDocStatus.class,
                ExchangeLog.class,
                Dept.class
        );
    }

    @Override
    protected void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {

        // Add profile: <validation> in -Dspring.profiles.active.
        if(!environment.acceptsProfiles("validation")) return;

        validatingListener.addValidator("beforeSave", mvcValidator);
        validatingListener.addValidator("beforeCreate", mvcValidator);
    }

    @Override
    protected void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}