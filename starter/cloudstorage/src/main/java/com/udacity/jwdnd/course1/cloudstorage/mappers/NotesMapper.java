package com.udacity.jwdnd.course1.cloudstorage.mappers;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.entities.Notes;


@Mapper
public interface NotesMapper {
	
	
	 @Select("select * from notes where notes.userid = #{id}")
	    List<Notes> findNoteByUserId(@Param("id") Integer id);

}
