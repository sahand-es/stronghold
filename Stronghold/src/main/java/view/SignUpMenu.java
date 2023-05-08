package view;

import controller.SignUpControl;
import utility.CheckFunctions;
import utility.SecurityQuestions;
import view.enums.commands.SignUpCommands;
import view.enums.messages.SignUpMessages;

import javax.management.StringValueExp;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpMenu {
    private static HashMap<String, String> createData;
    private static HashMap<String, String> questionData;

    static {
        createData = new HashMap<>();
        questionData = new HashMap<>();
    }

    public static void run() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;
        Matcher matcher;

        while (true) {
            command = scanner.nextLine();
            //ToDo add back feature
            if (SignUpCommands.getMatcher(command, SignUpCommands.EXIT) != null) {
                return;
            } else if ((matcher = SignUpCommands.getMatcher(command, SignUpCommands.CREATE)) != null) {
                extractCreateCommand(command);
                checkCreateUser(matcher,command);
            } else
                System.out.println("My liege, that's an invalid command!");
        }
    }


    public static HashMap<String, String> extractCreateCommand(String command) {
        if(createData != null){ createData.clear(); }
        String username = "";
        String password = "";
        String passwordConfirm = "";
        String nickname = "";
        String email = "";
        String slogan = "";

        String regex = SignUpCommands.getRegexARGUMENT();
        Pattern pattern = Pattern.compile(regex);
        Matcher checkMatcher = pattern.matcher(command);

        while (checkMatcher.find()) {
            String argName = checkMatcher.group("argument");
            String argNameSpace = checkMatcher.group("argumentSpace");
            String argVal, argVal2, argVal2SpaceNON;
            String argValSpace, argVal2Space;
            if (argName != null) {
                argVal = checkMatcher.group("firstString");
                argVal2 = checkMatcher.group("secondString");
                if (argName.equals("u") && username.equals("")){
                    username = argVal;
                }
                else if (argName.equals("p") && password.equals("") && passwordConfirm.equals("")){
                    password = argVal;
                    passwordConfirm = argVal2;
                }
                else if (argName.equals("n") && nickname.equals("")){
                    nickname = argVal;
                }
                else if (argName.equals("email") && email.equals("")){
                    email = argVal;
                }
                else if (argName.equals("s") && slogan.equals("")){
                    slogan = argVal;
                }
                else {
                    System.out.println("My liege, that's an invalid argument in create user command!");
                    if(createData != null){ createData.clear(); }
                    createData = null;
                    return null;
                }
            }
            else {
                argValSpace = checkMatcher.group("firstStringSpace");
                argVal2Space = checkMatcher.group("secondStringSpace");
                argVal2SpaceNON = checkMatcher.group("secondStringsSpaceNON");
                if (argNameSpace.equals("u") && username.equals("")){
                    username = argValSpace;
                }
                else if (argNameSpace.equals("p") && password.equals("") && passwordConfirm.equals("")){
                    password = argValSpace;
                    passwordConfirm = argVal2Space + argVal2SpaceNON;
                }
                else if (argNameSpace.equals("n") && nickname.equals("")){
                    nickname = argValSpace;
                }
                else if (argNameSpace.equals("email") && email.equals("")){
                    email = argValSpace;
                }
                else if (argNameSpace.equals("s") && slogan.equals("")){
                    slogan = argValSpace;
                }
                else {
                    System.out.println("My liege, that's an invalid argument in create user command!");
                    if(createData != null){ createData.clear(); }
                    createData = null;
                    return null;
                }
            }

            command = command.replaceAll(checkMatcher.group().toString().trim(),"");


        }

        if (SignUpCommands.getMatcher(command, SignUpCommands.FINALCREATECHECK) != null){
            createData.put("username", username);
            createData.put("nickname", nickname);
            createData.put("password", password);
            createData.put("passwordConfirm", passwordConfirm);
            createData.put("email", email);
            createData.put("slogan", slogan);

            return createData;
        }
        else{
            System.out.println("My liege, that's an invalid argument in create user command!");
            createData = null;
            return null;
        }
    }

    public static HashMap<String, String> extractSecurityAnswer(String command) {
        String questionNumber = "";
        String answer = "";
        String answerConfirm = "";
        boolean found = false;
        String regex = SignUpCommands.getRegexQUESTIONARGUMENT();
        Pattern pattern = Pattern.compile(regex);
        Matcher checkMatcher = pattern.matcher(command);

        while (checkMatcher.find()) {
            found = true;
            String argName = checkMatcher.group("argument");
            String argVal, argValSpace;
            if (argName != null) {
                argVal = checkMatcher.group("string");
                argValSpace = checkMatcher.group("stringSpace");
                if (argName.equals("q") && questionNumber.equals("")){
                    questionNumber = (argVal != null) ? argVal : argValSpace;
                }
                else if (argName.equals("a") && answer.equals("")){
                    answer = (argVal != null) ? argVal : argValSpace;
                }
                else if (argName.equals("c") && answerConfirm.equals("")){
                    answerConfirm = (argVal != null) ? argVal : argValSpace;
                }
                else {
                    System.out.println("My liege, that's an invalid argument in question pick command!");
                    if(questionData != null){ questionData.clear(); }
                    questionData = null;
                    return null;
                }
            }

            command = command.replaceAll(checkMatcher.group().toString().trim(),"");

        }
        if (SignUpCommands.getMatcher(command,SignUpCommands.FINALQUESTIONCHECK) == null){
            System.out.println("My liege, that's an invalid argument in question pick command!");
            if(questionData != null){ questionData.clear(); }
            questionData = null;
            return null;
        }

        if (found){
            questionData = new HashMap<>();
            questionData.put("questionNumber",questionNumber);
            questionData.put("answer",answer);
            questionData.put("answerConfirm",answerConfirm);
            return questionData;
        }
        else
            return null;
    }

    private static void checkCreateUser(Matcher matcher, String command) {
        SignUpMessages message;
        if(createData != null){
            if (command.matches(".*-s.*") && createData.get("slogan").equals("")) {
                System.out.println("My liege, you must give a slogan when you give it's argument!");
            }
            else {
                message = SignUpControl.signUp(createData);
                switch (message) {
                    case EMPTY_USERNAME:
                        System.out.println("My liege, you must give a username!");
                        break;
                    case EMPTY_PASSWORD:
                        System.out.println("My liege, you must give a password!");
                        break;
                    case EMPTY_PASSWORD_CONFIRM:
                        System.out.println("My liege, you must confirm your password");
                        break;
                    case INVALID_PASS_CONFIRM:
                        System.out.println("My liege, your password doesn't match with the password confirmation");
                        break;
                    case EMPTY_NICKNAME:
                        System.out.println("My liege, you must give a nickname!");
                        break;
                    case EMPTY_EMAIL:
                        System.out.println("My liege, you must give an email!");
                        break;
                    case INVALID_USER_FORMAT:
                        System.out.println("My liege, your username format is invalid");
                        break;
                    case USER_EXIST:
                        System.out.println("My liege, this username already exist");
                        break;
                    case INSUFFICIENT_PASS:
                        System.out.println("My liege, your password must be at least 6 characters");
                        break;
                    case INVALID_PASS_FORMAT:
                        System.out.println("My liege, your password format is invalid");
                        break;
                    case EXISTING_EMAIL:
                        System.out.println("My liege, this email already exist");
                        break;
                    case INVALID_EMAIL:
                        System.out.println("My liege, your email format is invalid");
                        break;
                    case INVALID_COMMAND_COMBINATION:
                        System.out.println("My liege, you have gave an invalid combination");
                        break;
                    case SUCCESS:
                        System.out.println("Your account has been successfully created!");
                        break;
                    case FAILED:
                        System.out.println("SignUp Failed!");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static Boolean secondLayerCheckRandom(String password) {
        System.out.println("Your random password is: " + password);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String passCheck;
        while (true) {
            System.out.print("Please re-enter your password here:");
            passCheck = scanner.nextLine();
            if (passCheck.equals(password)) {
                return true;
            } else if (passCheck.equals("back")) {
                return false;
            } else
                System.out.println("Password doesn't match! in order to go to signup menu type \"back\"");
        }
    }



    public static HashMap<String, String> getSecurityQuestionAnswer() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Pick you're security question number and then give an answer to it");
        System.out.println("it must be in this format: question pick -q <question number> -a <answer> -c <answer confirm>");
        for (int i = 0; i < SecurityQuestions.securityQuestions.length; i++) {
            int a = i + 1;
            System.out.println(a + ", " + SecurityQuestions.securityQuestions[i]);
        }
        String command;
        outer:
        while (true) {
            command = scanner.nextLine();
            Matcher matcher;

            if((matcher = SignUpCommands.getMatcher(command, SignUpCommands.BACK)) != null){
                if(questionData != null){ questionData = null; }
                return null;
            }
            else if ((matcher = SignUpCommands.getMatcher(command, SignUpCommands.QUESTION)) != null) {
                if(questionData != null){ questionData.clear(); }
                if((questionData = extractSecurityAnswer(command)) != null){

                    SignUpMessages message = CheckFunctions.questionCommandCheck(questionData);
                    switch (message) {
                        case QUESTION_INVALID_NUMBER:
                            System.out.println("My liege, you must give a number between 1 to 3!");
                            break;
                        case QUESTION_INVALID_ANSWER:
                            System.out.println("My liege, your answers don't match!");
                            break;
                        case SUCCESS:
                            break outer;
                        default:
                            break;
                    }
                }
                System.out.println("Re-enter your answer or type \"back\" to get back to signup menu:");
            }
            else{
                System.out.println("My liege, that's an invalid command!");
                System.out.println("Re-enter your answer or type \"back\" to get back to signup menu:");
            }
        }

        return questionData;
    }

}
