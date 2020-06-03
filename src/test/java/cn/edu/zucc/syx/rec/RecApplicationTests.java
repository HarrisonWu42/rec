package cn.edu.zucc.syx.rec;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.respository.PrankRepository;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.respository.UserRepository;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WrapperQueryBuilder;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.SearchHit;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.discovery.SelectorResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;//导入包
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootTest
class RecApplicationTests {
    @Autowired(required = false)
    private ElasticsearchTemplate esTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private PrankRepository prankRepository;
    RecApplicationTests() throws UnsupportedEncodingException, FileNotFoundException {
    }

    @Test
    void contextLoads() {
    }


    @Test
    public void createIndex() {
        // 创建索引，会根据Item类的@Document注解信息来创建
//        esTemplate.createIndex(User.class);
//         配置映射，会根据Item类中的@Id、@Field等字段来自动完成映射
//        esTemplate.putMapping(User.class);
//
//        esTemplate.createIndex(Song.class);
//        esTemplate.putMapping(Song.class);
//
//        esTemplate.createIndex(Artist.class);
//        esTemplate.putMapping(Artist.class);
//
//        esTemplate.createIndex(Sheet.class);
//        esTemplate.putMapping(Sheet.class);

//        esTemplate.createIndex(Table01.class);
//        esTemplate.putMapping(Table01.class);
        esTemplate.createIndex(Prank.class);
        esTemplate.putMapping(Prank.class);

    }
//
//
//
//        @Test
//    public void testAdd() {
////        KeyArtists keyArtists= new KeyArtists("ARM","XX");
////        KeySong keySong = new KeySong("123","kk","ARM","MASTODO","AA");
////        UserSheets userSheets = new UserSheets("123,","123");
////        ArrayList<KeySong> keySongList = new ArrayList<KeySong>();
////        keySongList.add(keySong);
////        ArrayList<UserSheets> userSheetsArrayList = new ArrayList<UserSheets>();
////        userSheetsArrayList.add(userSheets);
////        ArrayList<UserSheets> userSheetsArrayList = new ArrayList<UserSheets>();
////        userSheetsArrayList.add(userSheets);
////        UserCollect userCollect = new UserCollect(keySongList,userSheetsArrayList,keyArtists);
////        UserRec userRec = new UserRec(keySong,keyArtists);
////        User user= new User(2,"kk","syx","123456","1","20","@","159888",userCollect,userRec);
//        User user= new User();
////        user.setId(1);
//        user.setName("syx");
//        user.setEmail("@qq.com");
//        user.setPassword("1234");
//        userRepository.save(user);
//
//    }
//    @Test
//    public void update() {
////        KeyArtists keyArtists= new KeyArtists("ARM","XX");
////        KeySong keySong = new KeySong("123","kk","ARM","MASTODO","AA");
////        UserSheets userSheets = new UserSheets("123,","123");
////        ArrayList<KeySong> keySongList = new ArrayList<KeySong>();
////        keySongList.add(keySong);
////        ArrayList<UserSheets> userSheetsArrayList = new ArrayList<UserSheets>();
////        userSheetsArrayList.add(userSheets);
////        ArrayList<UserSheets> userSheetsArrayList = new ArrayList<UserSheets>();
////        userSheetsArrayList.add(userSheets);
////        UserCollect userCollect = new UserCollect(keySongList,userSheetsArrayList,keyArtists);
////        UserRec userRec = new UserRec(keySong,keyArtists);
////        User user= new User(2,"kk","syx","123456","1","20","@","159888",userCollect,userRec);
//        User user= new User();
////        user.setId(1);
//        user.setName("syx");
//        user.setEmail("@qq.com");
//        user.setPassword("123");
//        userRepository.save(user);
//
//    }
//    @Test
//    public void delete() {
//        User user= new User();
////        user.setId(1);
////        user.setName("syx");
////        user.setEmail("@qq.com");
////        user.setPassword("123");
////        userRepository.deleteById(user.getId());
//
//    }
//    @Test
//    public void fortest() {
//        Date d1 = new Date();
//        System.out.println("当前时间:");
//        System.out.println(d1);
//        System.out.println();
//        // 从1970年1月1日 早上8点0分0秒 开始经历的毫秒数
//        Date d2 = new Date(5000);
//        System.out.println("从1970年1月1日 早上8点0分0秒 开始经历了5秒的时间");
//        System.out.println(d2);
//    }

    @Test
    public void testtest() {
        SearchRequestBuilder searchRequestBuilder;
//        String query = " { \"query\":{\"match_all\" : {\"boost\" : 1.0}}}";
        String query = "{ \"match\": { \"lyric\": \"him departure\" } }";
        WrapperQueryBuilder wrapperQueryBuilder = QueryBuilders.wrapperQuery(query);
        searchRequestBuilder = esTemplate.getClient().prepareSearch("song");
        searchRequestBuilder.setQuery(QueryBuilders.wrapperQuery(query));
        SearchResponse response = searchRequestBuilder.execute().actionGet();
        SearchHit[] result = response.getHits().getHits();
        System.out.println(1);


    }

    @Test

    public void test3() {
        String lyric = "him departure";
//        MatchQuery matchQuery = new MatchQuery(1);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(matchQuery("title", articleTitle).minimumShouldMatch("75%"))
                .withQuery(QueryBuilders.matchQuery("lyric", lyric).minimumShouldMatch("75%"))
                .build();

        List<Song> songList = esTemplate.queryForList(searchQuery, Song.class);
        System.out.println(songList);
    }

    @Test

    public void test4() {
        User user = userRepository.findUserByHost("31701282");


        UserRecord userRecord = user.getRecord();
        List<RecordSong> recordSongList = userRecord.getSongs();
        List<RecordSong> newRecordSongList = new ArrayList<>();
        Boolean isExist = false;
        RecordSong recordSong = new RecordSong();


        for (RecordSong recordtmpSong : recordSongList) {

            recordSong = recordtmpSong;
            recordSong.setCnt((int) (Math.random() * 100) + 1);
            newRecordSongList.add(recordSong);

        }


//        keySongList.removeIf(song -> song.getSong_id().equals(song_id));
//        userCollection.setSongs(keySongList);
//        IndexRequest indexRequest = new IndexRequest();
//        indexRequest.source("collection", userCollection);
//        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(user.getHost()).withClass(User.class).withIndexRequest(indexRequest).build();
//        elasticsearchTemplate.update(updateQuery);
        userRecord.setSongs(newRecordSongList);
        user.setRecord(userRecord);

        userRepository.save(user);
    }

    @Test
    public void test5() throws IOException {
        List<String> str1 = new ArrayList<>();
//        str1.add("rock");
//        str1.add("pop");
//        str1.add("hip hop");
//        str1.add("electronic");
//        str1.add("jazz");
//        str1.add("metal");
//        str1.add("latin");
//        str1.add("dance");
        str1.add("all");
        for (String i : str1){
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\孙宇轩\\workshop\\pycharm\\music_recommend\\deal_with_data\\"+i+".txt")),
                    "UTF-8"));
            String lineTxt = null;
            List<String>  arrayList = new ArrayList<>();
            List<KeySong> keySongList = new ArrayList<>();
            int cnt =0;
            while ((lineTxt = br.readLine()) != null && cnt<=30) {
                cnt+=1;
                if(!lineTxt.equals("")){
                    Song  song = songRepository.queryById(lineTxt);
                    KeySong keySong = new KeySong();
                    keySong.setPic_url(song.getPic_url());
                    keySong.setArtist_id(song.getArtist_id());
                    keySong.setRelease(song.getRelease());
                    keySong.setSong_name(song.getName());
                    keySong.setSong_id(song.getId());
                    keySong.setArtist_name(song.getArtist_name());
                    keySongList.add(keySong);
                }
                Prank prank = new Prank();
                prank.setTag(i);
                prank.setSongs(keySongList);
                prankRepository.save(prank);
        }


//            System.out.println(lineTxt);
        }

    }
}
