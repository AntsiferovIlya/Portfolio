package News.News.models;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.scaffold.TypeWriter;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long usersId;

    private String name;
    private String password;

    @Column(name = "email", unique = true)
    private String eMail;

    private int priority;


    public User() {
    }

    public User(String name,  String password, String eMail) {
        this.name = name;
        this.eMail = eMail;
        this.password = password;
    }

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPriority(int priority) {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}