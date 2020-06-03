package cn.edu.zucc.syx.rec.respository;

import cn.edu.zucc.syx.rec.entity.Prank;
import cn.edu.zucc.syx.rec.entity.Song;
import cn.edu.zucc.syx.rec.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PrankRepository extends ElasticsearchRepository<Prank,Integer> {
    Prank queryByTag(String tag);

}
