package com.rest.employee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@ToString(exclude = "id")
@Setter
public class Message {

    @Id
    private Long id;

    private String text;
}