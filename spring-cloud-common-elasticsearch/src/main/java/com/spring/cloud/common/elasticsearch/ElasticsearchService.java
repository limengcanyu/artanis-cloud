package com.spring.cloud.common.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticsearchService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    // IndexOperations
    public boolean createIndex(Class<?> clazz) {
        return elasticsearchTemplate.indexOps(clazz).create();
    }

    public boolean deleteIndex(Class<?> clazz) {
        return elasticsearchTemplate.indexOps(clazz).delete();
    }

    public boolean existsIndex(Class<?> clazz) {
        return elasticsearchTemplate.indexOps(clazz).exists();
    }

    // DocumentOperations
    public <T> T save(T entity, IndexCoordinates index) {
        return elasticsearchTemplate.save(entity, index);
    }

    public <T> Iterable<T> save(Iterable<T> entities, IndexCoordinates index) {
        return elasticsearchTemplate.save(entities, index);
    }

    public long delete(Query query, Class<?> clazz, IndexCoordinates index) {
        return elasticsearchTemplate.delete(query, clazz, index).getDeleted();
    }

    public String delete(String id, IndexCoordinates index) {
        return elasticsearchTemplate.delete(id, index);
    }

    public UpdateResponse.Result update(UpdateQuery query, IndexCoordinates index) {
        return elasticsearchTemplate.update(query, index).getResult();
    }

    public long updateByQuery(UpdateQuery query, IndexCoordinates index) {
        return elasticsearchTemplate.updateByQuery(query, index).getUpdated();
    }

    // SearchOperations
    public <T> SearchHit<T> searchOne(Query query, Class<T> clazz, IndexCoordinates index) {
        return elasticsearchTemplate.searchOne(query, clazz, index);
    }

    public <T> List<SearchHits<T>> multiSearch(List<? extends Query> queries, Class<T> clazz, IndexCoordinates index) {
        return elasticsearchTemplate.multiSearch(queries, clazz, index);
    }

    public <T> SearchHits<T> search(Query query, Class<T> clazz, IndexCoordinates index) {
        return elasticsearchTemplate.search(query, clazz, index);
    }

    public Query matchAllQuery() {
        return elasticsearchTemplate.matchAllQuery();
    }

    public Query idsQuery(List<String> ids) {
        return elasticsearchTemplate.idsQuery(ids);
    }

}
