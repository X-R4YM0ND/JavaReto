package com.tcs.ms.entities;

import com.tcs.ms.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;
    @Column(length = 10)
    private String accountNumber;
    private String accountType;
    private double accountInitBalance;
    private boolean accountState;
    private long clientId;


    @OneToMany(mappedBy = "account", cascade =CascadeType.ALL, orphanRemoval = true)
    private List<Movements> movements;


}
