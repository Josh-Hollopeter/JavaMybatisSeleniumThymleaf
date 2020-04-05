package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.entities.Files;

@Mapper
public interface FilesMapper {
	
	@Select("select * from files where files.userid = #{id}")
    List<Files> findFilesByUserId(@Param("id") Integer id);
	
	@Select("select * from files where files.fileid = #{id}")
    Files findFilesByFileId(@Param("id") Integer id);
	
	@Insert("insert into files(filename,contenttype, filesize, filedata, userid ) VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata},#{userid})")
 	Integer addFiletoDatabase(@Param("file") Files file, @Param("userid") Integer userid);
	
	@Delete("delete from files where fileid = #{id}")
	Integer deleteFileFromDatabase(@Param("id") Integer id);

}
