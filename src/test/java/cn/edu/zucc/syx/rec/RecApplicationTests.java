package cn.edu.zucc.syx.rec;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;//导入包

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class RecApplicationTests {
    @Autowired(required=false)
    private ElasticsearchTemplate esTemplate;
    @Autowired
    private UserRepository userRepository;
    @Test
    void contextLoads() {
    }


    @Test
    public  void  createIndex(){
        // 创建索引，会根据Item类的@Document注解信息来创建
//        esTemplate.createIndex(User.class);
//        // 配置映射，会根据Item类中的@Id、@Field等字段来自动完成映射
//        esTemplate.putMapping(User.class);
//
//        esTemplate.createIndex(Song.class);
//        esTemplate.putMapping(Song.class);
//
//        esTemplate.createIndex(Artist.class);
//        esTemplate.putMapping(Artist.class);

        esTemplate.createIndex(Sheet.class);
        esTemplate.putMapping(Sheet.class);

    }



        @Test
    public void testAdd() {
//        KeyArtists keyArtists= new KeyArtists("ARM","XX");
//        KeySong keySong = new KeySong("123","kk","ARM","MASTODO","AA");
//        UserSheets userSheets = new UserSheets("123,","123");
//        ArrayList<KeySong> keySongList = new ArrayList<KeySong>();
//        keySongList.add(keySong);
//        ArrayList<UserSheets> userSheetsArrayList = new ArrayList<UserSheets>();
//        userSheetsArrayList.add(userSheets);
//        ArrayList<UserSheets> userSheetsArrayList = new ArrayList<UserSheets>();
//        userSheetsArrayList.add(userSheets);
//        UserCollect userCollect = new UserCollect(keySongList,userSheetsArrayList,keyArtists);
//        UserRec userRec = new UserRec(keySong,keyArtists);
//        User user= new User(2,"kk","syx","123456","1","20","@","159888",userCollect,userRec);
        User user= new User();
//        user.setId(1);
        user.setName("syx");
        user.setEmail("@qq.com");
        user.setPassword("1234");
        userRepository.save(user);

    }
    @Test
    public void update() {
//        KeyArtists keyArtists= new KeyArtists("ARM","XX");
//        KeySong keySong = new KeySong("123","kk","ARM","MASTODO","AA");
//        UserSheets userSheets = new UserSheets("123,","123");
//        ArrayList<KeySong> keySongList = new ArrayList<KeySong>();
//        keySongList.add(keySong);
//        ArrayList<UserSheets> userSheetsArrayList = new ArrayList<UserSheets>();
//        userSheetsArrayList.add(userSheets);
//        ArrayList<UserSheets> userSheetsArrayList = new ArrayList<UserSheets>();
//        userSheetsArrayList.add(userSheets);
//        UserCollect userCollect = new UserCollect(keySongList,userSheetsArrayList,keyArtists);
//        UserRec userRec = new UserRec(keySong,keyArtists);
//        User user= new User(2,"kk","syx","123456","1","20","@","159888",userCollect,userRec);
        User user= new User();
//        user.setId(1);
        user.setName("syx");
        user.setEmail("@qq.com");
        user.setPassword("123");
        userRepository.save(user);

    }
    @Test
    public void delete() {
        User user= new User();
//        user.setId(1);
//        user.setName("syx");
//        user.setEmail("@qq.com");
//        user.setPassword("123");
//        userRepository.deleteById(user.getId());

    }
}
