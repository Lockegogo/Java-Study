package com.locke.demos.web.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Highlight;
import co.elastic.clients.elasticsearch.core.search.HighlightField;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.locke.demos.web.pojo.Content;
import com.locke.demos.web.utils.HtmlParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 业务编写
@Service
public class ContentService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    // 1. 解析数据放入 es 索引
    public Boolean parseContent(String keywords) throws Exception {
        List<Content> contents = new HtmlParseUtil().parseJD(keywords);
        // 把查询的数据放入 es 中
        List<BulkOperation> bulkOperationArrayList = new ArrayList<>();
        // 遍历添加到 bulk 中
        // 如果不写 id 的话，就是随机 id
        for (Content content : contents) {
            bulkOperationArrayList.add(BulkOperation.of(o -> o.index(i -> i.document(content))));
        }

        BulkResponse bulkResponse = elasticsearchClient.bulk(b -> b.index("jd_goods")
                .operations(bulkOperationArrayList));
        return !bulkResponse.errors();

    }

    // 2. 获取这些数据
    public List<Map<String, Object>> searchPage(String keyword, int pageNo, int pageSize) {
        try {
            if (pageNo <= 0) {
                pageNo = 1;
            }
            if (pageSize <= 0) {
                pageSize = 10;
            }
            // 条件搜索
            int finalPageNo = pageNo;
            int finalPageSize = pageSize;
            SearchResponse<Content> searchResponse = elasticsearchClient.search(s -> s
                    .index("jd_goods")
                    // 查询 title 字段包含关键字的 document (不使用分词器精确查找)
                    .query(q -> q
                            .term(t -> t
                                    .field("title")
                                    .value(v -> v.stringValue(keyword))
                            ))
                    // 高亮 title 字段
                    .highlight(h -> h
                            .fields("title", f -> f
                                    .preTags("<font color='red'>")
                                    .postTags("</font>")))
                    // 分页查询，从第 PageNo 页开始查询 PageSize 个 document
                    .from(finalPageNo)
                    .size(finalPageSize), Content.class
            );
            // 解析结果
            List<Map<String, Object>> list = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            for (Hit<Content> hit : searchResponse.hits().hits()) {
                Content content = hit.source();
                Map<String, Object> map = objectMapper.convertValue(content, new TypeReference<Map<String, Object>>() {
                });
                // 解析高亮字段
                Map<String, List<String>> highlightFields = hit.highlight();
                List<String> title = highlightFields.get("title");
                if (title != null) {
                    map.put("title", title.get(0));
                }
                list.add(map);
            }
            return list;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
