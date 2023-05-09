import org.junit.jupiter.api.Test;
import view.SignUpMenu;
import view.enums.commands.SignUpCommands;

import java.util.HashMap;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
public class SecurityQTest {

    @Test
    public void test1() {
        HashMap<String, String> answer;
        answer = answerMap("33333", "ookHey", "gooGooLee");

        testExtraction(answer,
                "    question pick    -q   33333     -a    ookHey  -c  gooGooLee");
        testExtraction(answer,
                "question pick    -a    ookHey   -q   33333    -c  gooGooLee");

        testExtraction(answer,
                "question pick    -a    ookHey    -c  \"gooGooLee\" -q   33333");

        testExtraction(answer,
                "question pick    -a    \"ookHey\"    -c  gooGooLee -q   33333"
        );

        testExtraction(answer,
                "    question pick    -a    ookHey    -c  gooGooLee -q   \"33333\"     "
        );

        testExtraction(answer,
                "question          pick    -q   33333     -a    ookHey  -c  gooGooLee"
        );

    }

    @Test
    public void test2() {
        HashMap<String, String> answer;
        answer = answerMap("33333", "ooo kHey", "goo  Goo  Lee");

        testExtraction(answer,
                "question          pick    -q   33333     -a    \"ooo kHey\"  -c  \"goo  Goo  Lee\""
        );

        testExtraction(answer,
                "question          pick    -q   33333     -c  \"goo  Goo  Lee\"  -a    \"ooo kHey\""
        );

        testExtraction(answer,
                "      question          pick  -a    \"ooo kHey\"  -q   33333     -c  \"goo  Goo  Lee\"  "
        );

        testExtraction(answer,
                "question          pick  -a    \"ooo kHey\"    -c  \"goo  Goo  Lee\"  -q   33333"
        );
    }

    @Test
    public void test3(){
        testNull("pick question -q   33333     -a    ookHey  -c  gooGooLee");
        testNull("question pick -q   33333     -a    ookHey  -c  gooGooLee -t test");
        testNull("question pick -q  test 33333     -a    ookHey  -c  gooGooLee");
        testNull("question pick -q   33333   test  -a    ookHey  -c  gooGooLee    ");
        testNull("question pick -q   33333     -a  test  ookHey  -c  gooGooLee");
        testNull("question pick -q   33333     -a  \"test  ookHey\"test  -c  gooGooLee  ");
        testNull("question pick -q   33333     -a  test\"test  ookHey\"  -c  gooGooLee");
        testNull("question pick -q   33333     -a  test  ookHey  -c  \"gooGooLee");
        testNull("question pick -q   33333     -a  ookHey  -c test gooGooLee");
        testNull("question pick -q   33333     -a  test  ookHey  -c  gooGooLee test");
    }

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
