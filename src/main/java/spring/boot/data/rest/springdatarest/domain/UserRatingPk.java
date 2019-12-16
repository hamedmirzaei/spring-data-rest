package spring.boot.data.rest.springdatarest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class UserRatingPk implements Serializable {
    @ManyToOne
    private User user;

    @Column(insertable = false, updatable = false, nullable = false)
    private Integer customerId;

    protected UserRatingPk() {
    }

    public UserRatingPk(User user, Integer customerId) {
        this.user = user;
        this.customerId = customerId;
    }

    public User getUser() {
        return user;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserRatingPk userRatingPk = (UserRatingPk) obj;
        if (userRatingPk.customerId != customerId) return false;
        if (!userRatingPk.user.equals(this.user)) return false;
        return true;
    }
}
