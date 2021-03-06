package com.udacity.jwdnd.course1.cloudstorage.mappers;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.entities.Notes;


@Mapper
public interface NotesMapper {
	
	@Select("select * from notes where notes.noteid = #{id}")
		Notes findNoteById(@Param("id") Integer id);
	
	 @Select("select * from notes where notes.userid = #{id}")
	    List<Notes> findNoteByUserId(@Param("id") Integer id);
	 
	 
	 @Insert("insert into notes(notetitle,notedescription, userid ) VALUES (#{note.notetitle}, #{note.notedescription}, #{userid})")
	 	Integer addNotetoDatabase(@Param("note") Notes note, @Param("userid") Integer userid);
	 
	 @Update("UPDATE notes SET notetitle = #{note.notetitle}, notedescription = #{note.notedescription} WHERE noteid = #{note.noteid}")
	 	Integer updateNote(@Param("note") Notes note);
	 
	 @Delete("DELETE FROM notes WHERE noteid = #{noteid}")
	 	Integer deleteNote(@Param("noteid")Integer noteid);
}
