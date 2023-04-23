import org.junit.jupiter.api.Test;
import view.enums.commands.SignUpCommands;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class SignUpMenuTest {
    /*
    exit:

    exit
        exit
    exit

    create:

    move:

    user create -u sahand_es -p CorrectP@ssword1 CorrectP@ssword1 -email email@gmail.com -n sahand -s "this is a test"
    user create -p CorrectP@ssword1 CorrectP@ssword1 -email email@gmail.com -n sahand -s "this is a test" -u sahand_es
    user create -n sahand -p CorrectP@ssword1 CorrectP@ssword1 -email email@gmail.com -s "this is a test" -u sahand_es
    user create -n sahand -p CorrectP@ssword1 CorrectP@ssword1 -u sahand_es -email email@gmail.com
    user create -p CorrectP@ssword1 CorrectP@ssword1 -n sahand -u sahand_es -email email@gmail.com
    user create -u sahand_es -p CorrectP@ssword1 CorrectP@ssword1 -email email@gmail.com -n sahand -s thisisatest

    quote and space:

    user create -u "sahand_es      "       -p        CorrectP@ssword1     CorrectP@ssword1    -email    email@gmail.com -n sahand -s "this is a test"



    random:

    user create -u sahand_es -p random -email email@gmail.com -n sahand
    user create -u sahand_es -p CorrectP@ssword1 CorrectP@ssword1 -email email@gmail.com -n sahand -s random
    user create -u sahand_es -p random CorrectP@ssword1 -email email@gmail.com -n sahand -s random


     */
    @Test
    public void tests() {
        HashMap<String, String> answer = new HashMap<>();
        answer = answerMap("sahand_es", "CorrectP@ssword1", "CorrectP@ssword1", "sahand", "email@gmail.com", "thisisatest");
        assertTrue(regexTest(
                answer,
                "user create -u sahand_es -p CorrectP@ssword1 CorrectP@ssword1 -n sahand -email email@gmail.com -s thisisatest"
        ));

        assertTrue(regexTest(
                answer,
                "  user      create     -u     sahand_es   -p     CorrectP@ssword1        CorrectP@ssword1     -n     sahand  -email      email@gmail.com -s thisisatest"
        ));

        assertTrue(regexTest(
                answer,
                "user create -u sahand_es -p CorrectP@ssword1 CorrectP@ssword1 -email email@gmail.com -n sahand -s thisisatest"
        ));

        assertTrue(regexTest(
                answer,
                "user create -email email@gmail.com -p CorrectP@ssword1 CorrectP@ssword1 -n sahand -s thisisatest -u sahand_es"
        ));

        assertTrue(regexTest(
                answer,
                "user    create -email   email@gmail.com    -p CorrectP@ssword1 CorrectP@ssword1 -n   sahand -s thisisatest -u sahand_es"
        ));

        assertTrue(regexTest(
                answer,
                "       user      create -email    email@gmail.com -p CorrectP@ssword1 CorrectP@ssword1 -n sahand -s    thisisatest -u sahand_es        "
        ));


    }


    public boolean regexTest(HashMap<String, String> answer, String input) {
        Matcher matcher = SignUpCommands.getMatcher(input, SignUpCommands.CREATE);

        HashMap<String, String> data;
        if (matcher != null)
            data = checkCreateUser(matcher);
        else {
            fail("regex did not matched");
            return false;
        }

        return isEqual(answer, data);
    }

    private boolean isEqual(HashMap<String, String> answer, HashMap<String, String> data) {
        for (String s : answer.keySet()) {
            if (!answer.get(s).equals(data.get(s)))
                return false;
        }

        return true;
    }

    private HashMap<String, String> checkCreateUser(Matcher matcher) {
        HashMap<String, String> data = new HashMap<>();

        String username = matcher.group("username");
        if (username != null) {
            username = username.trim();
        }
        String password = matcher.group("password");
        if (password != null) {
            password = password.trim();
        }
        String passwordConfirm = matcher.group("confirmPassword");
        if (passwordConfirm != null) {
            passwordConfirm = passwordConfirm.trim();
        }
        String nickname = matcher.group("nickname");
        if (nickname != null) {
            nickname = nickname.trim();
        }
        String email = matcher.group("email");
        if (email != null) {
            email = email.trim();
        }
        String slogan = matcher.group("slogan");

        data.put("username", username);
        data.put("password", password);
        data.put("passwordConfirm", passwordConfirm);
        data.put("nickname", nickname);
        data.put("email", email);
        data.put("slogan", slogan);

        return data;
    }

    private HashMap<String, String> answerMap(String username, String password, String passwordConfirm, String nickname, String email, String slogan) {
        HashMap<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        data.put("passwordConfirm", passwordConfirm);
        data.put("nickname", nickname);
        data.put("email", email);
        if (slogan != null)
            data.put("slogan", slogan);

        return data;
    }
}
