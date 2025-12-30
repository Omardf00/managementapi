package com.hairdresser.managers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hairdresser.managers.entities.UserDetailEntity;
import com.hairdresser.managers.model.GetUserResponse;
import com.hairdresser.managers.utils.Constants;

@Mapper(componentModel = "spring", imports = {Constants.class})
public interface GetUserDetailsMapper {
	
	GetUserDetailsMapper INSTANCE = Mappers.getMapper(GetUserDetailsMapper.class);
	
	@Mapping(target = "name.userName", source = "user")
	@Mapping(target = "name.realName", source = "details.realName")
	@Mapping(target = "name.firstLastName", source = "details.firstLastName")
	@Mapping(target = "name.secondLastName", source = "details.secondLastName")
	@Mapping(target = "contactPoint.phoneNumber", source = "details.phoneNumber")
	@Mapping(target = "contactPoint.email", source = "details.email")
	GetUserResponse responseMapper(String user, UserDetailEntity details);

}
