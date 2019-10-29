package seedu.tarence.logic.parser;

import static seedu.tarence.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.tarence.logic.parser.CliSyntax.PREFIX_TUTORIAL_NAME;
import static seedu.tarence.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tarence.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tarence.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import seedu.tarence.commons.core.index.Index;
import seedu.tarence.logic.commands.assignment.DeleteAssignmentCommand;
import seedu.tarence.logic.parser.assignment.DeleteAssignmentCommandParser;
import seedu.tarence.model.module.ModCode;
import seedu.tarence.model.tutorial.Assignment;
import seedu.tarence.model.tutorial.TutName;
import seedu.tarence.model.tutorial.Tutorial;

public class DeleteAssignmentCommandParserTest {

    public static final String VALID_ASSIGN_NAME = "Assignment 1";
    public static final String VALID_END_DATE = "10-10-11 1000";
    public static final int VALID_INDEX = 1;
    public static final String VALID_MOD_CODE = "ZA9876X";
    public static final int VALID_SCORE = 10;
    public static final String VALID_START_DATE = "10-10-10 1000";
    public static final String VALID_TUT_NAME = "Studio 99";

    public static final int INVALID_INDEX = 0;

    public static final String ASSIGN_NAME_DESC = " " + PREFIX_NAME + VALID_ASSIGN_NAME;
    public static final String END_DATE_DESC = " " + PREFIX_END_DATE + VALID_END_DATE;
    public static final String INDEX_DESC = " " + PREFIX_INDEX + VALID_INDEX;
    public static final String MOD_CODE_DESC = " " + PREFIX_MODULE + VALID_MOD_CODE;
    public static final String SCORE_DESC = " " + PREFIX_SCORE + VALID_SCORE;
    public static final String START_DATE_DESC = " " + PREFIX_START_DATE + VALID_START_DATE;
    public static final String TUT_NAME_DESC = " " + PREFIX_TUTORIAL_NAME + VALID_TUT_NAME;

    public static final String INVALID_INDEX_DESC = " " + PREFIX_INDEX + INVALID_INDEX;
    public static final String INVALID_SCORE_DESC_NEGATIVE = " " + PREFIX_SCORE + "-1";
    public static final String INVALID_SCORE_DESC_NON_NUMBER = " " + PREFIX_SCORE + "abcde";
    public static final String INVALID_START_DATE_DESC = " " + PREFIX_START_DATE + "some random string";

    private DeleteAssignmentCommandParser parser = new DeleteAssignmentCommandParser();

    private SimpleDateFormat dateFormatter = new SimpleDateFormat(Tutorial.DATE_FORMAT);

    @Test
    void parse_validTutorialIndex_returnsAddCommand() throws ParseException {
        assertParseSuccess(parser, INDEX_DESC + ASSIGN_NAME_DESC + SCORE_DESC + START_DATE_DESC + END_DATE_DESC,
                new DeleteAssignmentCommand(null, null, Index.fromOneBased(VALID_INDEX),
                        null, VALID_ASSIGN_NAME, VALID_SCORE,
                        dateFormatter.parse(VALID_START_DATE), dateFormatter.parse(VALID_END_DATE)));
    }

    @Test
    void parse_validTutNameAndModCode_returnsAddCommand() throws ParseException {
        assertParseSuccess(parser,
                TUT_NAME_DESC + MOD_CODE_DESC + ASSIGN_NAME_DESC + SCORE_DESC + START_DATE_DESC + END_DATE_DESC,
                new DeleteAssignmentCommand(new ModCode(VALID_MOD_CODE), new TutName(VALID_TUT_NAME), null,
                        null, VALID_ASSIGN_NAME, VALID_SCORE,
                        dateFormatter.parse(VALID_START_DATE), dateFormatter.parse(VALID_END_DATE)));
    }

    @Test
    void parse_validAssignIndex_returnsAddCommand() {
        assertParseSuccess(parser,
                INDEX_DESC + INDEX_DESC,
                new DeleteAssignmentCommand(null, null, Index.fromOneBased(VALID_INDEX),
                        Index.fromOneBased(VALID_INDEX), null, null, null, null));
    }

    @Test
    void parse_invalidTutIndex_throwsParseException() {
        assertParseFailure(parser, INVALID_INDEX_DESC + INDEX_DESC,
                MESSAGE_INVALID_INDEX);
    }

    @Test
    void parse_invalidAssignIndex_throwsParseException() {
        assertParseFailure(parser, INDEX_DESC + INVALID_INDEX_DESC,
                MESSAGE_INVALID_INDEX);
    }

    @Test
    void parse_invalidDateFormat_throwsParseException() {
        assertParseFailure(parser, TUT_NAME_DESC + MOD_CODE_DESC + ASSIGN_NAME_DESC + SCORE_DESC
                + INVALID_START_DATE_DESC + END_DATE_DESC,
                Assignment.MESSAGE_CONSTRAINTS_START_END_DATE);
    }

    @Test
    void parse_negativeScore_throwsParseException() {
        assertParseFailure(parser, TUT_NAME_DESC + MOD_CODE_DESC + ASSIGN_NAME_DESC + INVALID_SCORE_DESC_NEGATIVE
                        + START_DATE_DESC + END_DATE_DESC,
                Assignment.MESSAGE_CONSTRAINTS_MAX_SCORE);
    }

    @Test
    void parse_nonNumberScore_throwsParseException() {
        assertParseFailure(parser, TUT_NAME_DESC + MOD_CODE_DESC + ASSIGN_NAME_DESC + INVALID_SCORE_DESC_NON_NUMBER
                        + START_DATE_DESC + END_DATE_DESC,
                Assignment.MESSAGE_CONSTRAINTS_MAX_SCORE);
    }

    @Test
    void parse_missingArgs_throwsParseException() {
        assertParseFailure(parser, INDEX_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssignmentCommand.MESSAGE_USAGE));
    }


}
