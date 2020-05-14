package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.entity.*;
import cn.edu.zucc.syx.rec.respository.SheetRepository;
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

    @Autowired
    public SheetServiceImpl(SheetRepository sheetRepository, UserRepository userRepository) {
        this.sheetRepository = sheetRepository;
        this.userRepository = userRepository;
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
        userSheet.setUsersheet_name(sheetName);

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
    public Boolean open(String host, String sheetId) {
        Sheet sheet = sheetRepository.findById(sheetId);
        User user = userRepository.findUserByHost(host);
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
    public Boolean close(String host, String sheetId) {
        Sheet sheet = sheetRepository.findById(sheetId);
        User user = userRepository.findUserByHost(host);
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
}
