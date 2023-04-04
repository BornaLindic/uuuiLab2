package ui.algorithms;

import ui.data.Clause;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RefutationResolution {
    
    private int index;
    private boolean foundNIL;

    public boolean propositionalLogicResolution(List<Clause> clauses) {

        Set<Clause> sos = negateGoalClause(clauses.remove(clauses.size()-1)); //set of support
        Set<Clause> entry = new HashSet<>(clauses);

        // check if there is redundant or unimportant clauses in initial dataset
        Set<Clause> union = new HashSet<>(sos);
        union.addAll(entry);
        for (Clause clause : union) {
            if(isRedundant(clause, union) || isUnimportant(clause)) {
                sos.remove(clause);
                entry.remove(clause);
            }
        }

        Set<Clause> newClauses = new HashSet<>();
        Set<Set<Clause>> checkedPairs = new HashSet<>();
        while (true) {
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
                    if(foundNIL) {
                        System.out.println("ovdje formatirani ispis za pronalazak");
                        return true;
                    }

                    Set<Clause> finalUnion = union;
                    resolvents.removeIf(resolvent -> isRedundant(resolvent, finalUnion) || isUnimportant(resolvent));
                    newClauses.addAll(resolvents);

                    checkedPairs.add(pair);
                }
            }
            
            if (union.containsAll(newClauses)) {
                System.out.println("ispis za fail");
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
            if(other.getLiterals().containsAll(target.getLiterals())) return true;
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
        return new HashSet<>();
    }


}
