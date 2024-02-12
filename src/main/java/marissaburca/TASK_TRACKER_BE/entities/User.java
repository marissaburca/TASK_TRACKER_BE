package marissaburca.TASK_TRACKER_BE.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")

@JsonIgnoreProperties({"password", "authorities", "accountNonExpired", "enabled", "accountNonLocked", "credentialsNonExpired"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "username")
    private String username;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToOne
    @JoinColumn(name = "avatar")
    private Avatar avatar;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "personal_tasks")
    @JsonManagedReference
    private List<Task> tasks= new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @Override
    public boolean isEnabled () {
        return true;
    }

    @Override
    public String getUsername () {
        return this.username;
    }


    //NEEDED METHODS
    // METHOD TO ADD A TASK
    public void addTask(Task task) {
        tasks.add(task);
        task.setUser(this);
    }

    // METHOD TO REMOVE A TASK
    public void removeTask(Task task) {
        tasks.remove(task);
        task.setUser(null);
    }

    //TO_STRING
    @Override
    public String toString () {
        return "User with id:" + id + " | name: " + name + " | surname: " + surname + " | username: " + username + " | avatar: " + avatar + " | role=" + role + " | tasks: " + tasks;
    }

}
