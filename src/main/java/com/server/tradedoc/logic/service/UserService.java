package com.server.tradedoc.logic.service;

import com.server.tradedoc.config.exception.CustomException;
import com.server.tradedoc.logic.dto.UserSignUpDTO;
import com.server.tradedoc.logic.dto.reponse.MessageSuccess;

import javax.mail.MessagingException;

/**
 * UserService
 *
 * @author DatDV
 */
public interface UserService {
    MessageSuccess signUpUser(UserSignUpDTO userSignUpDTO) throws CustomException, MessagingException;
}
