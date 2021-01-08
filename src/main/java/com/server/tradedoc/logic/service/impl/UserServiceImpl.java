package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.config.exception.CustomException;
import com.server.tradedoc.constants.AppConstant;
import com.server.tradedoc.logic.converter.CodeSignUpConverter;
import com.server.tradedoc.logic.converter.UserConverter;
import com.server.tradedoc.logic.dto.CodeSignUpDTO;
import com.server.tradedoc.logic.dto.UserSignUpDTO;
import com.server.tradedoc.logic.dto.custom.UserDTOCustom;
import com.server.tradedoc.logic.dto.reponse.MessageSuccess;
import com.server.tradedoc.logic.dto.request.ChangePassRequest;
import com.server.tradedoc.logic.entity.CodeSignUpEntity;
import com.server.tradedoc.logic.entity.UserEntity;
import com.server.tradedoc.logic.repository.CodeSignUpRepository;
import com.server.tradedoc.logic.repository.RoleRepository;
import com.server.tradedoc.logic.repository.UserRepository;
import com.server.tradedoc.logic.service.UserService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.JwtTokenUtils;
import com.server.tradedoc.utils.MailUtils;
import com.server.tradedoc.utils.RandomUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * UserServiceImpl : class implements UserService {com.server.tradedoc.logic.service}
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

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    /**
     * signUpUser : function signup user
     *
     * @param userSignUpDTO: param for signup {com.server.tradedoc.logic.dto}
     * @return MessageSuccess : response of API {com.server.tradedoc.logic.dto.reponse}
     * @throws CustomException
     * @throws MessagingException
     */
    @Override
    @Transactional
    public MessageSuccess signUpUser(UserSignUpDTO userSignUpDTO) throws CustomException, MessagingException {
        MessageSuccess result = new MessageSuccess();
        // validate params
        if (StringUtils.isBlank(userSignUpDTO.getEmail())) {
            throw new CustomException("ERROR_0003", "email not valid!");
        } else {
            if (!mailUtils.validate(userSignUpDTO.getEmail())) {
                throw new CustomException("ERROR_0030", "email invalid format");
            }
        }
        if (userSignUpDTO.getPassword().length() < 8) {
            throw new com.server.tradedoc.utils.error.CustomException("password is at least eight characters", CommonUtils.putError("password", "ERR_0034"));
        }
        // check user
        UserEntity user = userRepository.findOneByUsernameAndStatus(userSignUpDTO.getEmail(), AppConstant.ACTIVE.ACTIVE_STATUS);
        if (user != null) {
            throw new CustomException("ERROR_0034", "username or email already exist");
        }
        // check code empty or no empty
        if (StringUtils.isBlank(userSignUpDTO.getCode())) {
            // send mail confirm sign up to email of user sign up
            String uuid = RandomUtils.randomCode();
            CodeSignUpDTO codeSignUpDTO = new CodeSignUpDTO();
            codeSignUpDTO.setCode(uuid);
            codeSignUpDTO.setEmail(userSignUpDTO.getEmail());
            codeSignUpRepository.save(codeSignUpConverter.toEntity(codeSignUpDTO));
            String template = "support indicatormarkets!! \nthank you for sign up! \ncode for your using confirm signup account: " + uuid + "";
            String subject = "code confirm sign up";
            if (mailUtils.sendMailUseTemplate(template, null, userSignUpDTO.getEmail(), subject)) {
                result.setCodeSuccess("200");
                result.setMessageSuccess("Check your mail");
            } else {
                result.setCodeSuccess("500");
                result.setMessageSuccess("ERROR send mail");
            }
            return result;
        }
        CodeSignUpEntity codeSignUpEntity = codeSignUpRepository.findByCodeAndEmail(userSignUpDTO.getCode(), userSignUpDTO.getEmail());
        if (codeSignUpEntity.getCode().equals(userSignUpDTO.getCode())) {
            // sign up after confirm code sign up
            UserEntity userEntity = new UserEntity();
            userEntity.setStatus(1);
            userEntity.setUserName(userSignUpDTO.getEmail());
            userEntity.setEmail(userSignUpDTO.getEmail());
            userEntity.setPassWord(passwordEncoder.encode(userSignUpDTO.getPassword()));
            List<Long> roleId = new ArrayList<>();
            // auto sign up for user customer
            if (true) {
                roleId = Arrays.asList(3L);
            }
            userEntity.setRoles(roleRepository.findByIdIn(roleId));
            userEntity.setFullName(userSignUpDTO.getFullName());
            userEntity.setNumberPhone(userSignUpDTO.getPhoneNumber());
            userEntity.setCreatedDate(Instant.now());
            userEntity.setModifiedDate(Instant.now());
            userRepository.save(userEntity);
            codeSignUpRepository.deleteByCodeAndEmail(userSignUpDTO.getCode(), userSignUpDTO.getEmail());
            result.setCodeSuccess("200");
            result.setMessageSuccess("Sign Up Success");
        } else {
            throw new com.server.tradedoc.utils.error.CustomException("invalid confirm code change password", CommonUtils.putError("code", "ERR_0034"));
        }

        // response
        return result;
    }

    /**
     * findOne : find information user using login
     *
     * @return UserDTOCustom : DTO response of API {com.server.tradedoc.logic.dto.custom}
     */
    @Override
    public UserDTOCustom findOne() {
        // response after find by id user form token
        return userConverter.convertUserDTOCustom(userRepository.findById(jwtTokenUtils.getUserIdFromToken()).get());
    }

    /**
     * updateProfile : function update profile of user
     *
     * @param userDTOCustom : params for update profile of user {com.server.tradedoc.logic.dto.custom}
     * @return MessageSuccess : response of API
     */
    @Override
    @Transactional
    public MessageSuccess updateProfile(UserDTOCustom userDTOCustom) {
        MessageSuccess result = new MessageSuccess();
        if (userDTOCustom.getFullName().equals("")) {
            throw new com.server.tradedoc.utils.error.CustomException("fullname empty", CommonUtils.putError("fullname", "ERR_0034"));
        }
        if (userDTOCustom.getNumberPhone().equals("")) {
            throw new com.server.tradedoc.utils.error.CustomException("numberphone empty", CommonUtils.putError("numberphone", "ERR_0034"));
        }
        UserEntity userEntity = userRepository.findById(jwtTokenUtils.getUserIdFromToken()).get();
        userEntity.setFullName(userDTOCustom.getFullName());
        userEntity.setNumberPhone(userDTOCustom.getNumberPhone());
        userRepository.save(userEntity);
        result.setCodeSuccess("200");
        result.setMessageSuccess("update profile success");
        return result;
    }

    /**
     * changePassword : function using change pass of user login
     *
     * @param changePassRequest : param for change pass user login {com.server.tradedoc.logic.dto.request}
     * @return MessageSuccess : DTO response of API
     */
    @Override
    @Transactional
    public MessageSuccess changePassword(ChangePassRequest changePassRequest) {
        MessageSuccess result = new MessageSuccess();
        // validate params
        if (changePassRequest.getPasswordOld().equals("")) {
            throw new com.server.tradedoc.utils.error.CustomException("password old empty", CommonUtils.putError("passwordOld", "ERR_0034"));
        }
        if (changePassRequest.getPasswordNew().equals("")) {
            throw new com.server.tradedoc.utils.error.CustomException("password new empty", CommonUtils.putError("passwordNew", "ERR_0034"));
        }
        if (changePassRequest.getPasswordNew().length() < 8) {
            throw new com.server.tradedoc.utils.error.CustomException("password is at least eight characters", CommonUtils.putError("password", "ERR_0034"));
        }
        UserEntity userEntity = userRepository.findById(jwtTokenUtils.getUserIdFromToken()).get();
        if (!passwordEncoder.matches(changePassRequest.getPasswordOld(), userEntity.getPassWord())) {
            throw new com.server.tradedoc.utils.error.CustomException("password old not match", CommonUtils.putError("passwordOld", "ERR_0034"));
        }
        // send code confirm to mail
        if (StringUtils.isBlank(changePassRequest.getCode())) {
            String uuid = RandomUtils.randomCode();
            CodeSignUpDTO codeSignUpDTO = new CodeSignUpDTO();
            codeSignUpDTO.setCode(uuid);
            codeSignUpDTO.setEmail(jwtTokenUtils.getEmailFromToken());
            codeSignUpRepository.save(codeSignUpConverter.toEntity(codeSignUpDTO));
            String template = "support indicatormarkets!! \ncode for your using confirm change password account: " + uuid + "";
            String subject = "code confirm change password";
            if (mailUtils.sendMailUseTemplate(template, null, jwtTokenUtils.getEmailFromToken(), subject)) {
                result.setCodeSuccess("200");
                result.setMessageSuccess("Check your mail");
            } else {
                result.setCodeSuccess("500");
                result.setMessageSuccess("ERROR send mail");
            }
            return result;
        }
        // update password for user login
        CodeSignUpEntity codeSignUpEntity = codeSignUpRepository.findByCodeAndEmail(changePassRequest.getCode(), jwtTokenUtils.getEmailFromToken());
        if (codeSignUpEntity.getCode().equals(changePassRequest.getCode())) {
            userEntity.setPassWord(passwordEncoder.encode(changePassRequest.getPasswordNew()));
            userRepository.save(userEntity);
            result.setCodeSuccess("200");
            result.setMessageSuccess("update profile success");
        } else {
            throw new com.server.tradedoc.utils.error.CustomException("invalid confirm code change password", CommonUtils.putError("code", "ERR_0034"));
        }

        // response
        return result;
    }
}
