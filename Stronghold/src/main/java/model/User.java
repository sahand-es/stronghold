package model;

import view.enums.others.SecurityQuestions;

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

    public User(String username, String password, String nickname, String email, SecurityQuestions securityQuestion, String securityAnswer)
    {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;

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

    public void setPassword(String password)
    {
        this.password = password;
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

    public boolean checkPassword(String password)
    {
        return true;
    }

    public boolean checkAnswer(String answer)
    {
        return true;
    }


}
