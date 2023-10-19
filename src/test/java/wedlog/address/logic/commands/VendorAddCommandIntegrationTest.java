package wedlog.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wedlog.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static wedlog.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static wedlog.address.testutil.TypicalVendors.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wedlog.address.logic.Messages;
import wedlog.address.model.AddressBook;
import wedlog.address.model.Model;
import wedlog.address.model.ModelManager;
import wedlog.address.model.UserPrefs;
import wedlog.address.model.person.Vendor;
import wedlog.address.testutil.VendorBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code VendorAddCommand}.
 */
public class VendorAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newVendorIntoEmptyAddressBook_success() {
        model = new ModelManager(new AddressBook(), new UserPrefs());
        Vendor validVendor = new VendorBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addVendor(validVendor);

        assertCommandSuccess(new VendorAddCommand(validVendor), model,
                String.format(VendorAddCommand.MESSAGE_SUCCESS, Messages.format(validVendor)),
                expectedModel);

        Vendor insertedVendor = model.getFilteredVendorList().get(0);

        // Check that all fields of the Vendor have been stored properly in WedLog
        assertEquals(insertedVendor.getName(), validVendor.getName());
        assertEquals(insertedVendor.getPhone(), validVendor.getPhone());
        assertEquals(insertedVendor.getEmail(), validVendor.getEmail());
        assertEquals(insertedVendor.getAddress(), validVendor.getAddress());
        assertEquals(insertedVendor.getTags(), validVendor.getTags());
    }

    @Test
    public void execute_newVendorIntoPrefilledAddressBook_success() {
        Vendor validVendor = new VendorBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addVendor(validVendor);

        assertCommandSuccess(new VendorAddCommand(validVendor), model,
                String.format(VendorAddCommand.MESSAGE_SUCCESS, Messages.format(validVendor)),
                expectedModel);

    }

    @Test
    public void execute_duplicateVendor_throwsCommandException() {
        Vendor vendorInList = model.getAddressBook().getVendorList().get(0);
        assertCommandFailure(new VendorAddCommand(vendorInList), model,
                VendorAddCommand.MESSAGE_DUPLICATE_VENDOR);
    }

}
