package wedlog.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static wedlog.address.logic.commands.CommandTestUtil.DESC_GABE;
import static wedlog.address.logic.commands.CommandTestUtil.DESC_GIA;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_DIETARY_REQUIREMENTS_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_RSVP_STATUS_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_TABLE_NUMBER_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import wedlog.address.logic.commands.GuestEditCommand.EditGuestDescriptor;
import wedlog.address.testutil.EditGuestDescriptorBuilder;

public class EditGuestDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditGuestDescriptor descriptorWithSameGiaues = new EditGuestDescriptor(DESC_GIA);
        assertTrue(DESC_GIA.equals(descriptorWithSameGiaues));

        // same object -> returns true
        assertTrue(DESC_GIA.equals(DESC_GIA));

        // null -> returns false
        assertFalse(DESC_GIA.equals(null));

        // different types -> returns false
        assertFalse(DESC_GIA.equals(5));

        // different values -> returns false
        assertFalse(DESC_GIA.equals(DESC_GABE));

        // different name -> returns false
        EditGuestDescriptor editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_GIA.equals(editedGia));

        // different phone -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_GIA.equals(editedGia));

        // different email -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_GIA.equals(editedGia));

        // different address -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_GIA.equals(editedGia));

        // different rsvp -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withRsvp(VALID_RSVP_STATUS_BOB).build();
        assertFalse(DESC_GIA.equals(editedGia));

        // different table number -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withTable(VALID_TABLE_NUMBER_BOB).build();
        assertFalse(DESC_GIA.equals(editedGia));

        // different dietary -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withDietary(VALID_DIETARY_REQUIREMENTS_BOB).build();
        assertFalse(DESC_GIA.equals(editedGia));

        // different tags -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_GIA.equals(editedGia));

        // absent phone -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withoutPhone().build();
        assertFalse(DESC_GIA.equals(editedGia));

        // absent email -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withoutEmail().build();
        assertFalse(DESC_GIA.equals(editedGia));

        // absent address -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withoutAddress().build();
        assertFalse(DESC_GIA.equals(editedGia));

        // unknown rsvp -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withUnknownRsvp().build();
        assertFalse(DESC_GIA.equals(editedGia));

        // absent table number -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withoutTable().build();
        assertFalse(DESC_GIA.equals(editedGia));

        // absent dietary -> returns false
        editedGia = new EditGuestDescriptorBuilder(DESC_GIA).withDietary().build();
        assertFalse(DESC_GIA.equals(editedGia));

    }

    @Test
    public void toStringMethod() {
        EditGuestDescriptor editGuestDescriptor = new EditGuestDescriptor();
        String expected = EditGuestDescriptor.class.getCanonicalName() + "{name="
                + editGuestDescriptor.getName().orElse(null) + ", phone="
                + editGuestDescriptor.getPhone().orElse(null) + ", email="
                + editGuestDescriptor.getEmail().orElse(null) + ", address="
                + editGuestDescriptor.getAddress().orElse(null) + ", rsvp status="
                + editGuestDescriptor.getRsvp() + ", dietary requirement="
                + editGuestDescriptor.getDietary().orElse(null) + ", table number="
                + editGuestDescriptor.getTable().orElse(null) + ", tags="
                + editGuestDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editGuestDescriptor.toString());
    }
}
