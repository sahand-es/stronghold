package model;

import utility.SHA;

import java.security.NoSuchAlgorithmException;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private int securityQuestionNumber;

    private int rank;
    private int score;
    private int highScore;
    private final String securityAnswer;

    public User(String username, String password, String nickname, String email, int securityQuestionNumber, String securityAnswer) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.securityQuestionNumber = securityQuestionNumber;
        this.securityAnswer = securityAnswer;
        setPassword(password);
        Application.addUser(this);
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getSecurityQuestionNumber() {
        return securityQuestionNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = SHA.shaString(password);
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public boolean checkPassword(String passwordToCheck) {
        return SHA.shaString(passwordToCheck).equals(password);
    }

    public boolean checkAnswer(String answerToCheck) {
        return securityAnswer.equals(answerToCheck);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
