package model;

import utility.SHA;
import view.enums.others.SecurityQuestions;

import java.security.NoSuchAlgorithmException;

public class User
{
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private SecurityQuestions securityQuestion;

    private int score;
    private int highScore;
    private String securityAnswer;

    public User(String username, String password, String nickname, String email, SecurityQuestions securityQuestion, String securityAnswer) throws NoSuchAlgorithmException {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        setPassword(password);

        Application.addUser(this);
    }

    public String getUsername()
    {
        return username;
    }

    public String getNickname()
    {
        return nickname;
    }

    public String getEmail()
    {
        return email;
    }

    public String getSlogan()
    {
        return slogan;
    }

    public int getHighScore()
    {
        return highScore;
    }

    public SecurityQuestions getSecurityQuestion()
    {
        return securityQuestion;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = SHA.shaString(password);
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setSlogan(String slogan)
    {
        this.slogan = slogan;
    }

    public void setHighScore(int highScore)
    {
        this.highScore = highScore;
    }

    public boolean checkPassword(String passwordToCheck) throws NoSuchAlgorithmException {
        return SHA.shaString(passwordToCheck).equals(password);
    }

    public boolean checkAnswer(String answer)
    {
        return true;
    }


}
