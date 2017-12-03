package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;
    
    private final int LOVE = 0, FIFTEEN = 1, THIRTY = 2, FORTY = 3;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        String score;
        if (player1Score == player2Score) {
            return getEvenScore();
        } else if (player1Score >= 4 || player2Score >= 4) {
            return getWinOrAdvantage();
        } else {
            score = getLoveToForty(player1Score) + "-" + getLoveToForty(player2Score);
        }
        return score;
    }

    public String getEvenScore() {
        String score = "";
        switch (player1Score) {
            case LOVE:
                score = "Love-All";
                break;
            case FIFTEEN:
                score = "Fifteen-All";
                break;
            case THIRTY:
                score = "Thirty-All";
                break;
            case FORTY:
                score = "Forty-All";
                break;
            default:
                score = "Deuce";
                break;
        }
        return score;
    }

    public String getWinOrAdvantage() {
        String score;
        int minusResult = player1Score - player2Score;
        if (minusResult == 1) {
            score = "Advantage player1";
        } else if (minusResult == -1) {
            score = "Advantage player2";
        } else if (minusResult >= 2) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }
        return score;
    }

    public String getLoveToForty(int scoreValue) {
        String score = "";
        switch (scoreValue) {
            case LOVE:
                score = "Love";
                break;
            case FIFTEEN:
                score = "Fifteen";
                break;
            case THIRTY:
                score = "Thirty";
                break;
            case FORTY:
                score = "Forty";
                break;
        }
        return score;
    }
}
