package view;

import controller.SignUpControl;
import utility.SecurityQuestions;
import view.enums.commands.SignUpCommands;
import view.enums.messages.SignUpMessages;

import javax.management.StringValueExp;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpMenu
{
    private static HashMap<String,String> createData ;
    static{
        createData = new HashMap<>();
    }
    public static void run()
    {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command;
        Matcher matcher;

        while(true){
            command = scanner.nextLine();
            if (SignUpCommands.getMatcher(command, SignUpCommands.EXIT) != null){
                return;
            }
            else if((matcher = SignUpCommands.getMatcher(command, SignUpCommands.CREATE )) != null){
                checkCreateUser(matcher, command);
            }
            else
                System.out.println("My liege, that's an invalid command!");
        }
    }


    private static void extractCreateCommand(Matcher matcher,String command){
        createData.clear();
        String username = "";
        String password= "";
        String passwordConfirm = "";
        String nickname = "";
        String email = "";
        String slogan = "";

        String regex = SignUpCommands.getRegexARGUMENT();
        Pattern pattern = Pattern.compile(regex);
        Matcher checkMatcher = pattern.matcher(command);

        while (checkMatcher.find()){
            String argName = checkMatcher.group("argument");
            String argNameSpace = checkMatcher.group("argumentSpace");
            String argVal,argVal2,argVal2SpaceNON;
            String argValSpace, argVal2Space;
            if (argName != null){
                argVal = checkMatcher.group("firstString");
                argVal2 = checkMatcher.group("secondString");
                switch (argName){
                    case "u":
                        username = argVal;
                        break;
                    case "p":
                        password = argVal;
                        passwordConfirm = argVal2;
                        break;
                    case "n":
                        nickname = argVal;
                        break;
                    case "email":
                        email = argVal;
                        break;
                    case "s":
                        slogan = argVal;
                        break;
                    default:
                        System.out.println("My liege, that's an invalid argument in create user command!");
                        createData.clear();
                        return;
                }
            }
            else {
                argValSpace = checkMatcher.group("firstStringSpace");
                argVal2Space = checkMatcher.group("secondStringSpace");
                argVal2SpaceNON = checkMatcher.group("secondStringsSpaceNON");
                switch (argNameSpace){
                    case "u":
                        username = argValSpace;
                        break;
                    case "p":
                        password = argValSpace;
                        passwordConfirm = argVal2Space + argVal2SpaceNON;
                        break;
                    case "n":
                        nickname = argValSpace;
                        break;
                    case "email":
                        email = argValSpace;
                        break;
                    case "s":
                        slogan = argValSpace;
                        break;
                    default:
                        System.out.println("My liege, that's an invalid argument in create user command!");
                        createData.clear();
                        return;
                }
            }
        }
        createData.put("username",username);
        createData.put("nickname",nickname);
        createData.put("password",password);
        createData.put("passwordConfirm",passwordConfirm);
        createData.put("email",email);
        createData.put("slogan",slogan);
    }

    private static void checkCreateUser(Matcher matcher,String command){
        extractCreateCommand(matcher,command);
        SignUpMessages message;
        if (command.matches(".*-s.*") && createData.get("slogan").equals("")){
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

    public static Boolean secondLayerCheckRandom(String password){
        System.out.println("Your random password is: " + password);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String passCheck;
        while (true){
            System.out.print("Please re-enter your password here:");
            passCheck = scanner.nextLine();
            if (passCheck.equals(password)){
                return true;
            }
            else if (passCheck.equals("back")) {
                return false;
            }
            else
                System.out.println("Password doesn't match! in order to go to signup menu type \"back\"");
        }
    }

    public static String getSecurityQuestionAnswer(){
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Pick you're security question number and then give an answer to it");
        System.out.println("it must be in this format: question pick -q <question number> -a <answer> -c <answer confirm>");
        for (int i = 0; i< SecurityQuestions.securityQuestions.length ; i++){
            int a = i + 1;
            System.out.println(a + ", " + SecurityQuestions.securityQuestions[i]);
        }
        String command;
        while(true){
            command = scanner.nextLine();
            Matcher matcher;
            if ((matcher = SignUpCommands.getMatcher(command, SignUpCommands.QUESTION )) != null){
                String questionNumber = matcher.group("questionNumber");
                String answer = matcher.group("answer");
                String answerConfirm = matcher.group("answerConfirm");
                if (answer.equals(answerConfirm)){
                    return questionNumber + "-" + answer;
                }
                else
                    System.out.println("answer confirmation doesn't match");
            }
            else
                System.out.println("My liege, that's an invalid command!");
        }
    }

    public static void main(String[] args){
        run();
    }

}
