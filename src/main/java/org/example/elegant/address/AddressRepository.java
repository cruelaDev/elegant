package org.example.elegant.address;

import org.example.elegant.address.entity.Address;
import org.example.elegant.common.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends CommonRepository<Address, UUID> {
}
