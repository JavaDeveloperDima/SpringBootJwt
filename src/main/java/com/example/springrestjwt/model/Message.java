package com.example.springrestjwt.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message")
    private String message;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
