import model.User;
import org.junit.jupiter.api.Test;
import utility.CheckFunctions;

import static org.junit.jupiter.api.Assertions.*;

public class CheckFunctionsTest {
    @Test
    public void usernameFormat() {
        assertFalse(CheckFunctions.checkUsernameFormat("asdf"));
        assertFalse(CheckFunctions.checkUsernameFormat("asdAS_12"));
        assertFalse(CheckFunctions.checkUsernameFormat("1_2A__Sds1"));
        assertFalse(CheckFunctions.checkUsernameFormat("sadf"));

        assertTrue(CheckFunctions.checkUsernameFormat("s  adf"));
        assertTrue(CheckFunctions.checkUsernameFormat("fasdf!As"));
        assertTrue(CheckFunctions.checkUsernameFormat("  sadf"));
        assertTrue(CheckFunctions.checkUsernameFormat("SD-f"));
        assertTrue(CheckFunctions.checkUsernameFormat("   "));
        assertTrue(CheckFunctions.checkUsernameFormat("S "));
    }

    @Test
    public void passwordFormat() {
        assertFalse(CheckFunctions.checkPasswordFormat("Tes!t1"));
        assertFalse(CheckFunctions.checkPasswordFormat("s##Ddf23"));
        assertFalse(CheckFunctions.checkPasswordFormat("AAAAbb~2222"));
        assertFalse(CheckFunctions.checkPasswordFormat("123P$b"));
        assertFalse(CheckFunctions.checkPasswordFormat("S@has90"));

        assertTrue(CheckFunctions.checkPasswordFormat("Test1"));
        assertTrue(CheckFunctions.checkPasswordFormat("s##df23"));
        assertTrue(CheckFunctions.checkPasswordFormat("AAAA~2222"));
        assertTrue(CheckFunctions.checkPasswordFormat("S@has"));
    }

    @Test
    public void exists() {
        User user1 = new User("yousef", "S@hand844", "duh", "asdfa@dsaf.com", 2, "bluh",null);
        User user2 = new User("sahand", "S@hand844", "duh", "asdafa@dsaf.com", 2, "bluh",null);

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
        assertFalse(CheckFunctions.checkEmailFormat("email1@gmail.com"));
        assertFalse(CheckFunctions.checkEmailFormat("123pastil@shiba.s"));
        assertFalse(CheckFunctions.checkEmailFormat("DOOGH@MIKHAM.ALI"));

        assertTrue(CheckFunctions.checkEmailFormat("@NEMIKHAM.SWF"));
        assertTrue(CheckFunctions.checkEmailFormat("MOOz@.CUM"));
        assertTrue(CheckFunctions.checkEmailFormat("MOOz@."));
        assertTrue(CheckFunctions.checkEmailFormat("MOOz@sd f.asdf"));
        assertTrue(CheckFunctions.checkEmailFormat("MOOz@sdf.asd f"));
        assertTrue(CheckFunctions.checkEmailFormat("MOO z@sdf.asdf"));
        assertTrue(CheckFunctions.checkEmailFormat("MOOzsdf.asdf"));
        assertTrue(CheckFunctions.checkEmailFormat(" @MOOzsdf.asdf"));
        assertTrue(CheckFunctions.checkEmailFormat("@1.asdf"));
    }

}
