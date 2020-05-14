package cn.edu.zucc.syx.rec.respository;



import cn.edu.zucc.syx.rec.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CollectRepository extends ElasticsearchRepository<User,Integer> {
}
