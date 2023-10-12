package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.GuestDeleteCommand;
import seedu.address.logic.commands.GuestListCommand;
import seedu.address.logic.commands.GuestViewCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class GuestCommandParserTest {
    private static final String GUEST_ADD_PARSE_EXCEPTION_MSG =
            "Guest not created in GuestAddCommand due to un-evolved classes";
    private GuestCommandParser parser = new GuestCommandParser();

    // test guest add
    @Test
    public void parseCommand_guestAdd() throws Exception {
        String input = "add n/guest p/102391";
        // expected type, expected string, executable
        assertThrows(ParseException.class, GUEST_ADD_PARSE_EXCEPTION_MSG, () -> parser.parseCommand(input));

        // test for when Guest/Vendor/Person is evolved
        // Command command = parser.parseCommand(input);
        // assertTrue(command instanceof GuestAddCommand);
    }

    // test guest delete
    @Test
    public void parseCommand_guestDelete() throws Exception {
        String input = "delete 1";
        Command command = parser.parseCommand(input);
        assertTrue(command instanceof GuestDeleteCommand);
    }

    // test guest list
    @Test
    public void parseCommand_guestList() throws Exception {
        assertTrue(parser.parseCommand("list") instanceof GuestListCommand);
    }

    // test guest view
    @Test
    public void parseCommand_view() throws Exception {
        assertTrue(parser.parseCommand("view 3") instanceof GuestViewCommand);
    }

    // test for unrecognised input -> invalid command format
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    // test for unknown command
    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
