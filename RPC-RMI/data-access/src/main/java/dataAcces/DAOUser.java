package dataAcces;

import entitys.UserEntity;

public class DAOUser{

    private DAO dao;

    public UserEntity create(){
        System.out.println("DAO User: Creando Usuario en BD");
        return new UserEntity();
    }
}
