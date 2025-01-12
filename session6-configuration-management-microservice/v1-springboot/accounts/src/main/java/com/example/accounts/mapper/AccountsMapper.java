package com.example.accounts.mapper;

import com.example.accounts.dto.AccountsDto;
import com.example.accounts.entity.Accounts;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountsMapper {
    AccountsDto mapToAccountsDto(Accounts accounts);

    Accounts mapToAccounts(AccountsDto accountsDto);
}
