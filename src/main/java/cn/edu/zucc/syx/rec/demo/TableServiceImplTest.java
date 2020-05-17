package cn.edu.zucc.syx.rec.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TableServiceImplTest implements TableServiceTest {

    @Resource
    private TableDao dao;

    @Override
    public void save(Table01 table01) {
        dao.save(table01);
    }

    @Override
    public void delete(Table01 table01) {
        dao.delete(table01);
    }

    @Override
    public Iterable<Table01> findAll() {
        Iterable<Table01> iter = dao.findAll();
        return iter;
    }

    @Override
    public Page<Table01> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }
}