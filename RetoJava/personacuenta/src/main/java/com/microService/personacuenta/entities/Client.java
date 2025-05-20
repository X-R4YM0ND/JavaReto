package com.microService.personacuenta.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "Client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "CliId")

public class Client implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;
    private String clientPassword;
    private boolean clientState;

    @Getter
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Person getPerson() {
        return person;
    }



}
