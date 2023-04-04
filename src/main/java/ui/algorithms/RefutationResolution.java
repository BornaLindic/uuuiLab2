package ui.algorithms;

import ui.data.Clause;

import java.util.*;

public class RefutationResolution {
    
    private int index;
    private boolean foundNIL;

    public boolean propositionalLogicResolution(List<Clause> clauses, String goalClause) {

        Clause goal = clauses.remove(clauses.size()-1);
        Set<Clause> sos = negateGoalClause(goal); //set of support
        Set<Clause> entry = new HashSet<>(clauses);
        Set<Set<Clause>> checkedPairs = new HashSet<>();

        while (true) {
            Set<Clause> newClauses = new HashSet<>();
            Set<Clause> union = new HashSet<>(sos);
            union.addAll(entry);

            for (Clause clause : union) {
                if(isRedundant(clause, union) || isUnimportant(clause)) {
                    sos.remove(clause);
                    entry.remove(clause);
                }
            }

            union = new HashSet<>(sos);
            union.addAll(entry);

            for (Clause first : sos) {
                for (Clause second : union) {
                    if(first.equals(second)) continue;
                    
                    Set<Clause> pair = new HashSet<>();
                    pair.add(first);
                    pair.add(second);
                    if(checkedPairs.contains(pair)) continue;
                    
                    Set<Clause> resolvents = plResolve(first, second);
                    for (Clause resolvent : resolvents) {
                        if (resolvent.getLiterals().contains("NIL")) {
                            formatOutput(resolvent, goalClause);
                            return true;
                        }
                    }

                    resolvents.removeIf(this::isUnimportant);
                    newClauses.addAll(resolvents);
                    checkedPairs.add(pair);
                }
            }
            
            if (union.containsAll(newClauses) || newClauses.isEmpty()) {
                System.out.println("[CONCLUSION]: " + goalClause + " is unknown");
                return false;
            }

            sos.addAll(newClauses);
        }
    }


    private Set<Clause> negateGoalClause(Clause goal) {
        Set<Clause> result = new HashSet<>();

        index = goal.getIndex();
        for (String literal : goal.getLiterals()) {
            String negatedLiteral;
            if (literal.startsWith("~")) {
                negatedLiteral = literal.substring(1);
            } else negatedLiteral = "~" + literal;

            Set<String> literals = new HashSet<>();
            literals.add(negatedLiteral);

            result.add(new Clause(
                    literals,
                    index++)
            );
        }

        return result;
    }


    private boolean isRedundant(Clause target, Set<Clause> clauses) {
        for (Clause other : clauses) {
            if(target.equals(other)) continue;
            if(target.getLiterals().containsAll(other.getLiterals())) return true;
        }
        return false;
    }


    private boolean isUnimportant(Clause target) {
        for (String literal : target.getLiterals()) {
            if (literal.startsWith("~") && target.getLiterals().contains(literal.substring(1))) return true;
            if (!literal.startsWith("~") && target.getLiterals().contains("~" + literal)) return true;
        }
        return false;
    }


    private Set<Clause> plResolve(Clause first, Clause second) {
        Set<Clause> resolvents = new HashSet<>();

        for (String literal : first.getLiterals()) {
            Set<String> resolventLiterals = new HashSet<>();

            if (literal.startsWith("~") && second.getLiterals().contains(literal.substring(1))) {
                resolventLiterals.addAll(first.getLiterals());
                resolventLiterals.addAll(second.getLiterals());
                resolventLiterals.remove(literal);
                resolventLiterals.remove(literal.substring(1));

                if (resolventLiterals.isEmpty()) resolventLiterals.add("NIL");

                resolvents.add(new Clause(
                        resolventLiterals,
                        index++,
                        List.of(first, second)
                ));
            }

            if (!literal.startsWith("~") && second.getLiterals().contains("~" + literal)) {
                resolventLiterals.addAll(first.getLiterals());
                resolventLiterals.addAll(second.getLiterals());
                resolventLiterals.remove(literal);
                resolventLiterals.remove("~" + literal);

                if (resolventLiterals.isEmpty()) resolventLiterals.add("NIL");

                resolvents.add(new Clause(
                        resolventLiterals,
                        index++,
                        List.of(first, second)
                ));
            }
        }

        return resolvents;
    }


    private void formatOutput(Clause nil, String goalClause) {
        Set<Clause> parents = new TreeSet<>(Comparator.comparingInt(Clause::getIndex));

        Queue<Clause> queue = new LinkedList<>();
        queue.add(nil);
        while (!queue.isEmpty()) {
            Clause curr = queue.poll();
            parents.add(curr);
            queue.addAll(curr.getParents());
        }

        for (Clause clause : parents) {
            System.out.printf("%d. ", clause.getIndex());

            List<String> literals = new ArrayList<>(clause.getLiterals());
            for (int i = 0; i < literals.size(); i++) {
                System.out.print(literals.get(i) + (i == literals.size()-1 ? "" : " v "));
            }

            if (clause.hasParents()) {
                System.out.printf(" (%d, %d)",
                        clause.getParents().get(0).getIndex(), clause.getParents().get(1).getIndex());
            }
            System.out.print("\n");
        }

        System.out.println("===============");
        System.out.println("[CONCLUSION]: " + goalClause + " is true");
    }


}
