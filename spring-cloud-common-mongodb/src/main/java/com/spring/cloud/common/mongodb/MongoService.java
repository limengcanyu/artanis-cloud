package com.spring.cloud.common.mongodb;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Service;

@Service
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void createCollection(String collectionName) {
        mongoTemplate.createCollection(collectionName);
    }

    public <T> T createCollection(T objectToSave, String collectionName) {
        return mongoTemplate.save(objectToSave, collectionName);
    }

    public long upsert(Query query, UpdateDefinition update, Class<?> entityClass, String collectionName) {
        return mongoTemplate.upsert(query, update, entityClass, collectionName).getModifiedCount();
    }

    public long updateFirst(Query query, UpdateDefinition update, Class<?> entityClass, String collectionName) {
        return mongoTemplate.updateFirst(query, update, entityClass, collectionName).getModifiedCount();
    }

    public long updateMulti(Query query, UpdateDefinition update, Class<?> entityClass, String collectionName) {
        return mongoTemplate.updateMulti(query, update, entityClass, collectionName).getModifiedCount();
    }

    public long remove(Query query, String collectionName) {
        return mongoTemplate.remove(query, collectionName).getDeletedCount();
    }
}
