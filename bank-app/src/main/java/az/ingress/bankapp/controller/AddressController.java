package az.ingress.bankapp.controller;

import az.ingress.bankapp.models.dto.address.AddressRequest;
import az.ingress.bankapp.models.dto.address.AddressResponse;
import az.ingress.bankapp.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse getAddressById(@PathVariable("addressId") Long addressId) {
        return addressService.getAddressById(addressId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse createAddress(@RequestBody AddressRequest addressRequest) {
        return addressService.createAddress(addressRequest);
    }

    @PutMapping("/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse updateAddress(@RequestBody AddressRequest addressRequest, @PathVariable("addressId") Long addressId) {
        return addressService.updateAddress(addressRequest, addressId);
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse deleteAddress(@PathVariable("addressId") Long addressId) {
        return addressService.deleteAddress(addressId);
    }
}
