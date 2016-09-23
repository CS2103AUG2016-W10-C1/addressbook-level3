package seedu.addressbook.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents the medium used to store address book data.
 */
public class Storage {
	
	/** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "";

    public final Path path;

    /**
     * @throws IllegalValueException if the default path is invalid
     */
    public Storage() {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws IllegalValueException if the given medium path is invalid
     */
    public Storage(String filePath) {
        path = Paths.get(filePath);
    }

    /**
     * Saves all data to this storage medium.
     */
    public void save(AddressBook addressBook) throws Exception {
    	;
    }

    /**
     * Loads data from this storage medium.
     */
    public AddressBook load() throws Exception {
		return null;
    }

    public String getPath() {
        return path.toString();
    }
}
