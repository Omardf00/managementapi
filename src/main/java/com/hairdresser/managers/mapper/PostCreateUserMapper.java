package com.hairdresser.managers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hairdresser.managers.entities.UserDetailEntity;
import com.hairdresser.managers.entities.UserEntity;
import com.hairdresser.managers.model.PostCreateUserRequest;
import com.hairdresser.managers.model.PostCreateUserResponse;

@Mapper(componentModel = "spring")
public interface PostCreateUserMapper {
	
	PostCreateUserMapper INSTANCE = Mappers.getMapper(PostCreateUserMapper.class);
	
	@Mapping(target = "userName", source = "userName")
	@Mapping(target = "role.roleId", source = "role.roleId")
	@Mapping(target = "role.roleName", source = "role.roleName")
	@Mapping(target = "isActive", expression = "java(Boolean.TRUE)")
	UserEntity userEntityMapper(PostCreateUserRequest request);
	
	@Mapping(target = "phoneNumber", source = "details.phoneNumber")
	@Mapping(target = "realName", source = "details.realName")
	@Mapping(target = "firstLastName", source = "details.firstLastName")
	@Mapping(target = "secondLastName", source = "details.secondLastName")
	@Mapping(target = "email", source = "details.email")
	UserDetailEntity userDetailEntityMapper(PostCreateUserRequest request);
	
	@Mapping(target = "result", source = "response")
	PostCreateUserResponse responseMapper(String response);

}
