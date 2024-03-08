package az.ingress.bankapp.service.address;

import az.ingress.bankapp.exception.type.BaseException;
import az.ingress.bankapp.models.dto.address.AddressRequest;
import az.ingress.bankapp.models.dto.address.AddressResponse;
import az.ingress.bankapp.entity.Address;
import az.ingress.bankapp.mapper.address.AddressMapper;
import az.ingress.bankapp.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.ingress.bankapp.models.enums.response.ErrorMessages.ADDRESS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressResponse createAddress(AddressRequest addressRequest) {
        Address address = addressMapper.addressRequestToEntity(addressRequest);
        Address savedAddress = addressRepository.save(address);
        return getAddressResponse(savedAddress);
    }

    @Override
    public AddressResponse getAddressById(Long addressId) {
        Address address = checkAddressExistingGivenId(addressId);
        return getAddressResponse(address);
    }



    @Override
    public AddressResponse updateAddress(AddressRequest addressRequest, Long addressId) {
        Address address = checkAddressExistingGivenId(addressId);
        Address updatedAddress = updateIfNotNull(addressRequest, address);
        updatedAddress.setId(addressId);
        Address savedAddress = addressRepository.save(updatedAddress);
        return getAddressResponse(savedAddress);
    }

    @Override
    public AddressResponse deleteAddress(Long addressId) {
        Address address = checkAddressExistingGivenId(addressId);
        addressRepository.delete(address);
        return getAddressResponse(address);
    }

    private AddressResponse getAddressResponse(Address address) {
        return addressMapper.addressEntityToResponse(address);
    }

    private Address checkAddressExistingGivenId(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow(
                () -> BaseException.of(ADDRESS_NOT_FOUND)
        );
    }

    private Address updateIfNotNull(AddressRequest addressRequest, Address address) {
        if (addressRequest.getStreet() != null) {
            address.setStreet(addressRequest.getStreet());
        }
        if (addressRequest.getCity() != null) {
            address.setCity(addressRequest.getCity());
        }
        if (addressRequest.getPostalCode() != null) {
            address.setPostalCode(addressRequest.getPostalCode());
        }
        return address;
    }
}
