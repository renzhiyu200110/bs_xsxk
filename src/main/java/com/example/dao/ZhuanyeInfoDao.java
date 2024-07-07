package com.example.dao;

import com.example.entity.AdminInfo;
import com.example.entity.XueyuanInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface XueyuanInfoDao extends Mapper<XueyuanInfo> {
    @Select("select * from xueyuan_info where name = #{name} and password = #{password}")
    XueyuanInfo findByNameAndPass(@Param("name") String name, @Param("password")String password);

    @Select("select * from xueyuan_info where name = #{name} ")
    XueyuanInfo findByName(@Param("name") String name);
    @Select("select * from xueyuan_info where name like  concat('%',#{name},'%')")
    List<XueyuanInfo> findByNamePage(String name);
    @Select("select * from xueyuan_info where name like  concat('%',#{search},'%')")
    List<XueyuanInfo> find(String search);
}
