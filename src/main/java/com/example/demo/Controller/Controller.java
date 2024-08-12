package com.example.demo.Controller;

import com.example.demo.domain.SearchRequest;
import com.example.demo.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solr")
public class Controller {

    @Autowired
    private SolrService solrService;

    // Apache Solr 단독 실행 = Select *
    @GetMapping("/select")
    public ResponseEntity<?> getDocuments() {
        try {
            return ResponseEntity.ok(solrService.getAllDocuments());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // Apache Solr 단독 실행 = 쿼리문 실행
    @GetMapping("/search")
    public ResponseEntity<?> search (@RequestBody SearchRequest searchRequest) throws Exception {
        try {
            return ResponseEntity.ok(solrService.search(searchRequest.getQuery()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
