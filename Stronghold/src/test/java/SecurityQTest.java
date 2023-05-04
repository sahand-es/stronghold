import org.junit.jupiter.api.Test;
import view.SignUpMenu;
import view.enums.commands.SignUpCommands;

import java.util.HashMap;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
public class SecurityQTest {

    public void testNull(String input) {
        HashMap<String, String> data = SignUpMenu.extractSecurityAnswer(input);
        assertNull(data, "It should return null");
    }


    public void testExtraction(HashMap<String, String> answer, String input) {
        HashMap<String, String> data = SignUpMenu.extractSecurityAnswer(input);
        assertTrue(isEqual(answer, data));
    }

    private boolean isEqual(HashMap<String, String> answer, HashMap<String, String> data) {
        for (String s : answer.keySet()) {
            if (!answer.get(s).equals(data.get(s)))
                return false;
        }

        return true;
    }

    private HashMap<String, String> answerMap(String questionNumber, String answer, String answerConfirm) {
        HashMap<String, String> data = new HashMap<>();
        data.put("questionNumber", questionNumber);
        data.put("answer", answer);
        data.put("answerConfirm", answerConfirm);

        return data;
    }

}
