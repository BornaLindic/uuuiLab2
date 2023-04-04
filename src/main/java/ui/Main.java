package ui;

import ui.algorithms.RefutationResolution;
import ui.data.Clause;
import ui.data.DataLoader;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Clause> clauses = DataLoader.loadClauses(Paths.get("examples/resolution_small_example.txt"));

        RefutationResolution rf = new RefutationResolution();
        rf.propositionalLogicResolution(clauses);

    }


}
