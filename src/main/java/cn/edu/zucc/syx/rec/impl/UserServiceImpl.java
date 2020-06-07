package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.form.UserEditForm;
import cn.edu.zucc.syx.rec.form.UserForm;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.respository.UserRepository;
import cn.edu.zucc.syx.rec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SongRepository songRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Override
    public User create(UserForm userForm){
        User user = new User();
        user.setHost(userForm.getHost());
        user.setPassword(userForm.getPassword());
        user.setName(userForm.getName());
        user.setAge(0);
        user.setPhone(userForm.getPhone());
        user.setEmail(userForm.getEmail());
        user.setSex(userForm.getSex());
        UserCollection collection = new UserCollection();
        List<KeySong> collectSongs = new ArrayList<>();
        List<KeyArtists> collectArtists = new ArrayList<>();
        List<UserSheets> collectSheets = new ArrayList<>();
        collection.setArtists(collectArtists);
        collection.setSheets(collectSheets);
        collection.setSongs(collectSongs);
        user.setCollection(collection);

        UserRec rec = new UserRec();
        List<KeySong> recSongs = new ArrayList<>();
        List<KeyArtists> recArtists = new ArrayList<>();
        rec.setSongs(recSongs);
        rec.setArtists(recArtists);
        user.setRec(rec);

        UserRecord record = new UserRecord();
        List<RecordSong> recordSongs = new ArrayList<>();
        record.setSongs(recordSongs);
        UserRecord records = new UserRecord();

//        records.add(record);
        user.setRecord(records);
        userRepository.save(user);
        return user;
    }

    @Override
    public String delete(User user){
        try {
            userRepository.delete(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "defeated";
        }
    }


    @Override
    public RecordSong addRecordSong(String host, String song_id) {
        User user = userRepository.findUserByHost(host);
        Song song  = songRepository.queryById(song_id);

        UserRecord userRecord = user.getRecord();
        List<RecordSong> recordSongList = userRecord.getSongs();
        Boolean isExist = false;
        RecordSong recordSong = new RecordSong();

        if(recordSongList!=null && !recordSongList.isEmpty()) {
            for (RecordSong recordtmpSong : recordSongList) {
                if (recordtmpSong.getSong_id().equals(song_id)) {
                    recordSongList.remove(recordtmpSong);
                    isExist = true;
                    recordSong = recordtmpSong;
                    break;
                }
            }
        }
        else {
            List<RecordSong> tmprecordSongList = new ArrayList<>();
            recordSongList = tmprecordSongList;
        }

        if (isExist == true){
            recordSong.setCnt(recordSong.getCnt()+1);
            recordSong.setDate(new Date());
        }else {
            recordSong.setSong_id(song_id);
            recordSong.setSong_name(song.getName());
            recordSong.setArtist_name(song.getArtist_name());
            recordSong.setRelease(song.getRelease());
            recordSong.setArtist_id(song.getArtist_id());
            recordSong.setDate(new Date());
            recordSong.setPic_url(song.getPic_url());
            recordSong.setCnt(1);
        }


        recordSongList.add(recordSong);
//        keySongList.removeIf(song -> song.getSong_id().equals(song_id));
//        userCollection.setSongs(keySongList);
//        IndexRequest indexRequest = new IndexRequest();
//        indexRequest.source("collection", userCollection);
//        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(user.getHost()).withClass(User.class).withIndexRequest(indexRequest).build();
//        elasticsearchTemplate.update(updateQuery);
        userRecord.setSongs(recordSongList);
        user.setRecord(userRecord);
        userRepository.save(user);
        return recordSong;
    }

    @Override
    public List<RecordSong> listRecordSongs(String host) {
        User user = userRepository.findUserByHost(host);
        List<RecordSong> recordSongList = user.getRecord().getSongs();
        sortClass sort = new sortClass();
        Collections.sort(recordSongList,sort);
//        recordSongList.sort(Da);
//        List<Song> songList = new ArrayList<>();
//        for(RecordSong recordSong:recordSongList){
//            songList.add(songRepository.queryById(recordSong.getSong_id()));
//        }
        return  recordSongList;
//        return null;
    }

    @Override
    public User deleteInfo(String host) {
        User user = userRepository.findUserByHost(host);
        UserCollection collection = new UserCollection();
        List<KeySong> collectSongs = new ArrayList<>();
        List<KeyArtists> collectArtists = new ArrayList<>();
        List<UserSheets> collectSheets = new ArrayList<>();
        collection.setArtists(collectArtists);
        collection.setSheets(collectSheets);
        collection.setSongs(collectSongs);
        user.setCollection(collection);

        UserRec rec = new UserRec();
        List<KeySong> recSongs = new ArrayList<>();
        List<KeyArtists> recArtists = new ArrayList<>();
        rec.setSongs(recSongs);
        rec.setArtists(recArtists);
        user.setRec(rec);

        UserRecord record = new UserRecord();
        List<RecordSong> recordSongs = new ArrayList<>();
        record.setSongs(recordSongs);
        user.setRecord(record);
        userRepository.save(user);
        return user;
    }


    @Override
    public String update(User user){
        try {
            userRepository.save(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "defeated";
        }
    }
    @Override
    public User queryUser(String host){
        return userRepository.findUserByHost(host);
    }

    @Override
    public User editUser(UserEditForm form) {
        User user = userRepository.findUserByHost(form.getHost());
        user.setHost(form.getHost());
        user.setName(form.getName());
        user.setAge(form.getAge());
        user.setPhone(form.getPhone());
        user.setEmail(form.getEmail());
        user.setSex(form.getSex());
        userRepository.save(user);
        return user;
    }

    @Override
    public User login(String host, String pwd){
        return userRepository.queryUserByHostAndPassword(host, pwd);
    }

    @Override
    public Boolean isUserExist(String host){
        User user = userRepository.findUserByHost(host);
        if (user != null){
            return true;
        }
        return false;
    }
    public class sortClass implements Comparator {
        public int compare(Object arg0,Object arg1){
            RecordSong recordSong = (RecordSong) arg0;
            RecordSong recordSong1 = (RecordSong) arg1;
           if(recordSong.getDate().compareTo(recordSong1.getDate())==1)
               return -1;
           else return 1;
        }
    }
}

