package com.epam.jwd.fitness_center.command.api;

import com.epam.jwd.fitness_center.command.impl.training.CommentCommand;
import com.epam.jwd.fitness_center.command.impl.user.DefaultCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandManagerTest {

    @Test
    public void CommandOfTest_MustReturnAppropriateCommandByName_Command() {
        assertEquals(CommentCommand.class, CommandManager.of("comment").getClass());
    }

    @Test
    public void CommandOfTest_MustReturnDefaultCommandByUnrecognizedName_DefaultCommand() {
        assertEquals(DefaultCommand.class, CommandManager.of("unrec").getClass());
    }

}
