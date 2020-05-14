package cn.edu.zucc.syx.rec.service;

import cn.edu.zucc.syx.rec.entity.Sheet;

public interface SheetService {
    Sheet create(String sheetName, String description, String userId);
    Sheet delete(String host, String sheetId);
    Boolean open(String sheetId);
    Boolean close(String sheetId);

}
