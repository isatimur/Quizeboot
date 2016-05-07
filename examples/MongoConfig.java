package com.atc.rldd.service.config;

import com.atc.rldd.service.domain.addfield.AdditionalFieldRef;
import com.atc.rldd.service.domain.exceptions.CreateIndexException;
import com.atc.rldd.service.index.RlddIndexCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import javax.annotation.PostConstruct;
import java.net.UnknownHostException;

/**
 * Created by maxim on 19.10.15.
 */

@Configuration
public class MongoConfig {

    @Autowired
    private MongoProperties mongoProperties;

    @Autowired
    private GridFsMongoProperties gridFsMongoProperties;

    @Autowired
    private MongoMappingContext context;

    @Bean
    public GridFsTemplate gridFsTemplate() throws UnknownHostException {
        MongoProperties targetProperties = mongoProperties;
        if (gridFsMongoProperties.getMongodb() != null) {
            targetProperties = gridFsMongoProperties.getMongodb();
        }
        MongoDbFactory factory = new SimpleMongoDbFactory(targetProperties.createMongoClient(null), targetProperties.getDatabase());
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(factory), new MongoMappingContext());
        return new GridFsTemplate(factory, converter);
    }

    @Bean
    public RlddIndexCreator rlddIndexCreator() throws CreateIndexException {
        RlddIndexCreator rlddIndexCreator = new RlddIndexCreator();
        return  rlddIndexCreator;
    }

    @PostConstruct
    public void initAdditionalMappings() {
       context.getPersistentEntity(AdditionalFieldRef.class);
    }


}
