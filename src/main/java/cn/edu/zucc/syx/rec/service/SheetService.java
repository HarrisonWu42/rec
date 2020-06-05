package cn.edu.zucc.syx.rec.service;

import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.entity.UserSheets;

import java.util.List;

public interface SheetService {
    Sheet create(String sheetName, String description, String userId);
    Sheet delete(String host, String sheetId);
    List<Sheet> searchByNameContain(String name);
    Boolean open(String sheetId);
    Boolean close(String sheetId);
    List<UserSheets> listAll(String host);
    Boolean addSong2Sheet(String sheetId, String songId);
    Boolean deleteSongFromSheet(String sheetId, String songId);
    Sheet collect(String host, String sheedId);
    Sheet getInfo(String sheetId);
    Boolean isExist(String sheetId, String songId);
}
