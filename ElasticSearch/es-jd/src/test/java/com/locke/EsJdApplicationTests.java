package com.locke;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import com.locke.demos.web.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EsJdApplicationTests {

    @Autowired
    @Qualifier("elasticsearchClient")
    private ElasticsearchClient client;

    // 删除索引
    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexResponse deleteIndexResponse = client.indices().delete(d -> d.index("user"));
        System.out.println(deleteIndexResponse.acknowledged());
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
        //遍历添加到bulk中
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
                //查询 name 字段包含 hello 的 document (不使用分词器精确查找)
                .query(q -> q
                        .term(t -> t
                                .field("name")
                                .value(v -> v.stringValue("hello"))
                        ))
                //分页查询，从第 0 页开始查询 3 个 document
                .from(0)
                .size(3)
                //按 age 降序排序
                .sort(f -> f.field(o -> o.field("age").order(SortOrder.Desc))), User.class
        );
        for (Hit<User> hit : search.hits().hits()) {
            System.out.println(hit.source());
        }
    }

}
