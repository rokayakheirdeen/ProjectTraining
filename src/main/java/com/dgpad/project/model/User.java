package com.dgpad.project.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(nullable = false)
 private String firstName;

 @Column(nullable = false)
 private String lastName;

 @Column(nullable = false, unique = true)
 private String username;

 @Column(nullable = false, unique = true)
 private String email;

 @Column(nullable = false)
 private String password;

 @Enumerated(EnumType.STRING)
 @Column(nullable = false)
 private Role role;

 public enum Role {
  USER,
  ADMIN
 }
}