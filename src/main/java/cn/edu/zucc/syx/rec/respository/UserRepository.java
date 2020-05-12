package cn.edu.zucc.syx.rec.respository;

import cn.edu.zucc.syx.rec.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User,Integer> {
    public User queryByNameAndPassword(String Name, String passwd);

}
