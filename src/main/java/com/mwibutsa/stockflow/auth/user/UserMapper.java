package com.mwibutsa.stockflow.auth.user;

import com.mwibutsa.stockflow.auth.user.dto.CreateUserRequest;
import com.mwibutsa.stockflow.auth.user.dto.UserResponse;
import com.mwibutsa.stockflow.common.mapper.BaseMapper;
import com.mwibutsa.stockflow.common.mapper.ToEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends ToEntityMapper<User, CreateUserRequest> , BaseMapper<User, UserResponse> {

}
