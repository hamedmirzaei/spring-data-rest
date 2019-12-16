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

    @NonNull
    @ManyToOne
    private UserGroup userGroup;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        if (user.id != this.id) return false;
        if (user.firstName != this.firstName) return false;
        return true;
    }
}
