package Command.UserCommands;

import Command.ICommand;
import services.UserService;

public class CreateUserCommand implements ICommand {


    @Override
    public void execute() {
        System.out.println("USER COMMAND: Creando Usuario");
        UserService userService = new UserService();
        userService.createUser();
    }
}
