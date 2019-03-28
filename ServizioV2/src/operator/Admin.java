package operator;
/**
 * Classe admin
 * @author Simona Ramazzotti, Reda Kassame
 * @version 2
 */
public class Admin {
    private String password, username;
    private boolean isAdmin;

    public Admin(String username, String password){
        this.password=password;
        this.username=username;
        this.isAdmin=true;
    }

    /**
     * Cose da fare
     * metodi per isAdmin, se vero allora visualizza nel menu i tre metodi:
     * -creare nuovi libri complilando tutti i campi
     * -rimuovi un libro
     * -visulizzare per sottocategoria (for-each)
     *
     * Quindi ricordiamoci di modificare anche il MENU! solo per l'admin!
     * Ricordiamo di mettere qui dentro resource i 3 metodi!
     */

    /**
     * GET & SET della classe
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
