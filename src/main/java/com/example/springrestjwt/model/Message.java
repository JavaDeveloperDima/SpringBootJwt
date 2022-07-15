package com.example.springrestjwt.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Setter
@Getter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message")
    private String massage;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Message(String massage, User user) {
        this.massage = massage;
        this.user = user;
    }
}
