package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.logic.converter.CodeSignUpConverter;
import com.server.tradedoc.logic.converter.CustomersConverter;
import com.server.tradedoc.logic.dto.CodeSignUpDTO;
import com.server.tradedoc.logic.dto.CustomersDTO;
import com.server.tradedoc.logic.dto.reponse.MessageSuccess;
import com.server.tradedoc.logic.entity.CodeSignUpEntity;
import com.server.tradedoc.logic.entity.CustomersEntity;
import com.server.tradedoc.logic.repository.CodeSignUpRepository;
import com.server.tradedoc.logic.repository.CustomersRepository;
import com.server.tradedoc.logic.repository.ProductsRepository;
import com.server.tradedoc.logic.service.CustomersService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.MailUtils;
import com.server.tradedoc.utils.error.CustomException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * CustomersServiceImpl
 *
 * @author DatDV
 */
@Service
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CodeSignUpRepository codeSignUpRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private CodeSignUpConverter codeSignUpConverter;

    @Autowired
    private CustomersConverter customersConverter;

    @Override
    @Transactional
    public MessageSuccess createAddSending(CustomersDTO customersDTO) {
        MessageSuccess result = new MessageSuccess();
        if(StringUtils.isBlank(customersDTO.getEmail())){
            throw new CustomException("email not valid!" , CommonUtils.putError("email" , "ERR_003"));
        } else {
            if (!mailUtils.validate(customersDTO.getEmail())){
                throw new CustomException("email invalid format" , CommonUtils.putError("email" , "ERR_0034"));
            }
        }
        CustomersEntity customersEntity = customersRepository.findOneByEmail(customersDTO.getEmail());
        if(customersEntity == null){
            if(StringUtils.isBlank(customersDTO.getCode())){
                String uuid = UUID.randomUUID().toString();
                CodeSignUpDTO codeSignUpDTO = new CodeSignUpDTO();
                codeSignUpDTO.setCode(uuid);
                codeSignUpDTO.setEmail(customersDTO.getEmail());
                String template = "\n Thank you for create address email for buy product! \n UUID FOR confirm: "+uuid+"";
                String subject = "UUID confirm create address email";
                if(mailUtils.sendMailUseTemplate(template , null , customersDTO.getEmail() , subject)){
                    codeSignUpRepository.save(codeSignUpConverter.toEntity(codeSignUpDTO));
                    result.setCodeSuccess("200");
                    result.setMessageSuccess("Check your mail");
                } else {
                    result.setCodeSuccess("500");
                    result.setMessageSuccess("ERROR send mail");
                }
                return result;
            }
            CodeSignUpEntity codeSignUpEntity = codeSignUpRepository.findByCodeAndEmail(customersDTO.getCode() , customersDTO.getEmail());
            if (codeSignUpEntity.getCode().equals(customersDTO.getCode())){
                CustomersEntity customersEntityAfterInsert = customersRepository.save(customersConverter.toEntity(customersDTO));
                codeSignUpRepository.deleteByCodeAndEmail(customersDTO.getCode() , customersDTO.getEmail());
                if (customersEntityAfterInsert.getId() != null){
                    result.setCodeSuccess("200");
                    result.setMessageSuccess("success");
                    result.setCustomerId(customersEntityAfterInsert.getId());
                }
                return result;
            }
        } else {
            result.setCodeSuccess("200");
            result.setMessageSuccess("success");
            result.setCustomerId(customersEntity.getId());
        }
        return result;
    }
}
