package com.server.tradedoc.logic.converter;

import com.server.tradedoc.logic.dto.UserDTO;
import com.server.tradedoc.logic.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends MapperManager<UserDTO , UserEntity>{
}
