package camp.model;

public class Score {
    private final String scoreId;
    private  String subjectId;
    private String studentId;
    private int times;
    private int score;
    private char rank;

    public String getSubjectId() {
        return subjectId;
    }

    public Score(String scoreId, String subjectId, String studentId, int times, int score, char rank) {
        this.scoreId = scoreId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.times = times;
        this.score = score;
        this.rank = rank;
    }


    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getScore() {
        return score;
    }

    public int getTimes() {
        return times;
    }

    public char getRank() {
        return rank;
    }
}
