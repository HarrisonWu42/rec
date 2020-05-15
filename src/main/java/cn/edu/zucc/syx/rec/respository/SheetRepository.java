package cn.edu.zucc.syx.rec.respository;

import cn.edu.zucc.syx.rec.entity.Sheet;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SheetRepository extends ElasticsearchRepository<Sheet, Integer> {
    Sheet findById(String sheetId);
    Integer deleteById(String sheetId);
    List<Sheet> findByNameLike(String name);
}

