package com.apollo.upfs.apollotest0202;

import com.apollo.upfs.apollotest0202.kit.MongoUtils;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MongoDBTest {

    @Value("business")
    private String collection;

    @Value("6018dbda0abb6976ecf30b2c")
    private String id;

    @Autowired
    private MongoUtils mongoUtils;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insertOne() {
        Document doc = new Document();
        doc.put("name", "world");
        doc.put("age", 16);
        mongoUtils.insertOne(collection, doc);
    }

    @Test
    void insertMany() {
        List<Document> documentList = new ArrayList<Document>();
        Document doc = new Document();
        doc.put("name", "sam");
        doc.put("age", 30);
        documentList.add(doc);
        doc = new Document();
        doc.put("name", "nicole");
        doc.put("age", 30);
        documentList.add(doc);
        mongoUtils.insertMany(collection, documentList);
    }

    @Test
    void findById() {
        System.out.println(mongoUtils.findById(collection, id));
    }

    @Test
    void updateOne() {
        Document doc = mongoUtils.findById(collection, id);
        doc.replace("name", "Jully");
        mongoUtils.updateOne(collection, doc);
    }

    @Test
    void findOne() {
        Document doc = new Document();
        doc.put("name", "hello");
        System.out.println(mongoUtils.findOne(collection, doc));
    }

    @Test
    void findMany() {
        Document doc = new Document();
        doc.put("id", "5db7a5e66957f95f2a7459a6");
        System.out.println(mongoUtils.findMany(collection, doc));
    }

    @Test
    void findAll() {
        System.out.println(mongoUtils.findAll(collection));
    }
}