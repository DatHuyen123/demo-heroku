package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.config.exception.CustomException;
import com.server.tradedoc.constants.AppConstant;
import com.server.tradedoc.logic.converter.CodeSignUpConverter;
import com.server.tradedoc.logic.dto.CodeSignUpDTO;
import com.server.tradedoc.logic.dto.UserSignUpDTO;
import com.server.tradedoc.logic.dto.reponse.MessageSuccess;
import com.server.tradedoc.logic.entity.CodeSignUpEntity;
import com.server.tradedoc.logic.entity.UserEntity;
import com.server.tradedoc.logic.repository.CodeSignUpRepository;
import com.server.tradedoc.logic.repository.RoleRepository;
import com.server.tradedoc.logic.repository.UserRepository;
import com.server.tradedoc.logic.service.UserService;
import com.server.tradedoc.utils.MailUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * UserServiceImpl
 *
 * @author DatDV
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CodeSignUpRepository codeSignUpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CodeSignUpConverter codeSignUpConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MailUtils mailUtils;

    @Override
    @Transactional
    public MessageSuccess signUpUser(UserSignUpDTO userSignUpDTO) throws CustomException, MessagingException {
        MessageSuccess result = new MessageSuccess();
        if(StringUtils.isBlank(userSignUpDTO.getEmail())){
            throw new CustomException("ERROR_0003" , "email not valid!");
        } else {
            if(!mailUtils.validate(userSignUpDTO.getEmail())){
                throw new CustomException("ERROR_0030" , "email invalid format");
            }
        }
        UserEntity user = userRepository.findOneByUsernameAndStatus(userSignUpDTO.getEmail() , AppConstant.ACTIVE.ACTIVE_STATUS);
        if(user != null){
            throw new CustomException("ERROR_0034" , "username or email already exist");
        }
        if(StringUtils.isBlank(userSignUpDTO.getCode())){
            String uuid = UUID.randomUUID().toString();
            CodeSignUpDTO codeSignUpDTO = new CodeSignUpDTO();
            codeSignUpDTO.setCode(uuid);
            codeSignUpDTO.setEmail(userSignUpDTO.getEmail());
            codeSignUpRepository.save(codeSignUpConverter.toEntity(codeSignUpDTO));
            String template = "AE ANTSOFT CU Hello World!! \n Thank you for sign up! \n UUID FOR confirm: "+uuid+"";
            String subject = "UUID confirm sign up";
            if(mailUtils.sendMailUseTemplate(template , null , userSignUpDTO.getEmail() , subject)){
                result.setCodeSuccess("200");
                result.setMessageSuccess("Check your mail");
            } else {
                result.setCodeSuccess("500");
                result.setMessageSuccess("ERROR send mail");
            }
            return result;
        }
        CodeSignUpEntity codeSignUpEntity = codeSignUpRepository.findByCodeAndEmail(userSignUpDTO.getCode() , userSignUpDTO.getEmail());
        if(codeSignUpEntity.getCode().equals(userSignUpDTO.getCode())){
            UserEntity userEntity = new UserEntity();
            userEntity.setStatus(1);
            userEntity.setUserName(userSignUpDTO.getEmail());
            userEntity.setEmail(userSignUpDTO.getEmail());
            userEntity.setPassWord(passwordEncoder.encode(userSignUpDTO.getPassword()));
            List<Long> roleId = Arrays.asList(1L , 2L);
            userEntity.setRoles(roleRepository.findByIdIn(roleId));
            userRepository.save(userEntity);
            codeSignUpRepository.deleteByCodeAndEmail(userSignUpDTO.getCode() , userSignUpDTO.getEmail());
            result.setCodeSuccess("200");
            result.setMessageSuccess("Sign Up Success");
        }
        return result;
    }
}