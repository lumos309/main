package seedu.tarence.logic;

import org.apache.commons.lang3.StringUtils;

import seedu.tarence.logic.parser.ApplicationParser;
import seedu.tarence.logic.parser.PartialInput;
import seedu.tarence.logic.parser.exceptions.ParseException;
import seedu.tarence.model.Model;

public class AutocompleteHandler {

    Model model;

    public AutocompleteHandler(Model model) {
        this.model = model;
    }

    public AutocompleteData handle(String input) throws ParseException {
        if (model.hasInputChanged()) {
            model.setInputChangedToFalse();
            // find and display new suggested autocompletions
            PartialInput partialInput = new ApplicationParser()
                    .parsePartialInput(input, model);
            model.storeSuggestedCompletions(partialInput);
            String autofilledInput = StringUtils.removeEnd(partialInput.originalInput,
                partialInput.lastArgument) + partialInput.completions.get(0);
            return new AutocompleteData(autofilledInput, partialInput.originalInput.length());
        } else {
            PartialInput partialInput = model.getSuggestedCompletions();
            partialInput.completions.add(partialInput.completions.remove(0));
            String autofilledInput = StringUtils.removeEnd(partialInput.originalInput,
                    partialInput.lastArgument) + partialInput.completions.get(0);
            return new AutocompleteData(autofilledInput, partialInput.originalInput.length());
            // display next suggestion in stored list
        }
    }

    public class AutocompleteData {
        public String autocompleteText;
        public int cursorPosition;

        private AutocompleteData(String autocompleteText, int cursorPosition) {
            this.autocompleteText = autocompleteText;
            this.cursorPosition = cursorPosition;
        }
    }
}
