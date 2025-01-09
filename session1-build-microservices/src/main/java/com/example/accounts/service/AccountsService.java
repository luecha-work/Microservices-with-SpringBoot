package com.example.accounts.service;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.AccountsDto;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.entity.Accounts;
import com.example.accounts.entity.Customer;
import com.example.accounts.exception.CustomerAlreadyExistsException;
import com.example.accounts.exception.ResourceNotFoundException;
import com.example.accounts.mapper.AccountsMapper;
import com.example.accounts.mapper.CustomerMapper;
import com.example.accounts.repository.AccountsRepository;
import com.example.accounts.repository.CustomerRepository;
import com.example.accounts.sevice_contract.IAccountsService;
import com.example.accounts.utils.EntityUpdater;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AccountsService implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private AccountsMapper accountsMapper;


    @Override
    public void createAccounts(CustomerDto customerDto) {
        Customer customer = customerMapper.mapToCustomer(customerDto);

        customerRepository.findByMobileNumber(customer.getMobileNumber());

        if (customerRepository.findByMobileNumber(customer.getMobileNumber()).isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customer.getMobileNumber() + " already exists");
        }

//        customer.setCreatedBy("System");
//        customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto getAccountDetailByMobilePhone(String phoneNumber) {
        Customer customer = customerRepository
                .findByMobileNumber(phoneNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "mobileNumber", phoneNumber));
        Accounts account = accountsRepository
                .findByCustomerId(customer.getCustomerId()).orElseThrow(
                        () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));


        CustomerDto customerDto = customerMapper.mapToCustomerDto(customer);
        AccountsDto accountsDto = accountsMapper.mapToAccountsDto(account);
        customerDto.setAccountsDto(accountsDto);

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts existingAccount = accountsRepository
                    .findById(accountsDto.getAccountNumber()).orElseThrow(
                            () -> new ResourceNotFoundException("Account", "accountId", accountsDto.getAccountNumber().toString()));

            EntityUpdater.updateNonNullFields(accountsDto, existingAccount);
            existingAccount.setUpdatedAt(LocalDateTime.now());
            existingAccount.setUpdatedBy("System");
            accountsRepository.save(existingAccount);

            Long customerId = existingAccount.getCustomerId();
            Customer existingCustomer = customerRepository
                    .findById(customerId).orElseThrow(
                            () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

            EntityUpdater.updateNonNullFields(customerDto, existingCustomer);
            existingCustomer.setUpdatedAt(LocalDateTime.now());
            existingCustomer.setUpdatedBy("System");
            customerRepository.save(existingCustomer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository
                .findByMobileNumber(mobileNumber).orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        long randomAccountNumber = 100000000L + (long) (Math.random() * 900000000L);

        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
//        newAccount.setCreatedBy("System");
//        newAccount.setCreatedAt(LocalDateTime.now());

        return newAccount;
    }


}
