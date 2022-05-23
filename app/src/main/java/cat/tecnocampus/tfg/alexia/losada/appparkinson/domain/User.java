package cat.tecnocampus.tfg.alexia.losada.appparkinson.domain;

public class User {

    private String email;
    private String name;
    private String surname;
    private String doctor;

    public User(){

    }

    public User(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.doctor = "logsparkinson@gmail.com";

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDoctor(){
        return doctor;
    }

    public void setDoctor(String doctor){
        this.doctor = doctor;
    }

}
