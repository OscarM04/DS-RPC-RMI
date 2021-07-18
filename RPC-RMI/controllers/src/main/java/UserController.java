import Command.UserCommands.CreateUserCommand;

public class UserController {

    public void createUser(){
        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.execute();
    }
}
