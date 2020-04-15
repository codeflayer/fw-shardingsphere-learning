package com.yisu.shardingsphere.subtable;

import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import com.yisu.shardingsphere.common.model.SysUser;
import com.yisu.shardingsphere.common.service.SysUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SysUserServiceImplSubTableTenant {
    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testSelect(){
        int count = sysUserService.count();
        System.out.println(count);
    }

    @Test
    public void testInsert(){
        SysUser sysUser=new SysUser();
        sysUser.setAvatar("/home/avatar");
        sysUser.setCreateTime(DateUtil.date());
        sysUser.setCreateUser("sys");
        sysUser.setDeleteFlag(0);
        sysUser.setDeptCode("depe");
        sysUser.setDisableFlag(0);
        sysUser.setEmail("***@123.com");
        sysUser.setPassword("123456");
        sysUser.setPosCode("pos");
        sysUser.setRealName("realname");
        sysUser.setUpdateTime(DateUtil.date());
        sysUser.setUserName("fwcloud");
        sysUser.setUpdateUser("sys");
        sysUser.setUserPhone("12345678912");
        sysUserService.save(sysUser);
    }


    @Test
    public void testBatch(){
        List<String> tenantList = Lists.newArrayList("nike", "adidas", "converse");
        List<SysUser> list=new ArrayList<>();
        for (int i = 0; i <100 ; i++) {


            SysUser sysUser=new SysUser();
            sysUser.setAvatar("/home/avatar");
            sysUser.setCreateTime(DateUtil.date());
            sysUser.setCreateUser("sys"+i);
            sysUser.setDeleteFlag(0);
            sysUser.setDeptCode("depe"+i);
            sysUser.setDisableFlag(0);
            sysUser.setEmail("***@123.com"+i);
            sysUser.setPassword("123456");
            sysUser.setPosCode("pos");
            sysUser.setRealName("realname"+i);
            sysUser.setUpdateTime(DateUtil.date());
            sysUser.setUserName("fwcloud"+i);
            sysUser.setUpdateUser("sys"+i);
            sysUser.setUserPhone("12345678912"+i);
            sysUser.setTenant(tenantList.get(new Random().nextInt(tenantList.size())));
            list.add(sysUser);
        }

        boolean saveBatch = sysUserService.saveBatch(list);
        Assert.assertEquals(true,saveBatch);
    }


    @Test
    public void testSelectAll(){
        List<SysUser> sysUserList = sysUserService.list();
        System.out.println(sysUserList.size());
    }



}