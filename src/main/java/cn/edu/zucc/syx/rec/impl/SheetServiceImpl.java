package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.respository.SheetRepository;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.respository.UserRepository;
import cn.edu.zucc.syx.rec.service.SheetService;
import cn.edu.zucc.syx.rec.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SheetServiceImpl implements SheetService {
    private final SheetRepository sheetRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    @Autowired
    public SheetServiceImpl(SheetRepository sheetRepository, UserRepository userRepository, SongRepository songRepository) {
        this.sheetRepository = sheetRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Override
    public Sheet create(String sheetName, String description, String host) {
        Sheet sheet = new Sheet();

        sheet.setName(sheetName);
        sheet.setDescription(description);
        sheet.setCreator_id(host);
        sheet.setIs_open(false);    // 默认是不开放

        List<KeySong> songs = new ArrayList<>();
        sheet.setSongs(songs);

        User user = userRepository.findUserByHost(host);
        sheet.setCreator_name(user.getName());

        Boolean isNotSheetExist = false;
        String s = Tool.getRandomString(20);
        while (isNotSheetExist == false){
            if (sheetRepository.findById(s) == null){
                isNotSheetExist = true;
            }else {
                s = Tool.getRandomString(20);
            }
        }

        sheet.setId(s);
        sheetRepository.save(sheet);

        List<UserSheets> userSheets= user.getCollection().getSheets();
        UserSheets userSheet = new UserSheets();
        userSheet.setCreator_id(host);
        userSheet.setCreator_name(user.getName());
        userSheet.setDescription(description);
        userSheet.setIs_open(false);
        userSheet.setSheet_id(s);
        userSheet.setSheet_name(sheetName);

        userSheets.add(userSheet);

        UserCollection userCollection = user.getCollection();
        userCollection.setSheets(userSheets);
        user.setCollection(userCollection);
        userRepository.save(user);

        return sheet;
    }

    @Override
    public Sheet delete(String host, String sheetId) {
        Sheet sheet = sheetRepository.findById(sheetId);
        sheetRepository.deleteById(sheetId);
        User user = userRepository.findUserByHost(host);
        UserCollection collection = user.getCollection();
        List<UserSheets> userSheets = user.getCollection().getSheets();
        userSheets.removeIf(s -> sheetId.equals(s.getSheet_id()));
        collection.setSheets(userSheets);
        user.setCollection(collection);
        userRepository.save(user);
        return sheet;
    }

    @Override
    public Sheet collect(String host, String sheedId) {
        Sheet sheet = sheetRepository.findById(sheedId);

        Sheet sheetCopy = new Sheet();

        sheetCopy.setName(sheet.getName());
        sheetCopy.setDescription(sheet.getDescription());
        sheetCopy.setCreator_id(host);
        sheetCopy.setIs_open(false);    // 默认是不开放
        sheetCopy.setSongs(sheet.getSongs());

        User user = userRepository.findUserByHost(host);
        sheetCopy.setCreator_name(user.getName());

        Boolean isNotSheetExist = false;
        String s = Tool.getRandomString(20);
        while (isNotSheetExist == false){
            if (sheetRepository.findById(s) == null){
                isNotSheetExist = true;
            }else {
                s = Tool.getRandomString(20);
            }
        }

        sheetCopy.setId(s);
        sheetRepository.save(sheetCopy);

        List<UserSheets> userSheets= user.getCollection().getSheets();
        UserSheets userSheet = new UserSheets();
        userSheet.setCreator_id(host);
        userSheet.setCreator_name(user.getName());
        userSheet.setDescription(sheetCopy.getDescription());
        userSheet.setIs_open(false);
        userSheet.setSheet_id(s);
        userSheet.setSheet_name(sheetCopy.getName());

        userSheets.add(userSheet);

        UserCollection userCollection = user.getCollection();
        userCollection.setSheets(userSheets);
        user.setCollection(userCollection);
        userRepository.save(user);

        return sheetCopy;
    }

    @Override
    public Boolean open(String sheetId) {
        Sheet sheet = sheetRepository.findById(sheetId);
        User user = userRepository.findUserByHost(sheet.getCreator_id());
        sheet.setIs_open(true);
        sheetRepository.save(sheet);
        List<UserSheets> userSheets = user.getCollection().getSheets();
        for (UserSheets s:userSheets){
            if (sheetId.equals(s.getSheet_id())){
                s.setIs_open(true);
            }
        }
        UserCollection collection = user.getCollection();;
        collection.setSheets(userSheets);
        user.setCollection(collection);
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean close(String sheetId) {
        Sheet sheet = sheetRepository.findById(sheetId);
        User user = userRepository.findUserByHost(sheet.getCreator_id());
        sheet.setIs_open(false);
        sheetRepository.save(sheet);
        List<UserSheets> userSheets = user.getCollection().getSheets();
        for (UserSheets s:userSheets){
            if (sheetId.equals(s.getSheet_id())){
                s.setIs_open(false);
            }
        }
        UserCollection collection = user.getCollection();;
        collection.setSheets(userSheets);
        user.setCollection(collection);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserSheets> listAll(String host) {
        User user = userRepository.findUserByHost(host);
        List<UserSheets> sheets = user.getCollection().getSheets();
        return sheets;
    }

    @Override
    public Boolean addSong2Sheet(String sheetId, String songId) {
        Sheet sheet = sheetRepository.findById(sheetId);
        List<KeySong> songs = sheet.getSongs();
        Song song = songRepository.queryById(songId);
        KeySong keySong = new KeySong();
        keySong.setArtist_id(song.getArtist_id());
        keySong.setArtist_name(song.getArtist_name());
        keySong.setRelease(song.getRelease());
        keySong.setSong_id(song.getId());
        keySong.setSong_name(song.getName());
        songs.add(keySong);
        sheet.setSongs(songs);
        sheetRepository.save(sheet);
        return true;
    }

    @Override
    public Boolean deleteSongFromSheet(String sheetId, String songId) {
        Sheet sheet = sheetRepository.findById(sheetId);
        List<KeySong> songs = sheet.getSongs();
        songs.removeIf(s -> songId.equals(s.getSong_id()));
        sheet.setSongs(songs);
        sheetRepository.save(sheet);
        return true;
    }

    @Override
    public List<Sheet> searchByName(String name){
//        return sheetRepository.queryByNameLike("%"+name+"%");
//        return sheetRepository.queryByNameLike(name);
        List<Sheet> sheetList = sheetRepository.queryByNameContains(name);
        List<Sheet> sheets = new ArrayList<>();
        for (Sheet s:sheetList){
            if (s.getIs_open()){
                sheets.add(s);
            }
        }

        return sheets;
    }

    @Override
    public Sheet getInfo(String sheetId) {
        return sheetRepository.findById(sheetId);
    }
}
