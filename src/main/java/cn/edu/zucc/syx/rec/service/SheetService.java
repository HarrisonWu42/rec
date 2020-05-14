package cn.edu.zucc.syx.rec.service;

import cn.edu.zucc.syx.rec.entity.Sheet;

import java.util.List;

public interface SheetService {
    Sheet create(String sheetName, String description, String userId);
    Sheet delete(String host, String sheetId);
    List<Sheet> findByName(String name);
    Boolean open(String sheetId);
    Boolean close(String sheetId);

}
