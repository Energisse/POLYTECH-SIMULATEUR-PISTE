package com.epul.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numutil", nullable = false)
    private Integer id;

    @Column(name = "nomutil", nullable = false, length = 100)
    private String nomUtil;

    @Column(name = "motpasse", nullable = false, length = 100)
    private String motPasse;

    @Column(name = "salt", nullable = false, length = 100)
    private String salt;

    @Column(name = "role", nullable = false, length = 100)
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}