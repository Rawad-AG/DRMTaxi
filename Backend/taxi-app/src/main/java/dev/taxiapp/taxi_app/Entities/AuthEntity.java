package dev.taxiapp.taxi_app.Entities;

import java.time.LocalDateTime;
import java.util.List;

import dev.taxiapp.taxi_app.Utils.UserRoles;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(name = "Auth")
@Table(name = "auths")
public class AuthEntity {

    @Id
    @SequenceGenerator(name = "auth_id_sequence", sequenceName = "auth_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_id_sequence")
    private Long id;


    @OneToOne(optional = false)
    @JoinColumn(name = "user_id",referencedColumnName = "id", unique = true, nullable = false)
    private UserEntity user;
    
    @Column(nullable = false)
    private String password;
    private LocalDateTime passwordUpdatedAt;


    @ElementCollection(targetClass = UserRoles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name= "user_id"))    
    @Enumerated(EnumType.STRING)
    private List<UserRoles> roles;


    @Column(nullable = false)
    private Boolean enabled = false;

    @Column(nullable = false)
    private Boolean locked = false;

    @Column(nullable = false)
    private Boolean credintialsExpired = false;


    //Email Verification
    private String emailVerificationToken;
    private LocalDateTime emailVerificationTokenExpiresAt;
    private LocalDateTime emailVerificationTokenInitiatedAt;


    //Phone number Verification
    private String phoneVerificationToken;
    private LocalDateTime phoneVerificationTokenExpiresAt;
    private LocalDateTime phoneVerificationTokenInitiatedAt;

    public AuthEntity(UserEntity user, String password, List<UserRoles> roles) {
        this.user = user;
        this.password = password;
        this.passwordUpdatedAt = LocalDateTime.now();
        this.roles = roles;
    }

    
}
