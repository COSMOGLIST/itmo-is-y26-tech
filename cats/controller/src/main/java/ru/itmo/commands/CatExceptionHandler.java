package ru.itmo.commands;

import picocli.CommandLine;

public class CatExceptionHandler implements CommandLine.IExecutionExceptionHandler {
    @Override
    public int handleExecutionException(Exception e, CommandLine commandLine, CommandLine.ParseResult parseResult) throws Exception {
        commandLine.getErr().println(commandLine.getColorScheme().errorText(e.getMessage()));

        return commandLine.getExitCodeExceptionMapper() != null
                ? commandLine.getExitCodeExceptionMapper().getExitCode(e)
                : commandLine.getCommandSpec().exitCodeOnExecutionException();
    }
}
