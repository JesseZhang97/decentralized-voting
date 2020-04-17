/*
 * @Date: 2020-04-17 21:35:30
 * @Author: zhen
 * @LastEditTime: 2020-04-17 23:39:50
 * @Description: 
 */
package com.zz.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.backend.entity.User;
import com.zz.backend.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ReturnDataService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;

    /**
     * 向前端返回org list
     */
    public List<String> returnOrgList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT organization");
        List<Map<String, Object>> orgListFromDB = userMapper.selectMaps(wrapper);
        List<String> orgList = new ArrayList<>();
        for (Map<String, Object> m : orgListFromDB) { // 取list集合里的那一条Map集合
            for (String s : m.keySet()) { // 取map集合里的String类型的key，
                orgList.add(m.get(s).toString());// 根据key迭代输出value
            }
        }
        // System.out.println(orgList);
        return orgList;
    }

    /**
     * 根据前端select的目录返回voterlist
     */
    public List<Map<String, Object>> returnVoterList(String org) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("organization", "16022");
        wrapper.select("name", "publickey");
        // System.out.println(userMapper.selectMaps(wrapper));
        return userMapper.selectMaps(wrapper);
    }
}