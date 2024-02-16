package com.locke;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.locke.demos.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EsApiApplicationTests {

    @Autowired
    @Qualifier("elasticsearchClient")
    private ElasticsearchClient client;

    // 创建索引
    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexResponse indexResponse = client.indices().create(c -> c.index("user"));
        System.out.println(indexResponse);
    }

    // 查询索引
    @Test
    public void testGetIndex() throws IOException {
        GetIndexResponse getindexResponse = client.indices().get(i -> i.index("user"));
        System.out.println(getindexResponse);
    }

    // 判断索引是否存在
    @Test
    public void testExistsIndex() throws IOException {
        BooleanResponse booleanResponse = client.indices().exists(e -> e.index("user"));
        System.out.println(booleanResponse.value());
    }

    // 删除索引
    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexResponse deleteIndexResponse = client.indices().delete(d -> d.index("user"));
        System.out.println(deleteIndexResponse.acknowledged());
    }

    // 测试添加文档
    @Test
    public void testAddDocument() throws IOException {
        User user = new User("locke", 18);
        IndexResponse indexResponse = client.index(i -> i.index("user").id("1").document(user));
    }

    // 更新文档
    @Test
    public void testUpdateDocument() throws IOException {
        UpdateResponse<User> updateResponse = client.update(u -> u.index("user")
                .id("1")
                .doc(new User("feifei", 13)), User.class);
    }

    // 判断文档是否存在
    @Test
    public void testExistsDocument() throws IOException {
        BooleanResponse booleanResponse = client.exists(e -> e.index("user").id("1"));
        System.out.println(booleanResponse.value());
    }

    // 查询文档
    @Test
    public void testGetDocument() throws IOException {
        GetResponse<User> getResponse = client.get(g -> g.index("user").id("1"), User.class);
        System.out.println(getResponse.source());
    }

    // 删除文档
    @Test
    public void testDeleteDocument() throws IOException {
        DeleteResponse deleteResponse = client.delete(d -> d.index("user").id("1"));
        System.out.println(deleteResponse.id());
    }

    // 批量插入文档
    @Test
    public void bulkTest() throws IOException {
        List<User> userList = new ArrayList<>();
        userList.add(new User("hello world", 11));
        userList.add(new User("hello java", 12));
        userList.add(new User("hello es", 13));
        userList.add(new User("hello spring", 14));
        userList.add(new User("user", 15));
        List<BulkOperation> bulkOperationArrayList = new ArrayList<>();
        // 遍历添加到 bulk 中
        for (User user : userList) {
            bulkOperationArrayList.add(BulkOperation.of(o -> o.index(i -> i.document(user))));
        }

        BulkResponse bulkResponse = client.bulk(b -> b.index("user")
                .operations(bulkOperationArrayList));

    }

    // 查询
    @Test
    public void searchTest() throws IOException {
        SearchResponse<User> search = client.search(s -> s
                .index("user")
                // 查询 name 字段包含 hello 的 document (不使用分词器精确查找)
                .query(q -> q
                        .term(t -> t
                                .field("name")
                                .value(v -> v.stringValue("hello"))
                        ))
                // 分页查询，从第 0 页开始查询 3 个 document
                .from(0)
                .size(3)
                // 按 age 降序排序
                .sort(f -> f.field(o -> o.field("age").order(SortOrder.Desc))), User.class
        );
        for (Hit<User> hit : search.hits().hits()) {
            System.out.println(hit.source());
        }
    }

}
