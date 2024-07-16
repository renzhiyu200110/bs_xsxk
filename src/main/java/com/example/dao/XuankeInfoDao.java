package com.example.dao;

import com.example.entity.KechengInfo;
import com.example.entity.XuankeInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface XuankeInfoDao extends Mapper<XuankeInfo> {
    @Select("select * from xuanke_info where name = #{name} ")
    XuankeInfo findByName(String name);

    @Select("select * from xuanke_info where name like  concat('%',#{name},'%')")
    List<XuankeInfo> findByNamePage(String name);

    //从课程名称和学生id找出有咩有选过
    @Select("select * from xuanke_info where name = #{name}  and xueshengid = #{xueshengid}" )
    XuankeInfo find(@Param("name") String name, @Param("xueshengid") Long xueshengid);

    List<XuankeInfo> findBycondition(@Param("teacherid") Long teacherid,@Param("xueshengid")Long xueshengid);

//    @Select("select * from xuanke_info where name like  concat('%',#{search},'%')")
//    List<XuankeInfo> find2(String search);
}
