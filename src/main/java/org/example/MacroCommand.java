package org.example;

import lombok.SneakyThrows;

import java.util.ArrayList;

public class MacroCommand implements ICommand {
    private final ArrayList<ICommand> commands;

    public MacroCommand(ArrayList<ICommand> commands) {
        this.commands = commands;
    }

    @SneakyThrows
    @Override
    public void execute() {
        try {
            for (ICommand command : commands) {
                command.execute();
            }
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}
