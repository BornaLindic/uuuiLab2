package ui.data;

import java.util.*;

public class Clause {

    private Set<String> literals = new HashSet<>();
    private int index;
    private List<Clause> parents = new ArrayList<>();

    public Clause(Set<String> literals, int index) {
        this.literals = literals;
        this.index = index;
    }

    public Clause(Set<String> literals, int index, List<Clause> parents) {
        this(literals, index);
        this.parents = parents;
    }

    public Set<String> getLiterals() {
        return literals;
    }

    public int getIndex() {
        return index;
    }

    public List<Clause> getParents() {
        return parents;
    }

    @Override
    public String toString() {
        return "Clause{" +
                "literals=" + literals +
                ", index=" + index +
                ", parents=" + parents +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clause clause = (Clause) o;
        return Objects.equals(literals, clause.literals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(literals);
    }
}
