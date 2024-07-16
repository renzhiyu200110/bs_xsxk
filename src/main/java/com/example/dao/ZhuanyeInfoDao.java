package com.example.dao;

import com.example.entity.XueyuanInfo;
import com.example.entity.ZhuanyeInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ZhuanyeInfoDao extends Mapper<ZhuanyeInfo> {
    @Select("select * from zhuanye_info where name = #{name} ")
    ZhuanyeInfo findByName(String name);

    @Select("select * from zhuanye_info where name like  concat('%',#{name},'%')")
    List<ZhuanyeInfo> findByNamePage(String name);

    @Select("select * from zhuanye_info where name like  concat('%',#{search},'%')")
    List<ZhuanyeInfo> find(String search);
}
