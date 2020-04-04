package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.entities.Notes;

@Mapper
public interface CredentialsMapper {

	@Select("select * from credentials where credentials.credentialid = #{id}")
	Credentials findCredentialById(@Param("id") Integer id);

	@Select("select * from credentials where credentials.userid = #{id}")
	List<Credentials> findCredentialByUserId(@Param("id") Integer id);

	@Insert("insert into credentials(url,username,skeleton,password, userid ) VALUES (#{credential.url}, #{credential.username},#{credential.skeleton},#{credential.password}, #{userid})")
	Integer addCredentialtoDatabase(@Param("credential") Credentials credential, @Param("userid") Integer userid);

	@Update("UPDATE credentials SET url = #{credential.url}, username = #{credential.username}, skeleton = #{credential.skeleton}, password = #{credential.password} WHERE credentialid = #{credential.credentialid}")
	Integer updateCredential(@Param("credential") Credentials credential);

	@Delete("DELETE FROM credentials WHERE credentialid = #{credentialid}")
	Integer deleteCredential(@Param("credentialid") Integer credentialid);

}
