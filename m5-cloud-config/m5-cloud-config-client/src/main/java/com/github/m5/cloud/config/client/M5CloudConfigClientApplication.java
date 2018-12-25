package com.github.m5.cloud.config.client;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
@RestController
@EnableCaching
@SpringBootApplication
public class M5CloudConfigClientApplication {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private long lastModified = System.currentTimeMillis();
    @Value("${username}")
    String name;
    AtomicInteger i = new AtomicInteger();

    @Autowired
    private Client client;
    @Autowired
    private RestClient restClient;

    @RequestMapping("/search/{wd}")
    public Object search(@PathVariable String wd) throws Exception {
        SearchRequestBuilder search = client.prepareSearch(StockSearch.INDEX_NAME);
        search.setQuery(QueryBuilders.queryStringQuery("\"" + wd + "\""));
        SearchResponse response = search.execute().get();
        List<Map> rs = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            rs.add(hit.getSourceAsMap());
        }
        return rs;
    }

    @RequestMapping("/ssidx")
    public void ssidx() throws Exception {
        if (!client.admin().indices().exists(new IndicesExistsRequest(StockSearch.INDEX_NAME)).get().isExists()) {
            Request request = new Request("PUT", StockSearch.INDEX_NAME);
            request.setJsonEntity(StreamUtils.copyToString(StockSearch.class.getResourceAsStream("StockSearchConfig.json"), Charset.defaultCharset()));
            Response response = restClient.performRequest(request);
            logger.info("Create ES index: {}, response: {}", StockSearch.INDEX_NAME, response);
        }
    }

    @RequestMapping("/ssad")
    public Object ssad(@RequestParam String sc, @RequestParam String sn, @RequestParam String cn, @RequestParam String ld) throws Exception {
        BulkRequestBuilder bulk = client.prepareBulk();
        StockSearch ss = new StockSearch();
        ss.setStockCode(sc);
        ss.setStockName(sn);
        ss.setCompanyName(cn);
        ss.setListingDate(ld);
        bulk.add(client.prepareIndex(StockSearch.INDEX_NAME, StockSearch.INDEX_TYPE, sc).setSource(ss.map()));
        BulkItemResponse response = bulk.execute().get().getItems()[0];
        if (response.getFailure() != null) {
            throw response.getFailure().getCause();
        }
        return response;
    }
//    @RequestMapping("/scad")
//    public Object add(String sc, String sn, String cn, String ld) throws Exception {
//        if (!elasticsearchTemplate.indexExists(StockSearch.class)) {
//            elasticsearchTemplate.createIndex(StockSearch.class);
//            elasticsearchTemplate.putMapping(StockSearch.class);
//            logger.info("Create ES index: {}", AnnotationUtils.getAnnotation(StockSearch.class, Document.class).indexName());
//        }
//        SearchQuery query = new NativeSearchQuery(QueryBuilders.queryStringQuery("stockName:中通国脉 OR stockCode:603559"));
//        System.out.println(query);
//        elasticsearchTemplate.
//        return PageWrapper.wrapFromES(rs);
//    }


    @RequestMapping("/abc")
    @Cacheable(value = "test", sync = true)
    public String abc() {
        return "abc";
    }

    @GetMapping("/")
    public ResponseEntity home() {
        String result = "hell " + name + " " + i.incrementAndGet();
        System.out.println("哈哈哈");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLastModified(lastModified);
        int i = 1 / 0;
//        httpHeaders.setETag("\"" + DigestUtils.md5DigestAsHex(result.getBytes()) + "\"");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(result);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean123() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter((servletRequest, servletResponse, filterChain) -> {
            System.out.println("----doFilter before----");
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("----doFilter after----");
        });
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }


    public static void main(String[] args) {
        SpringApplication.run(M5CloudConfigClientApplication.class, args);
    }
}

