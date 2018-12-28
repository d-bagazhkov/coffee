package dev.dechesterv.coffeemodels.entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Consumer {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Permission permission;
    private Date date;

}
