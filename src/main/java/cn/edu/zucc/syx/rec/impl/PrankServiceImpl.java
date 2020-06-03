package cn.edu.zucc.syx.rec.impl;

import cn.edu.zucc.syx.rec.entity.Prank;
import cn.edu.zucc.syx.rec.entity.Song;
import cn.edu.zucc.syx.rec.respository.PrankRepository;
import cn.edu.zucc.syx.rec.respository.SongRepository;
import cn.edu.zucc.syx.rec.service.PrankService;
import cn.edu.zucc.syx.rec.service.SongService;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrankServiceImpl implements PrankService {

    private final PrankRepository prankRepository;

    @Autowired
    public PrankServiceImpl(PrankRepository prankRepository) {
        this.prankRepository = prankRepository;
    }

    @Override
    public Prank searchByTag(String tag) {
        return prankRepository.queryByTag(tag);
    }


}
