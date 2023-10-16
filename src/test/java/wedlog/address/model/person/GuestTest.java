package wedlog.address.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_DIETARY_REQUIREMENTS_AMY;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_DIETARY_REQUIREMENTS_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_RSVP_STATUS_AMY;
import static wedlog.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static wedlog.address.model.person.PersonTest.VALID_ADDRESS;
import static wedlog.address.model.person.PersonTest.VALID_EMAIL;
import static wedlog.address.model.person.PersonTest.VALID_NAME;
import static wedlog.address.model.person.PersonTest.VALID_PHONE;
import static wedlog.address.model.person.PersonTest.VALID_TAGS;
import static wedlog.address.testutil.Assert.assertThrows;
import static wedlog.address.testutil.TypicalGuests.GINA;
import static wedlog.address.testutil.TypicalGuests.GREG;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import wedlog.address.model.tag.Tag;
import wedlog.address.testutil.GuestBuilder;

/**
 * Simplified test class for Guest.
 * More detailed tests to be written in the future.
 *
 * @author Keagan
 * @version v1.2
 */
public class GuestTest {

    public static final DietaryRequirements VALID_DIETARY_REQUIREMENTS =
            new DietaryRequirements(VALID_DIETARY_REQUIREMENTS_AMY);

    public static final RsvpStatus VALID_RSVP_STATUS = new RsvpStatus(VALID_RSVP_STATUS_AMY);

    @Test
    public void constructor() {
        // Name Null
        assertThrows(NullPointerException.class, () -> new Guest(null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_RSVP_STATUS, VALID_DIETARY_REQUIREMENTS, VALID_TAGS));

        // Phone Null
        assertDoesNotThrow(() -> new Guest(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_RSVP_STATUS, VALID_DIETARY_REQUIREMENTS, VALID_TAGS));

        // Email Null
        assertDoesNotThrow(() -> new Guest(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_RSVP_STATUS, VALID_DIETARY_REQUIREMENTS, VALID_TAGS));

        // Address Null
        assertDoesNotThrow(() -> new Guest(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_RSVP_STATUS, VALID_DIETARY_REQUIREMENTS, VALID_TAGS));

        // Rsvp Status Null
        assertThrows(NullPointerException.class, () -> new Guest(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, null, VALID_DIETARY_REQUIREMENTS, VALID_TAGS));

        // Dietary Requirements Null
        assertThrows(NullPointerException.class, () -> new Guest(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_RSVP_STATUS, null, VALID_TAGS));

        // Tags Null
        assertThrows(NullPointerException.class, () -> new Guest(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_RSVP_STATUS, VALID_DIETARY_REQUIREMENTS, null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Guest ginaCopy = new GuestBuilder(GINA).build();
        assertTrue(GINA.equals(ginaCopy));

        // same object -> returns true
        assertTrue(GINA.equals(GINA));

        // null -> returns false
        assertFalse(GINA.equals(null));

        // different type -> returns false
        assertFalse(GINA.equals(5));

        // different guest -> returns false
        assertFalse(GINA.equals(GREG));

        // different name -> returns false
        Guest editedGina = new GuestBuilder(GINA).withName(VALID_NAME_BOB).build();
        assertFalse(GINA.equals(editedGina));

        // different phone -> returns false
        editedGina = new GuestBuilder(GINA).withPhone(VALID_PHONE_BOB).build();
        assertFalse(GINA.equals(editedGina));

        // different email -> returns false
        editedGina = new GuestBuilder(GINA).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(GINA.equals(editedGina));

        // different address -> returns false
        editedGina = new GuestBuilder(GINA).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(GINA.equals(editedGina));

        // edited rsvp status -> returns false
        editedGina = new GuestBuilder(GINA).withRsvpStatus(VALID_RSVP_STATUS_AMY).build();
        assertFalse(GINA.equals(editedGina));

        // edited dietary requirements -> returns false
        editedGina = new GuestBuilder(GINA).withDietaryRequirements(VALID_DIETARY_REQUIREMENTS_BOB).build();
        assertFalse(GINA.equals(editedGina));

        // different tags -> returns false
        editedGina = new GuestBuilder(GINA).withTags(VALID_TAG_FRIEND).build();
        assertFalse(GINA.equals(editedGina));

        // no phone -> returns false
        editedGina = new GuestBuilder(GINA).withoutPhone().build();
        assertFalse(GINA.equals(editedGina));

        // no email -> returns false
        editedGina = new GuestBuilder(GINA).withoutEmail().build();
        assertFalse(GINA.equals(editedGina));

        // no address -> returns false
        editedGina = new GuestBuilder(GINA).withoutAddress().build();
        assertFalse(GINA.equals(editedGina));
    }

    @Test
    public void toStringTest() {
        Name name = new Name("Bob");
        Phone phone = new Phone("91234567");
        Email email = new Email("bob@bob.com");
        Address address = new Address("Blk 123");
        DietaryRequirements dietaryRequirements = new DietaryRequirements("Halal");
        RsvpStatus rsvpStatus = new RsvpStatus("yes");
        Tag tag = new Tag("friend");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        Guest guest = new Guest(name, phone, email, address, rsvpStatus, dietaryRequirements, tags);

        String expected = Guest.class.getCanonicalName() + "{name=" + guest.getName() + ", phone=" + guest.getPhone()
                + ", email=" + guest.getEmail() + ", address=" + guest.getAddress()
                + ", rsvpStatus=" + guest.getRsvpStatus() + ", dietaryRequirements=" + guest.getDietaryRequirements()
                + ", tags=" + guest.getTags() + "}";
        assertEquals(expected, guest.toString());
    }
}