package com.lc.service.loan.impl;

import com.lc.mapper.loan.RiskmMapper;
import com.lc.service.loan.RiskmService;

import javax.annotation.Resource;

public class RiskServiceImpl implements RiskmService {

    @Resource
    private RiskmMapper riskmMapper;

    @Override
    public void insertEmoji(String id, String emoji) {
        riskmMapper.insertEmoji(id,emoji);
    }
}
