package seedu.tarence.logic.commands;

import seedu.tarence.logic.commands.exceptions.CommandException;
import seedu.tarence.model.Model;

/**
 * Represent the user confirming a previously stored command.
 * When executed, retrieves and execute the stored command, and returns its {@code CommandResult}.
 */
public class ConfirmYesCommand extends Command {

    public static final String COMMAND_WORD = "y";

    public static final String[] COMMAND_SYNONYMS = {COMMAND_WORD, "yes", "confirm"};

    // returns the result of executing the stored pending command
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return model.getPendingCommand().execute(model);
    }

    /**
     * Returns true if user command matches command word or any defined synonyms, and false otherwise.
     *
     * @param userCommand command word from user.
     * @return whether user command matches specified command word or synonyms.
     */
    public static boolean isMatchingCommandWord(String userCommand) {
        for (String synonym : COMMAND_SYNONYMS) {
            if (synonym.equals(userCommand.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}


