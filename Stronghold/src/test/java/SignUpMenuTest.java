import org.junit.jupiter.api.Test;
import view.SignUpMenu;
import view.enums.commands.SignUpCommands;

import java.util.HashMap;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpMenuTest {
    @Test
    public void test1() {
        HashMap<String, String> answer;
        answer = answerMap("sahand_es", "CorrectP@ssword1", "CorrectP@ssword1",
                "sahand", "email@gmail.com", "thisisatest");

        testExtraction(answer,
                "  user      create     -u     sahand_es   -p     CorrectP@ssword1        CorrectP@ssword1" +
                        "     -n     sahand  -email      email@gmail.com -s thisisatest");
        testExtraction(answer,
                "  user      create     -u     sahand_es   -p     CorrectP@ssword1        CorrectP@ssword1" +
                        "     -n     sahand  -email      email@gmail.com -s thisisatest");

        testExtraction(answer,
                "user create -u sahand_es -p CorrectP@ssword1 CorrectP@ssword1 -email email@gmail.com -n sahand" +
                        " -s thisisatest");

        testExtraction(answer,
                "user create -email email@gmail.com -p CorrectP@ssword1 CorrectP@ssword1 -n sahand -s thisisatest" +
                        " -u sahand_es"
        );

        testExtraction(answer,
                "user    create -email   email@gmail.com    -p CorrectP@ssword1 CorrectP@ssword1 -n   sahand" +
                        " -s thisisatest -u sahand_es"
        );

        testExtraction(answer,
                "       user      create -email    email@gmail.com -p CorrectP@ssword1 CorrectP@ssword1 -n sahand " +
                        "-s    thisisatest -u sahand_es        "
        );
    }

    @Test

    public void test2() {
        HashMap<String, String> answer;
        answer = answerMap("sahand_es", "CorrectP@ssword1", "CorrectP@ssword1",
                "sahand", "email   @gmail   .com", "in che kosSherie?");

        testExtraction(answer,
                "create user -u sahand_es -p CorrectP@ssword1 CorrectP@ssword1 " +
                        "-n sahand -email \"email   @gmail   .com\" -s \"in che kosSherie?\""
        );

        testExtraction(answer,
                "create    user     -u     sahand_es -p CorrectP@ssword1 CorrectP@ssword1 " +
                        "-n sahand -email    \"email   @gmail   .com\" -s       \"in che kosSherie?\""
        );

        testExtraction(answer,
                "create    user  -p CorrectP@ssword1 CorrectP@ssword1      -u     sahand_es " +
                        "-n      sahand -email    \"email   @gmail   .com\" -s       \"in che kosSherie?\""
        );

        testExtraction(answer,
                "   create  user  -p CorrectP@ssword1 CorrectP@ssword1      -u     sahand_es " +
                        "-email    \"email   @gmail   .com\" -s       \"in che kosSherie?\"  -n    sahand "
        );
    }

    @Test
    public void test3(){
        HashMap<String, String> answer;
        answer = answerMap("sahand_es", "CorrectP@ssword1", "CorrectP@ssword1",
                "sahand", "email   @gmail   .com", null);

        testExtraction(answer,
                "create user -u sahand_es -p CorrectP@ssword1 CorrectP@ssword1 " +
                        "-n sahand -email \"email   @gmail   .com\""
        );
        testExtraction(answer,
                "create user -email \"email   @gmail   .com\"     -u sahand_es -p CorrectP@ssword1 CorrectP@ssword1 " +
                        "-n sahand"
        );
        testExtraction(answer,
                "create user -p CorrectP@ssword1 CorrectP@ssword1 -u sahand_es " +
                        "-n sahand -email \"email   @gmail   .com\""
        );

    }

    @Test
    public void test4(){
        testNull("user create -u sahand_es -u nigga -p pass1 pass1 -n sahand -email email@gmail.com -s thisisatest");
        testNull("user create -u sahand_es -p pass1 pass1 bluh -n sahand -email email@gmail.com -s thisisatest");
        testNull("user create -u sahand_es -p pass1 pass1 -n sahand -email email@gmail.com ezafe ezafe");
        testNull("user create -u sahand_es -p pass1 pass1 -n sahand -email email@gmail.com -b wtf");
        testNull("user create ezafe inja -u sahand_es -p pass1 pass1 -n sahand -email email@gmail.com  ");
    }

    public void testNull(String input) {

        HashMap<String, String> data = SignUpMenu.extractCreateCommand(input);
        assertNull(data, "It should return null");
    }


    public void testExtraction(HashMap<String, String> answer, String input) {
        HashMap<String, String> data = SignUpMenu.extractCreateCommand(input);
        assertTrue(isEqual(answer, data));
    }

    private boolean isEqual(HashMap<String, String> answer, HashMap<String, String> data) {
        for (String s : answer.keySet()) {
            if (!answer.get(s).equals(data.get(s)))
                return false;
        }

        return true;
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
