package com.application.util.dtoEntityConverter;

import com.application.entity.PaymentMethod;
import com.application.entity.dto.PaymentMethodDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodConverter {

    private static ModelMapper modelMapper;

    public PaymentMethodConverter() {
    }

    @Autowired
    public PaymentMethodConverter(ModelMapper modelMapper) {

        PaymentMethodConverter.modelMapper = modelMapper;
        modelMapper.addMappings(orderToDTOMapping);
        modelMapper.addMappings(DTOToOrderMapping);
    }

    PropertyMap<PaymentMethod, PaymentMethodDTO> orderToDTOMapping = new PropertyMap<>() {
        protected void configure() {
            map().setPaymentId(source.getPaymentMethodId());
            map().setCcNumber(source.getCcNumber());
            map().setCardNetworkType(source.getCardNetworkType().getCardProviderName());
        }
    };

    PropertyMap<PaymentMethodDTO, PaymentMethod> DTOToOrderMapping = new PropertyMap<>() {
        protected void configure() {
            map().setPaymentMethodId(source.getPaymentId());
            map().setCcIssueDate(source.getCcIssueDate());
            map().setCcNumber(source.getCcNumber());
            map().setCreditCard(source.isCC());
            map().setNameOnCard(source.getNameOnCard());
            map().setCcv2(source.getCcv2());
        }
    };

    public static PaymentMethod convertToPaymentMethod(PaymentMethodDTO paymentMethodDTO) {
        return modelMapper.map(paymentMethodDTO, PaymentMethod.class);
    }

    public static PaymentMethodDTO convertToDTO(PaymentMethod paymentMethod) {
        PaymentMethodDTO pmDTO = modelMapper.map(paymentMethod, PaymentMethodDTO.class);
        pmDTO.setLastFourDigits(paymentMethod.getCcNumber().substring((paymentMethod.getCcNumber().length() - 5)));
        pmDTO.setCcNumber("");
        return pmDTO;
    }
}
