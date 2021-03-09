package com.epam.jwd.fitness_center.command.api;

import com.epam.jwd.fitness_center.command.impl.DefaultCommand;
import com.epam.jwd.fitness_center.command.impl.ErrorCommand;
import com.epam.jwd.fitness_center.command.impl.LoginCommand;
import com.epam.jwd.fitness_center.command.impl.LogoutCommand;
import com.epam.jwd.fitness_center.command.impl.SignupCommand;

public enum CommandManager {
    DEFAULT(DefaultCommand.DEFAULT_COMMAND), LOGIN(LoginCommand.LOGIN_COMMAND), SIGNUP(SignupCommand.SIGNUP_COMMAND)
    , ERROR(ErrorCommand.ERROR_COMMAND), LOGOUT(LogoutCommand.LOGOUT_COMMAND);

    private final Command command;

    CommandManager(Command command) {
        this.command = command;
    }

    static Command of(String command_name) {
        for (CommandManager commandManager : values()) {
            if (commandManager.name().equalsIgnoreCase(command_name)) {
                return commandManager.command;
            }
        }
        return DEFAULT.command;
    }

}
