package com.epam.jwd.fitness_center.command.api;

import com.epam.jwd.fitness_center.command.impl.purpose.CreatePurposeCommand;
import com.epam.jwd.fitness_center.command.impl.purpose.DeletePurposeCommand;
import com.epam.jwd.fitness_center.command.impl.training.CommentCommand;
import com.epam.jwd.fitness_center.command.impl.training.CreateTrainingCommand;
import com.epam.jwd.fitness_center.command.impl.user.DefaultCommand;
import com.epam.jwd.fitness_center.command.impl.profile.DeleteProfileCommand;
import com.epam.jwd.fitness_center.command.impl.training.DeleteTrainingCommand;
import com.epam.jwd.fitness_center.command.impl.ErrorCommand;
import com.epam.jwd.fitness_center.command.impl.training.InspectTrainingCommand;
import com.epam.jwd.fitness_center.command.impl.user.LoginCommand;
import com.epam.jwd.fitness_center.command.impl.user.LogoutCommand;
import com.epam.jwd.fitness_center.command.impl.training.ShowAllTrainingsCommand;
import com.epam.jwd.fitness_center.command.impl.user.ShowInstructorsCommand;
import com.epam.jwd.fitness_center.command.impl.training.ShowTrainingsCommand;
import com.epam.jwd.fitness_center.command.impl.user.SignupCommand;
import com.epam.jwd.fitness_center.command.impl.profile.ShowProfileCommand;
import com.epam.jwd.fitness_center.command.impl.profile.UpdateProfileCommand;

public enum CommandManager {
    DEFAULT(DefaultCommand.DEFAULT_COMMAND), LOGIN(LoginCommand.LOGIN_COMMAND), SIGNUP(SignupCommand.SIGNUP_COMMAND)
    , ERROR(ErrorCommand.ERROR_COMMAND), LOGOUT(LogoutCommand.LOGOUT_COMMAND)
    , SHOW_INSTRUCTORS(ShowInstructorsCommand.INSTANCE), SHOW_PROFILE(ShowProfileCommand.INSTANCE)
    , SHOW_TRAININGS(ShowTrainingsCommand.INSTANCE), CREATE_TRAINING(CreateTrainingCommand.INSTANCE)
    , UPDATE_PROFILE(UpdateProfileCommand.INSTANCE), INSPECT_TRAINING(InspectTrainingCommand.INSTANCE)
    , SHOW_ALL_TRAININGS(ShowAllTrainingsCommand.INSTANCE), DELETE_PROFILE(DeleteProfileCommand.INSTANCE)
    , DELETE_TRAINING(DeleteTrainingCommand.INSTANCE), COMMENT(CommentCommand.INSTANCE)
    , DELETE_PURPOSE(DeletePurposeCommand.INSTANCE), CREATE_PURPOSE(CreatePurposeCommand.INSTANCE);

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
