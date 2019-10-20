package seedu.tarence.logic;

import org.apache.commons.lang3.StringUtils;

import seedu.tarence.logic.parser.ApplicationParser;
import seedu.tarence.logic.parser.PartialInput;
import seedu.tarence.logic.parser.exceptions.ParseException;
import seedu.tarence.model.Model;

/**
 * Handles autocompletion of partial inputs from user.
 */
public class AutocompleteHandler {

    private Model model;

    public AutocompleteHandler(Model model) {
        this.model = model;
    }

    /**
     * Main method for handling autocomplete requests from user.
     *
     * @param input Partial input string from user.
     * @return Completed string to be filled into the input command box..
     * @throws ParseException if the users tries to autocomplete an invalid/unsupported field.
     */
    public String handle(String input) throws ParseException {
        if (model.hasInputChanged()) {
            model.setInputChangedToFalse();
            // find and display new suggested autocompletions
            PartialInput partialInput = new ApplicationParser()
                    .parsePartialInput(input, model);
            model.storeSuggestedCompletions(partialInput);
            return StringUtils.removeEnd(partialInput.getOriginalInput(),
                    partialInput.getLastArgument()) + partialInput.getCompletions().get(0);
        } else {
            // display next suggestion in stored list
            PartialInput partialInput = model.getSuggestedCompletions();
            partialInput.getCompletions().add(partialInput.getCompletions().remove(0));
            return StringUtils.removeEnd(partialInput.getOriginalInput(),
                    partialInput.getLastArgument()) + partialInput.getCompletions().get(0);
        }
    }

}
