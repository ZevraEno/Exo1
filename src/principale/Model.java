package principale;

public class Model {
    private String nom;
    private String prenom;
    private String club;
    private String mail;

    public void participant(String nom, String prenom, String mail, String club) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.club = club;
    }

    public String getNom() {
        return nom; }
    public void setNom(String nom) {
        this.nom = nom; }

    public String getPrenom() {
        return prenom; }
    public void setPrenom(String prenom) {
        this.prenom = prenom; }

    public String getMail() {
        return mail; }
    public void setMail(String mail) {
        this.mail = mail; }

    public String getClub() {
        return club; }
    public void setClub(String club) {
        this.club = club; }

    @Override
    public String toString() {
        return "Personne{nom='" + nom + "', prenom='" + prenom + "', email='" + mail + "'}";
    }
}


