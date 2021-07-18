package services;

import dataAcces.DAOUser;
import entitys.UserEntity;

public class UserService {

    public UserEntity createUser(){
        System.out.println("USER SERVICE: Creando Usuario");
        DAOUser daoUser = new DAOUser();
        return daoUser.create();
    }
}
