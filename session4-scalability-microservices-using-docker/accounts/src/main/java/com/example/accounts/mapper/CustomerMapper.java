package com.example.accounts.mapper;

import com.example.accounts.dto.CustomerDto;
import com.example.accounts.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer mapToCustomer(CustomerDto customerDto);
    CustomerDto mapToCustomerDto(Customer customer);
}
