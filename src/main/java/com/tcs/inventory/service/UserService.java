package com.tcs.inventory.service;

import com.tcs.inventory.dto.SignupRequest;
//import com.tcs.inventory.dto.SignupRequest;
import com.tcs.inventory.entity.Customer;
import com.tcs.inventory.entity.User;
import com.tcs.inventory.enums.Role;
import com.tcs.inventory.repository.CustomerRepository;
import com.tcs.inventory.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerCustomer(SignupRequest request) {
    	  if(userRepository.existsByUsername(request.getUsername())) {
    	        throw new RuntimeException("Username is already taken");
    	    }
    	    // Check if customer with email or contact exists
    	    if(customerRepository.existsByEmail(request.getEmail())) {
    	        throw new RuntimeException("Customer with this email already registered");
    	    }
    	    if(customerRepository.existsByContact(request.getContact())) {
    	        throw new RuntimeException("Customer with this contact already registered");
    	    }
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setContact(request.getContact());
        customer.setAddress(request.getAddress());
        customer.setCustomerCode("CUST-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        Customer savedCustomer = customerRepository.save(customer);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CUSTOMER);
        user.setCustomer(savedCustomer);

        userRepository.save(user);
    }
}
