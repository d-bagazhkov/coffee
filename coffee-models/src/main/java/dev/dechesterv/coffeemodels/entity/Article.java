package dev.dechesterv.coffeemodels.entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {

    private Long id;
    private String title;
    private String content;
    private Date date;
    private Consumer author;

}
