package utility;

public class SecurityQuestions {
    public static String[] securityQuestions = {
            "What was your first job? ",
            "What was your first pet’s name? ",
            "What is your social security number? "
    };

    public static String getSecurityQuestion(int index){
        return securityQuestions[index];
    }
}
