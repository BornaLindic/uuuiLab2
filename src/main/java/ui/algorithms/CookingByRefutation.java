package ui.algorithms;

import ui.data.Clause;

import java.util.HashSet;
import java.util.List;

public class CookingByRefutation {

    public static void findRecipes(List<Clause> clauses, List<String> userCommands) {

        RefutationResolution refutationResolution = new RefutationResolution();
        int index = clauses.size() + 1;

        for (String userCommand : userCommands) {
            System.out.println("User command: " + userCommand);
            String clause = userCommand.substring(0, userCommand.length()-2);

            switch (userCommand.substring(userCommand.length() - 1)) {
                case "?" -> {
                    clauses.add(new Clause(
                            new HashSet<>(List.of(clause.split("\sv\s"))),
                            index++)
                    );
                    refutationResolution.propositionalLogicResolution(clauses, clause);
                }
                case "+" -> {
                    clauses.add(new Clause(
                            new HashSet<>(List.of(clause.split("\sv\s"))),
                            index++)
                    );
                    System.out.println("Added " + clause);
                }
                case "-" -> {
                    clauses.remove(new Clause(
                            new HashSet<>(List.of(clause.split("\sv\s"))),
                            0)
                    );
                    System.out.println("Removed " + clause);
                }
                default -> throw new IllegalArgumentException();
            }

            System.out.println();
        }

    }


}
