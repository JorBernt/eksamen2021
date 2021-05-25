package com.example.eksamen2021;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;

@RestController
public class Controller {

    @Autowired
    private JdbcTemplate db;

    Logger logger = LoggerFactory.getLogger(Controller.class);


    @PostMapping("/registrerPakke")
    public void registrerPakke(Pakke pakke, HttpServletResponse response) throws IOException{
        if(!validerInput(pakke)) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i validering av input");
            return;
        }
        String sql = "INSERT INTO Kunde (Fornavn, Etternavn, Adresse, Postnr, Telefonnr, Epost) VALUES(?,?,?,?,?,?)";
        KeyHolder id = new GeneratedKeyHolder();
        try {
            db.update(con -> {
                PreparedStatement par = con.prepareStatement(sql, new String[]{"KId"});
                par.setString(1, pakke.getFornavn());
                par.setString(2, pakke.getEtternavn());
                par.setString(3, pakke.getAdresse());
                par.setString(4, pakke.getPostnr());
                par.setString(5, pakke.getTelefonnr());
                par.setString(6, pakke.getEpost());
                return par;
            }, id);

            int kid = id.getKey().intValue();

            String sql2 = "INSERT INTO Pakke (KId, Volum, Vekt) VALUES(?,?,?)";
            db.update(sql2, kid, pakke.getVolum(), pakke.getVekt());

        }
        catch (Exception e) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
            logger.error("Feil i lagring av data i db");
        }
    }

        @GetMapping("/sjekkPostnr")
        public boolean sjekkPostnr(String postnr, HttpServletResponse response) throws IOException {
            String sql = "SELECT Poststed FROM Poststed WHERE Postnr = ?";
            try {
                String resultat = db.queryForObject(sql, String.class, postnr);
                return !resultat.isEmpty();
            }
            catch (Exception e) {
                logger.error("Feil i DB");
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
                return false;
            }
        }

    public boolean validerInput(Pakke pakke) {
        String navnRegex = "[a-zæøåA-ZÆØÅ' .\\-]{2,50}";
        String postnrRegex = "[\\d]{4}";

        boolean fornavnOK = pakke.getFornavn().matches(navnRegex);
        boolean etternavnOK = pakke.getEtternavn().matches(navnRegex);
        boolean postnrOK = pakke.getPostnr().matches(postnrRegex);

        if(!fornavnOK) {
            logger.error("Fornavn feilet validering");
        }
        if(!etternavnOK) {
            logger.error("Etternavn feilet validering");
        }
        if(!postnrOK) {
            logger.error("Postnr feilet validering");
        }

        return fornavnOK && etternavnOK && postnrOK;
    }


}
