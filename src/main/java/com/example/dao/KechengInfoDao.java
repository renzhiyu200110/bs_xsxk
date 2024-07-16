package com.example.dao;

import com.example.entity.KechengInfo;
import com.example.entity.ZhuanyeInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface KechengInfoDao extends Mapper<KechengInfo> {
    @Select("select * from kecheng_info where name = #{name} ")
   KechengInfo findByName(String name);

    @Select("select * from kecheng_info where name like  concat('%',#{name},'%')")
    List<KechengInfo> findByNamePage(String name);

    @Select("select * from kecheng_info where name like  concat('%',#{search},'%')")
    List<KechengInfo> find(String search);
}
