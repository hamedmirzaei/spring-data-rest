package spring.boot.data.rest.springdatarest.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor()
@Setter
@Getter
@ToString
@Entity
@Table
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column
    private String firstName;

    @NonNull
    @Column
    private String lastName;
}
