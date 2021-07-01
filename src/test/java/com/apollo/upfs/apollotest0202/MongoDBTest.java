package com.apollo.upfs.apollotest0202;

import com.apollo.upfs.apollotest0202.kit.MongoUtils;
import com.sun.xml.internal.fastinfoset.util.StringArray;
import org.assertj.core.util.Strings;
import org.bson.Document;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest
public class MongoDBTest {

    @Value("business")
    private String collection;

    @Value("6018e4600aa8555842f72ecd")
    private String id;

    @Autowired
    private MongoUtils mongoUtils;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    private static final String fileName = "E:\\资料\\apollo-test-0202\\src\\test\\java\\com\\apollo\\upfs\\apollotest0202\\data.txt";
    @Test
    void getFile() {

        //读取文件
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8")); //这里可以控制编码
            sb = new StringBuffer();
            String line = null;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String data = new String(sb); //StringBuffer ==> String
        LocalDate localDate= LocalDate.now();
        String now = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE).replaceAll("-","");

        insertOne(now,data);
        System.out.println("数据为==> " + data);

    }

    @Test
    void insertOne(String FileNo,String data) {
        Document doc = new Document();
        doc.put("fileId", FileNo);
        doc.put("old", data);
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
        doc.put("fileId", "5");
        System.out.println(mongoUtils.findOne(collection, doc).get("version"));
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


    @Test
    public  void removeAllTest() {
        List<String> list1 = new ArrayList<String>() {{
            this.add("1");
            this.add("2");
            this.add("3");
        }};

        List<String> list2 = new ArrayList<String>() {{
            this.add("1");
            this.add("2");
            this.add("3");
            this.add("4");
            this.add("5");
            this.add("6");
        }};

        Collection C = new ArrayList(list1);
        list2.removeAll(C);



        List<String> list01=Arrays.asList("a","b","c","D","E");
        List<String> list02=Arrays.asList("a","b","c","4");

        //list01和list02的差集, 仅保留了 b,c
        List<String> result=list01.stream().filter(word->!list02.contains(word)).collect(Collectors.toList());

        System.out.println(result);
    }




}