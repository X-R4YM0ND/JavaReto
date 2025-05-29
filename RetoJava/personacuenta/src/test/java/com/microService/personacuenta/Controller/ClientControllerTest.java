package com.microService.personacuenta.Controller;

import com.microService.personacuenta.controller.ClientController;
import com.microService.personacuenta.dto.AccountDTO;
import com.microService.personacuenta.dto.ClientDTO;
import com.microService.personacuenta.dto.ClientDetailDTO;
import com.microService.personacuenta.entities.Client;
import com.microService.personacuenta.entities.Person;
import com.microService.personacuenta.services.IClientServices;
import com.microService.personacuenta.services.IPersonServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private IClientServices clientservice;

    @Mock
    private IPersonServices personservice;

    @Mock
    private RestTemplate restTemplate;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Person person = new Person();
        person.setPersonName("Ricardo");
        person.setPersonAge(23);
        person.setPersonCI("1740203043");
        person.setPersonPhone("0999999999");

        client = new Client();
        client.setClientId(1L);
        client.setClientPassword("pass123");
        client.setClientState(true);
        client.setPerson(person);
    }

    @Test
    void testGetClientDetail() {
        when(clientservice.obtainbyId(1L)).thenReturn(client);

        ResponseEntity<ClientDetailDTO> response = clientController.getClientDetail(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Ricardo", response.getBody().getPersonName());
        verify(clientservice).obtainbyId(1L);
    }

    @Test
    void testGetClientAccounts() {
        when(clientservice.obtainbyId(1L)).thenReturn(client);

        List<AccountDTO> accounts = Collections.singletonList(new AccountDTO());
        ResponseEntity<List<AccountDTO>> mockResponse = ResponseEntity.ok(accounts);

        when(restTemplate.exchange(
                eq("http://localhost:8090/cuentas/cliente/1"),
                eq(HttpMethod.GET),
                isNull(),
                ArgumentMatchers.<ParameterizedTypeReference<List<AccountDTO>>>any()
        )).thenReturn(mockResponse);

        ResponseEntity<List<AccountDTO>> response = clientController.getClientAccounts(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(restTemplate).exchange(
                eq("http://localhost:8090/cuentas/cliente/1"),
                eq(HttpMethod.GET),
                isNull(),
                ArgumentMatchers.<ParameterizedTypeReference<List<AccountDTO>>>any()
        );
    }

    @Test
    void testAddClient() {
        doNothing().when(clientservice).addClient(client);
        when(restTemplate.postForEntity(anyString(), any(ClientDTO.class), eq(Void.class)))
                .thenReturn(ResponseEntity.ok().build());

        assertDoesNotThrow(() -> clientController.addClient(client));
        verify(clientservice).addClient(client);
        verify(restTemplate).postForEntity(anyString(), any(ClientDTO.class), eq(Void.class));
    }

    @Test
    void testListClient() {
        when(clientservice.listClient()).thenReturn(List.of(client));

        List<Client> result = clientController.listClient();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateClient() {
        doNothing().when(clientservice).updateClient(any(Client.class));
        doNothing().when(restTemplate).put(anyString(), any(ClientDTO.class));

        assertDoesNotThrow(() -> clientController.updateClient(1L, client));
        verify(clientservice).updateClient(any(Client.class));
        verify(restTemplate).put(anyString(), any(ClientDTO.class));
    }

    @Test
    void testDeleteClient() {
        doNothing().when(clientservice).deleteClient(1L);
        doNothing().when(restTemplate).delete(anyString());

        assertDoesNotThrow(() -> clientController.deleteClient(1L));
        verify(clientservice).deleteClient(1L);
        verify(restTemplate).delete(contains("/eliminar-cliente/1"));
    }
}
