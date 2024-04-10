package com.epul.demo.repositories;

import com.epul.demo.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>  {

    public Utilisateur getByNomUtil(String login);

}

