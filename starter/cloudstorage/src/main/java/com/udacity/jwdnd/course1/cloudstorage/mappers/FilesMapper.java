package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.entities.Files;

@Mapper
public interface FilesMapper {
	
	@Select("select * from files where files.userid = #{id}")
    List<Files> findNoteByUserId(@Param("id") Integer id);

}
