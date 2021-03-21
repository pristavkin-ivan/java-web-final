package com.epam.jwd.fitness_center.command.api;

import com.epam.jwd.fitness_center.command.impl.CreateTrainingCommand;
import com.epam.jwd.fitness_center.command.impl.DefaultCommand;
import com.epam.jwd.fitness_center.command.impl.DeleteProfileCommand;
import com.epam.jwd.fitness_center.command.impl.DeleteTrainingCommand;
import com.epam.jwd.fitness_center.command.impl.ErrorCommand;
import com.epam.jwd.fitness_center.command.impl.InspectTraining;
import com.epam.jwd.fitness_center.command.impl.LoginCommand;
import com.epam.jwd.fitness_center.command.impl.LogoutCommand;
import com.epam.jwd.fitness_center.command.impl.ShowAllTrainingsCommand;
import com.epam.jwd.fitness_center.command.impl.ShowInstructorsCommand;
import com.epam.jwd.fitness_center.command.impl.ShowTrainingsCommand;
import com.epam.jwd.fitness_center.command.impl.SignupCommand;
import com.epam.jwd.fitness_center.command.impl.ShowProfileCommand;
import com.epam.jwd.fitness_center.command.impl.UpdateProfileCommand;

public enum CommandManager {
    DEFAULT(DefaultCommand.DEFAULT_COMMAND), LOGIN(LoginCommand.LOGIN_COMMAND), SIGNUP(SignupCommand.SIGNUP_COMMAND)
    , ERROR(ErrorCommand.ERROR_COMMAND), LOGOUT(LogoutCommand.LOGOUT_COMMAND)
    , SHOW_INSTRUCTORS(ShowInstructorsCommand.INSTANCE), SHOW_PROFILE(ShowProfileCommand.INSTANCE)
    , SHOW_TRAININGS(ShowTrainingsCommand.INSTANCE), CREATE_TRAINING(CreateTrainingCommand.INSTANCE)
    , UPDATE_PROFILE(UpdateProfileCommand.INSTANCE), INSPECT_TRAINING(InspectTraining.INSTANCE)
    , SHOW_ALL_TRAININGS(ShowAllTrainingsCommand.INSTANCE), DELETE_PROFILE(DeleteProfileCommand.INSTANCE)
    , DELETE_TRAINING(DeleteTrainingCommand.INSTANCE);

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
