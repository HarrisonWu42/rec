package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.entity.Sheet;
import cn.edu.zucc.syx.rec.respository.SheetRepository;
import cn.edu.zucc.syx.rec.service.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SheetServiceImpl implements SheetService {

    private final SheetRepository sheetRepository;

    @Autowired
    public SheetServiceImpl(SheetRepository sheetRepository) {
        this.sheetRepository = sheetRepository;
    }

    @Override
    public String add(Sheet sheet) {
        try {
            sheetRepository.save(sheet);
            return "success";
        }catch (Exception e) {
            e.printStackTrace();
            return "defeated";
        }

    }
}
