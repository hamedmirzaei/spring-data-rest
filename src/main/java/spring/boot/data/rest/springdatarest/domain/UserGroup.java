package spring.boot.data.rest.springdatarest.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table
public class UserGroup implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column
    private String name;

    @NonNull
    @Column(unique = true)
    private Long code;
}
