package cn.edu.zucc.syx.rec.demo;

import cn.edu.zucc.syx.rec.demo.Table01;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TableServiceTest {

    //添加
    public void save(Table01 table01);

    //删除
    public void delete(Table01 table01);

    //查询全部
    public Iterable<Table01> findAll();

    //分页查询
    public Page<Table01> findAll(Pageable pageable);
}