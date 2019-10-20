package seedu.tarence.logic.parser;

import static seedu.tarence.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_NUSID;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_MATNO;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_TUTORIAL_NAME;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_TUTORIAL_DAY;

import java.util.List;

import seedu.tarence.logic.finder.Finder;
import seedu.tarence.logic.parser.exceptions.ParseException;
import seedu.tarence.model.Model;

public class PartialInputParser {

    public static PartialInput parse(String partialInput, Model model) throws ParseException {
        Finder finder = new Finder(model);

        ArgumentSingleValue lastPrefixValue = ArgumentTokenizer.tokenizeLastArgument(partialInput, PREFIX_EMAIL,
                PREFIX_NAME, PREFIX_NUSID, PREFIX_MATNO, PREFIX_MODULE, PREFIX_TUTORIAL_NAME, PREFIX_TUTORIAL_DAY);

        Prefix prefix = lastPrefixValue.getPrefix();
        String value = lastPrefixValue.getValue();
        List<String> completions;

        if (prefix.equals(PREFIX_EMAIL)) {
            completions = finder.autocompleteEmail(value);
        } else if (prefix.equals(PREFIX_NAME)) {
            completions = finder.autocompleteName(value);
        } else if (prefix.equals(PREFIX_NUSID)) {
            completions = finder.autocompleteNusId(value);
        } else if (prefix.equals(PREFIX_MATNO)) {
            completions = finder.autocompleteMatNo(value);
        } else if (prefix.equals(PREFIX_MODULE)) {
            completions = finder.autocompleteModCode(value);
        } else if (prefix.equals(PREFIX_TUTORIAL_NAME)) {
            completions = finder.autocompleteTutName(value);
        } else if (prefix.equals(PREFIX_TUTORIAL_DAY)) {
            completions = finder.autocompleteDay(value);
        } else {
            return null;
        }

        return new PartialInput(partialInput, value, completions);

//        if (prefix.equals(PREFIX_EMAIL)) {
//            return finder.autocompleteEmail(value);
//        } else if (prefix.equals(PREFIX_NAME)) {
//            return finder.autocompleteName(value);
//        } else if (prefix.equals(PREFIX_NUSID)) {
//            return finder.autocompleteNusId(value);
//        } else if (prefix.equals(PREFIX_MATNO)) {
//            return finder.autocompleteMatNo(value);
//        } else if (prefix.equals(PREFIX_TUTORIAL_NAME)) {
//            return finder.autocompleteTutName(value);
//        } else if (prefix.equals(PREFIX_TUTORIAL_DAY)) {
//            return finder.autocompleteDay(value);
//        } else {
//            return "";
//        }
    }

}
