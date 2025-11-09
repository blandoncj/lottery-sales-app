package com.blandev.lottery.backend.application.usecase.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.blandev.lottery.backend.application.dto.customer.CreateCustomerDTO;
import com.blandev.lottery.backend.application.dto.customer.CustomerResponseDTO;
import com.blandev.lottery.backend.domain.exception.DuplicateResourceException;
import com.blandev.lottery.backend.domain.model.Customer;
import com.blandev.lottery.backend.domain.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateCustomerUseCase Test")
class CreateCustomerUseCaseTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CreateCustomerUseCase createCustomerUseCase;

    private CreateCustomerDTO validDTO;
    private Customer savedCustomer;

    @BeforeEach
    void setUp() {
        validDTO = new CreateCustomerDTO("123456", "John Doe", "johndoe@email.com");

        savedCustomer = new Customer("123456", "John Doe", "johndoe@email.com");
        savedCustomer.setId(1L);
    }

    @Test
    @DisplayName("Should create a new customer successfully")
    void shouldCreateNewCustomerSuccessfully() {
        when(customerRepository.existsByDocumentNumber(anyString())).thenReturn(false);
        when(customerRepository.existsByEmail(anyString())).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerResponseDTO result = createCustomerUseCase.execute(validDTO);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("123456", result.documentNumber());
        assertEquals("John Doe", result.name());
        assertEquals("johndoe@email.com", result.email());

        verify(customerRepository).existsByDocumentNumber("123456");
        verify(customerRepository).existsByEmail("johndoe@email.com");
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    @DisplayName("Should throw DuplicateResourceException when document number already exists")
    void shouldThrowDuplicateResourceExceptionWhenDocumentNumberExists() {
        when(customerRepository.existsByDocumentNumber(validDTO.documentNumber())).thenReturn(true);

        DuplicateResourceException exception = assertThrows(
                DuplicateResourceException.class,
                () -> createCustomerUseCase.execute(validDTO));

        assertTrue(exception.getMessage().contains("Customer"));
        assertTrue(exception.getMessage().contains("document"));
        assertTrue(exception.getMessage().contains("123456"));

        verify(customerRepository).existsByDocumentNumber("123456");
        verify(customerRepository, never()).existsByEmail(anyString());
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    @DisplayName("Should throw DuplicateResourceException when email already exists")
    void shouldThrowDuplicateResourceExceptionWhenEmailExists() {
        when(customerRepository.existsByDocumentNumber(validDTO.documentNumber())).thenReturn(false);
        when(customerRepository.existsByEmail(validDTO.email())).thenReturn(true);

        DuplicateResourceException exception = assertThrows(
                DuplicateResourceException.class,
                () -> createCustomerUseCase.execute(validDTO));

        assertTrue(exception.getMessage().contains("Customer"));
        assertTrue(exception.getMessage().contains("email"));
        assertTrue(exception.getMessage().contains("johndoe@email.com"));

        verify(customerRepository).existsByDocumentNumber("123456");
        verify(customerRepository).existsByEmail("johndoe@email.com");
        verify(customerRepository, never()).save(any(Customer.class));
    }
}