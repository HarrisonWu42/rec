package cn.edu.zucc.syx.rec.respository;

import cn.edu.zucc.syx.rec.entity.Sheet;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.awt.peer.LightweightPeer;
import java.util.List;

public interface SheetRepository extends ElasticsearchRepository<Sheet, Integer> {
    Sheet findById(String sheetId);
    Integer deleteById(String sheetId);
    List<Sheet> queryByNameContains(String sheetName);
    List<Sheet> queryByCreator_id(String createId);
}

