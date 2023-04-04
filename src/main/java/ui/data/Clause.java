package ui.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public String toString() {
        return "Clause{" +
                "literals=" + literals +
                ", index=" + index +
                ", parents=" + parents +
                '}';
    }
}
