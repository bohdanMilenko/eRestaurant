package com.application.util.dtoEntityConverter;

import com.application.entity.Address;
import com.application.entity.Province;
import com.application.entity.User;
import com.application.entity.dto.AddressDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    private static ModelMapper modelMapper;

    @Autowired
    public AddressConverter(ModelMapper modelMapper) {
        AddressConverter.modelMapper = modelMapper;
        modelMapper.addMappings(addressToDTOMap);
        modelMapper.addMappings(DTOToAddressMap);
    }

    PropertyMap<Address, AddressDTO> addressToDTOMap = new PropertyMap<Address, AddressDTO>() {
        @Override
        protected void configure() {
            map().setAddressId(source.getAddressId());
            map().setAddressLine1(source.getAddressLine1());
            map().setAddressLine2(source.getAddressLine2());
            map().setCity(source.getCity());
            map().setPostalCode(source.getPostalCode());
            map().setProvince(source.getProvince().getFullNameProvince());
            map().setProvinceId(source.getProvince().getProvinceId());
            map().setCountry(source.getCountry());
            map().setDeliveryNotes(source.getDeliveryNotes());
            map().setUserId(source.getUser().getUserId());
        }

    };

    PropertyMap<AddressDTO, Address> DTOToAddressMap = new PropertyMap<AddressDTO, Address>() {
        @Override
        protected void configure() {
            map().setAddressId(source.getAddressId());
            map().setAddressLine1(source.getAddressLine1());
            map().setAddressLine2(source.getAddressLine2());
            map().setCity(source.getCity());
            map().setPostalCode(source.getPostalCode());
            map().setCountry(source.getCountry());
            map().setDeliveryNotes(source.getDeliveryNotes());
        }

    };

    public static Address convertDTOToAddress(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }

    public static AddressDTO convertAddressToDTO(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }
}
