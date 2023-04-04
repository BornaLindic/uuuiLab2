package ui.data;

import java.util.List;

public class CookingDescriptor {

    List<Clause> clauses;
    List<String> userCommands;

    public CookingDescriptor(List<Clause> clauses, List<String> userCommands) {
        this.clauses = clauses;
        this.userCommands = userCommands;
    }

    public List<Clause> getClauses() {
        return clauses;
    }

    public List<String> getUserCommands() {
        return userCommands;
    }
}
