// Entidad que representa un doctor en la base de datos
package edu.co.ustavillavicencio.quiz2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserApp user;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 80)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 120)
    private String specialization;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;
}
