package cn.edu.zucc.syx.rec.respository;

import cn.edu.zucc.syx.rec.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User,Integer> {
    User queryUserByHostAndPassword(String host, String pwd);

    User findUserByHost(String host);


}
