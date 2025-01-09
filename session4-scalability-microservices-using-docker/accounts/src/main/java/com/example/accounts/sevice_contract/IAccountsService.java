package com.example.accounts.sevice_contract;

import com.example.accounts.dto.AccountsDto;
import com.example.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Creates a new account with the provided account details.
     *
     * @param customerDto the account details to create the new account
     */
    void createAccounts(CustomerDto customerDto);

    /**
     * Retrieves the account details for the provided phone number.
     *
     * @param phoneNumber the phone number of the account to retrieve
     * @return the account details for the provided phone number
     */
    CustomerDto getAccountDetailByMobilePhone(String phoneNumber);

    /**
     * Updates the account details for the provided account.
     *
     * @param customerDto the account details to update
     * @return true if the account details were updated successfully, false otherwise
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * Deletes the account for the provided phone number.
     *
     * @param mobileNumber the phone number of the account to delete
     * @return true if the account was deleted successfully, false otherwise
     */
    boolean deleteAccount(String mobileNumber);
}
