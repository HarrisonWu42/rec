package cn.edu.zucc.syx.rec.respository;


import cn.edu.zucc.syx.rec.entity.Sheet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SheetRepository extends ElasticsearchRepository<Sheet, Integer> {
    Sheet findById(String sheetId);
}

