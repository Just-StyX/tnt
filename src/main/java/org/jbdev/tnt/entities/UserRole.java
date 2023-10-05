package org.jbdev.tnt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user_roles")
public class UserRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userID;

    @Column(name = "role_id")
    private int roleID;

    public UserRole() {}
    public UserRole(int userID, int roleID) {
        this.userID = userID;
        this.roleID = roleID;
    }
}
