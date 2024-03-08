package az.ingress.bankapp.service.address;

import az.ingress.bankapp.models.dto.address.AddressRequest;
import az.ingress.bankapp.models.dto.address.AddressResponse;

public interface AddressService {

    AddressResponse createAddress(AddressRequest addressRequest);

    AddressResponse getAddressById(Long addressId);

    AddressResponse updateAddress(AddressRequest addressRequest, Long addressId);

    AddressResponse deleteAddress(Long addressId);
}
