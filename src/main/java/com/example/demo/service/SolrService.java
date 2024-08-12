package com.example.demo.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SolrService {

    @Autowired
    private SolrClient solrClient;

    @Value("${spring.data.solr.core}")
    private String solrCore;

    public SolrService(@Value("${spring.data.solr.host}") String solrHost) {
        this.solrClient = new HttpSolrClient.Builder(solrHost).build();
    }

    // Apache Solr 단독 실행 = Select *
    public SolrDocumentList getAllDocuments() throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        QueryResponse response = solrClient.query("test", query);
        return response.getResults();
    }

    public QueryResponse search(String queryStr) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery(queryStr);
        QueryResponse response = solrClient.query(solrCore, query);
        System.out.println("검색 쿼리 실행: " + queryStr);
        return response;
    }
}