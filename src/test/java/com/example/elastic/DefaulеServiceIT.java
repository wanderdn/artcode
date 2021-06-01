//package com.example.elastic;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.testcontainers.containers.Network;
//import org.testcontainers.elasticsearch.ElasticsearchContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//@Testcontainers
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//class DefaulеServiceIT {
//
//    @Container
//    private static ElasticsearchContainer elasticsearchContainer = new WrappedElasticsearchContainer();
//
//    @BeforeAll
//    static void setUp() {
//        System.setProperty("elastic.host",
//                elasticsearchContainer.getNetworkAliases().get(0)
//                        + ":"
//                        + 9200
//        );
//        elasticsearchContainer.start();
//    }
//
//    @AfterAll
//    static void destroy() {
//        elasticsearchContainer.stop();
//    }
//}