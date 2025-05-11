package com.example.ignite.server.mapper;

import com.example.common.dto.CustomerDTO;
import com.example.ignite.server.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDto(Customer entity);
    Customer toEntity(CustomerDTO dto);
}
