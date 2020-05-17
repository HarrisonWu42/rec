package cn.edu.zucc.syx.rec.demo;

import cn.edu.zucc.syx.rec.demo.Table01;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface TableDao extends ElasticsearchRepository<Table01, Integer> {
    //根据标题查询
    List<Table01> findByTitle(String condition);

    //根据标题查询（含分页）
    Page<Table01> findByTitle(String condition, Pageable pageable);
}
