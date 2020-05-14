package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.entity.KeySong;
import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.entity.User;
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
        return sheet;
    }
}
