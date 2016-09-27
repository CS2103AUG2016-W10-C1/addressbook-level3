package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;


/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Displays all persons in the address book as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;

    public enum SortField {
        NONE (""),
        ADDRESS("address"),
        EMAIL("email"),
        NAME("name"),
        PHONE("phone");

        public final String name;

        SortField(String name) {
            this.name = name;
        }
    }

    private final SortField sortField;

    public ListCommand(SortField field) {
        sortField = field;
    }

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> unsortedPersons = addressBook.getAllPersons().immutableListView();

        // break early if no need to sort
        if (sortField == SortField.NONE) {
            return new CommandResult(getMessageForPersonListShownSummary(unsortedPersons), unsortedPersons);
        }

        List<String> values = new ArrayList<>();
        HashMap<String, ReadOnlyPerson> personMap = new HashMap<>();
        List<ReadOnlyPerson> sortedPersons = new ArrayList<>();

        for (ReadOnlyPerson person : unsortedPersons) {
            String sortValue = "";
            switch (sortField) {
                case NAME:
                    sortValue = person.getName().fullName;
                    break;
                case ADDRESS:
                    sortValue = person.getAddress().value;
                    break;
                case EMAIL:
                    sortValue = person.getEmail().value;
                    break;
                case PHONE: {
                    sortValue = person.getPhone().value;
                    break;
                }
                default: {
                    throw new AssertionError("Unhandled sort field for list command. Did you forget to implement it?");
                }
            }

            values.add(sortValue);
            personMap.put(sortValue, person);
        }

        Collections.sort(values);
        for(String sortValue : values) {
            sortedPersons.add(personMap.get(sortValue));
        }

        return new CommandResult(getMessageForPersonListShownSummary(sortedPersons), sortedPersons);
    }

}
