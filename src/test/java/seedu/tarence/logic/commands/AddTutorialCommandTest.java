package seedu.tarence.logic.commands;

import static seedu.tarence.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.tarence.logic.commands.exceptions.CommandException;
import seedu.tarence.model.module.ModCode;
import seedu.tarence.model.module.Module;
import seedu.tarence.model.tutorial.Tutorial;
import seedu.tarence.testutil.TutorialBuilder;

public class AddTutorialCommandTest {

    @Test
    public void execute_noSuchModule_throwsCommandException() throws Exception {
        final String validModCode = "ES1601";
        final String validTutName = "T02";
        Tutorial tutorial = new TutorialBuilder().withModCode(validModCode).withTutName(validTutName).build();
        AddTutorialCommandTest.ModelStubWithNoModule modelStub = new AddTutorialCommandTest.ModelStubWithNoModule();
        AddTutorialCommand addTutorialCommand = new AddTutorialCommand(tutorial);

        assertThrows(CommandException.class,
                AddTutorialCommand.MESSAGE_INVALID_MODULE, () -> addTutorialCommand.execute(modelStub));
    }

    private class ModelStubWithNoModule extends ModelStub {
        final ArrayList<Module> modules = new ArrayList<>();
        final ArrayList<Tutorial> tutorials = new ArrayList<>();

        @Override
        public boolean hasModuleOfCode(ModCode modCode) {
            for (Module module : modules) {
                if (module.getModCode().equals(modCode)) {
                    return true;
                }
            }
            return false;
        }

    }
}
