package com.eureka.auth.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.Length;
import org.checkerframework.common.value.qual.StringVal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(length = 50)
    private String username;
    private String password;
    private String role;
}
