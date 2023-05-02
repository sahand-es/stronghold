import model.User;
import org.junit.jupiter.api.Test;
import utility.CheckFunctions;

import static org.junit.jupiter.api.Assertions.*;

public class CheckFunctionsTest {
    @Test
    public void usernameFormat() {
        assertTrue(CheckFunctions.checkUsernameFormat("asdf"));
        assertTrue(CheckFunctions.checkUsernameFormat("asdAS_12"));
        assertTrue(CheckFunctions.checkUsernameFormat("1_2A__Sds1"));
        assertTrue(CheckFunctions.checkUsernameFormat("sadf"));

        assertFalse(CheckFunctions.checkUsernameFormat("s  adf"));
        assertFalse(CheckFunctions.checkUsernameFormat("fasdf!As"));
        assertFalse(CheckFunctions.checkUsernameFormat("  sadf"));
        assertFalse(CheckFunctions.checkUsernameFormat("SD-f"));
        assertFalse(CheckFunctions.checkUsernameFormat("   "));
        assertFalse(CheckFunctions.checkUsernameFormat("S "));
    }

    @Test
    public void passwordFormat() {
        assertTrue(CheckFunctions.checkPasswordFormat("Tes!t1"));
        assertTrue(CheckFunctions.checkPasswordFormat("s##Ddf23"));
        assertTrue(CheckFunctions.checkPasswordFormat("AAAAbb~2222"));
        assertTrue(CheckFunctions.checkPasswordFormat("123P$b"));
        assertTrue(CheckFunctions.checkPasswordFormat("S@has90"));

        assertFalse(CheckFunctions.checkPasswordFormat("Test1"));
        assertFalse(CheckFunctions.checkPasswordFormat("s##df23"));
        assertFalse(CheckFunctions.checkPasswordFormat("AAAA~2222"));
        assertFalse(CheckFunctions.checkPasswordFormat("123P$b"));
        assertFalse(CheckFunctions.checkPasswordFormat("S@has"));
    }

    @Test
    public void exists() {
        User user1 = new User("yousef", "S@hand844", "duh", "asdfa@dsaf.com", 2, "bluh");
        User user2 = new User("sahand", "S@hand844", "duh", "asdafa@dsaf.com", 2, "bluh");

        assertTrue(CheckFunctions.checkUsernameExits("sahand"));
        assertTrue(CheckFunctions.checkUsernameExits("yousef"));
        assertFalse(CheckFunctions.checkUsernameExits("armin"));
        assertFalse(CheckFunctions.checkUsernameExits("sahand "));

        assertTrue(CheckFunctions.checkEmailExits("asdfa@dsaf.com"));
        assertTrue(CheckFunctions.checkEmailExits("asdafa@dsaf.com"));
        assertFalse(CheckFunctions.checkEmailExits("armin"));
        assertFalse(CheckFunctions.checkEmailExits("sahand "));
    }

    @Test

    public void emailFormat() {
        assertTrue(CheckFunctions.checkEmailFormat("email1@gmail.com"));
        assertTrue(CheckFunctions.checkEmailFormat("123pastil@shiba.s"));
        assertTrue(CheckFunctions.checkEmailFormat("DOOGH@MIKHAM.ALI"));

        assertFalse(CheckFunctions.checkEmailFormat("@NEMIKHAM.SWF"));
        assertFalse(CheckFunctions.checkEmailFormat("MOOz@.CUM"));
        assertFalse(CheckFunctions.checkEmailFormat("MOOz@."));
        assertFalse(CheckFunctions.checkEmailFormat("MOOz@sd f.asdf"));
        assertFalse(CheckFunctions.checkEmailFormat("MOOz@sdf.asd f"));
        assertFalse(CheckFunctions.checkEmailFormat("MOO z@sdf.asdf"));
        assertFalse(CheckFunctions.checkEmailFormat("MOOzsdf.asdf"));
        assertFalse(CheckFunctions.checkEmailFormat(" @MOOzsdf.asdf"));
        assertFalse(CheckFunctions.checkEmailFormat("@1.asdf"));
    }

}
