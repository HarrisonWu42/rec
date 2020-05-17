package cn.edu.zucc.syx.rec.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600)
@RestController
@RequestMapping("/demo")
public class TableController {

    @Autowired
    private TableServiceTest service;

    @GetMapping("/demo")
    public void findAllPage(){
        Pageable pageable= PageRequest.of(0,5);
        Page<Table01> page = service.findAll(pageable);
        for (Table01 table01:page.getContent()){
            System.out.println(table01);
        }
    }

    @PostMapping("/demo")
    public void save20(){
        for (int i=1;i<20;i++){
            Table01 table01=new Table01();
            table01.setId(i);
            table01.setTitle(i+"ES版本");
            table01.setContent(i+"基于Lucene搜索服务器");
            service.save(table01);
        }
    }
}
