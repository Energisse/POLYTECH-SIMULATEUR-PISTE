package com.epul.demo.service;

import com.epul.demo.domain.Utilisateur;
import com.epul.demo.domain.LogiParam;
import com.epul.demo.mesExceptions.MonException;
import com.epul.demo.repositories.UtilisateurRepository;
import com.epul.demo.utilitaires.MonMotPassHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

    private UtilisateurRepository unUtilisateurRepostory;

    @Autowired
    public AuthentificationService(UtilisateurRepository UtilisateurRepostory) {
        this.unUtilisateurRepostory = UtilisateurRepostory;
    }

    public Utilisateur authentification(LogiParam unUti) throws Exception {
        Utilisateur unUtilisateur = null;
        String message;
        String login = unUti.getNomUtil();
        String pwd = unUti.getMotPasse();
        unUtilisateur = unUtilisateurRepostory.getByNomUtil(login);
            if (unUtilisateur != null) {
                try {
                    // on récupère le sel
                    String sel = unUtilisateur.getSalt();
                    // on récupère le mot de passe
                    String mdp = unUtilisateur.getMotPasse();
                    // on génère le mot de passe avec les données de connexion
                    byte[] salt = MonMotPassHash.transformeEnBytes(unUtilisateur.getSalt());
                    char[] pwd_char = MonMotPassHash.converttoCharArray(pwd);
                    byte[] monpwdCo = MonMotPassHash.generatePasswordHash(pwd_char, salt);
                    // on récupère le mot de passe enregistré
                    byte[] mdp_byte = MonMotPassHash.transformeEnBytes(mdp);
                    if (!MonMotPassHash.verifyPassword(monpwdCo, mdp_byte)) {
                        message = "mot de passe erroné";
                        return null;
                    }
                } catch (MonException e) {
                    throw e;
                } catch (Exception e) {
                    throw e;
                }
            }

        return unUtilisateur;
    }


}
