package cn.edu.zucc.syx.rec;

import cn.edu.zucc.syx.rec.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;//导入包


@SpringBootTest
class RecApplicationTests {
    @Autowired(required=false)
    private ElasticsearchTemplate esTemplate;

    @Test
    void contextLoads() {
    }
    @Test
    public  void  createIndex(){
        // 创建索引，会根据Item类的@Document注解信息来创建
        esTemplate.createIndex(User.class);
        // 配置映射，会根据Item类中的@Id、@Field等字段来自动完成映射
        esTemplate.putMapping(User.class);

    }
}
