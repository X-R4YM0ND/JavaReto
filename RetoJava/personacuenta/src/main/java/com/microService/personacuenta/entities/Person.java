package com.microService.personacuenta.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "Perid")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "Person")
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long personId;
    @Column(length = 50)
    private String personName;
    private String personGender;
    private int personAge;
    @Column(length = 10, unique = true)
    private String personCI;
    private String personAddress;
    @Column(length = 10)
    private String personPhone;

}
