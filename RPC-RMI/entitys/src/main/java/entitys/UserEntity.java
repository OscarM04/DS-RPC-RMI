package entitys;

import java.io.Serializable;

public class UserEntity implements Serializable {
    private String username;
    private String password;
    private String name;

    public UserEntity(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
