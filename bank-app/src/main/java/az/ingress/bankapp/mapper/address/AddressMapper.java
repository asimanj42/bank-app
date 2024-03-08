package az.ingress.bankapp.mapper.address;

import az.ingress.bankapp.models.dto.address.AddressRequest;
import az.ingress.bankapp.models.dto.address.AddressResponse;
import az.ingress.bankapp.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressResponse addressEntityToResponse(Address address) {
        return AddressResponse.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }

    public Address addressRequestToEntity(AddressRequest addressRequest) {
        return Address.builder()
                .street(addressRequest.getStreet())
                .city(addressRequest.getCity())
                .postalCode(addressRequest.getPostalCode())
                .build();
    }

//    public Address toAddress(AddressRequest addressRequest, Address address) {
//        address.setStreet(addressRequest.getStreet());
//        address.setCity(addressRequest.getCity());
//        address.setPostalCode(addressRequest.getPostalCode());
//        return address;
//    }
//
//    public AddressResponse toAddressResponse(Address address, AddressResponse addressResponse) {
//        addressResponse.setStreet(address.getStreet());
//        addressResponse.setCity(address.getCity());
//        addressResponse.setPostalCode(address.getPostalCode());
//        return addressResponse;
//    }


}
