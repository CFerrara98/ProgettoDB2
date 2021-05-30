package com.example.progettodb22.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/*

 */
@Document(collection = "biblioteche2")
public class Biblioteca {
    @Id
    private String id;
    private String Email;
    private String Regione;
    private String CodiceSbn;
    private String  Url;
    private String Comune;
    private String Cap;
    private String Indirizzo;
    private String CodiceIstatComune;
    private String Telefono;
    private String CodiceIstatProvincia;
    private Location location;
    private String Fax;
    private String Denominazione;
    private String Provincia;
    private int VolumiDisponibili;

    public Biblioteca(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRegione() {
        return Regione;
    }

    public void setRegione(String regione) {
        Regione = regione;
    }

    public String getCodiceSbn() {
        return CodiceSbn;
    }

    public void setCodiceSbn(String codiceSbn) {
        CodiceSbn = codiceSbn;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getComune() {
        return Comune;
    }

    public void setComune(String comune) {
        Comune = comune;
    }

    public String getCap() {
        return Cap;
    }

    public void setCap(String cap) {
        Cap = cap;
    }

    public String getIndirizzo() {
        return Indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }

    public String getCodiceIstatComune() {
        return CodiceIstatComune;
    }

    public void setCodiceIstatComune(String codiceIstatComune) {
        CodiceIstatComune = codiceIstatComune;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCodiceIstatProvincia() {
        return CodiceIstatProvincia;
    }

    public void setCodiceIstatProvincia(String codiceIstatProvincia) {
        CodiceIstatProvincia = codiceIstatProvincia;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getDenominazione() {
        return Denominazione;
    }

    public void setDenominazione(String denominazione) {
        Denominazione = denominazione;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public int getVolumiDisponibili() {
        return VolumiDisponibili;
    }

    public void setVolumiDisponibili(int volumiDisponibili) {
        VolumiDisponibili = volumiDisponibili;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biblioteca that = (Biblioteca) o;
        return Objects.equals(id, that.id) && Objects.equals(Email, that.Email) && Objects.equals(Regione, that.Regione) && Objects.equals(CodiceSbn, that.CodiceSbn) && Objects.equals(Url, that.Url) && Objects.equals(Comune, that.Comune) && Objects.equals(Cap, that.Cap) && Objects.equals(Indirizzo, that.Indirizzo) && Objects.equals(CodiceIstatComune, that.CodiceIstatComune) && Objects.equals(Telefono, that.Telefono) && Objects.equals(CodiceIstatProvincia, that.CodiceIstatProvincia) && Objects.equals(location, that.location) && Objects.equals(Fax, that.Fax) && Objects.equals(Denominazione, that.Denominazione) && Objects.equals(Provincia, that.Provincia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Email, Regione, CodiceSbn, Url, Comune, Cap, Indirizzo, CodiceIstatComune, Telefono, CodiceIstatProvincia, location, Fax, Denominazione, Provincia);
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "id='" + id + '\'' +
                ", Email='" + Email + '\'' +
                ", Regione='" + Regione + '\'' +
                ", CodiceSbn='" + CodiceSbn + '\'' +
                ", Url='" + Url + '\'' +
                ", Comune='" + Comune + '\'' +
                ", Cap='" + Cap + '\'' +
                ", Indirizzo='" + Indirizzo + '\'' +
                ", CodiceIstatComune='" + CodiceIstatComune + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", CodiceIstatProvincia='" + CodiceIstatProvincia + '\'' +
                ", location=" + location +
                ", Fax='" + Fax + '\'' +
                ", Denominazione='" + Denominazione + '\'' +
                ", Provincia='" + Provincia + '\'' +
                ", Volumi Disponibili=" + VolumiDisponibili  +
                '}';
    }
}
