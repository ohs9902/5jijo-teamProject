package camp.model;

public class Score {
    private final String scoreId;

    public Score(String seq) {
        this.scoreId = seq;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public Integer getScore() {
        return Integer.parseInt(scoreId);
    }

    public Object getSubjectId() {
        return scoreId;
    }

    public int getRound() {
        return Integer.parseInt(scoreId);
    }

    public Object getStudentId() {
        return scoreId;
    }
}
