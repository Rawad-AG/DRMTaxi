package dev.taxiapp.taxi_app.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import dev.taxiapp.taxi_app.Utils.UserRoles;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(name = "User")
@Table(name = "users")
public class UserEntity {

    @Id
    @SequenceGenerator(name = "user_id_sequence" , sequenceName = "user_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Long id;

    private String email;
    private String phoneNumber;

    @Column(nullable = false)
    private String firstName;
    
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private Boolean deleted = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;



    @OneToOne(
        targetEntity = AuthEntity.class, 
        fetch = FetchType.EAGER, 
        cascade = CascadeType.ALL, 
        optional = false,
        orphanRemoval = true, 
        mappedBy = "user"
    )
    private AuthEntity auth;


    public UserEntity(String email, String phoneNumber,String password, String firstName, String lastName, LocalDate birthDate, List<UserRoles> roles) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.auth = new AuthEntity(this, password, roles);
    }


    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber + ", firstName=" + firstName
                + ", lastName=" + lastName + ", birthDate=" + birthDate + ", deleted=" + deleted + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt + "]";
    }


    

    


    

}
