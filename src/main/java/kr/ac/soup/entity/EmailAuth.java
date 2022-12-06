package kr.ac.soup.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailAuth {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String code;
}
