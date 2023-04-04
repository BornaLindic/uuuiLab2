package ui.data;

import java.util.List;

public class ClausesDescriptor {

    private List<Clause> clauses;
    private String goalClause;

    public ClausesDescriptor(List<Clause> clauses, String finalClause) {
        this.clauses = clauses;
        this.goalClause = finalClause;
    }

    public List<Clause> getClauses() {
        return clauses;
    }

    public String getGoalClause() {
        return goalClause;
    }
}
