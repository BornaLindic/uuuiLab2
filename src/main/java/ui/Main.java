package ui;

import ui.algorithms.CookingByRefutation;
import ui.algorithms.RefutationResolution;
import ui.data.Clause;
import ui.data.ClausesDescriptor;
import ui.data.CookingDescriptor;
import ui.data.DataLoader;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        // ClausesDescriptor clausesDescriptor = DataLoader.loadClauses(Paths.get("examples/cooking_coffee.txt"));
        // RefutationResolution rf = new RefutationResolution();
        // rf.propositionalLogicResolution(clausesDescriptor.getClauses(), clausesDescriptor.getGoalClause());

        CookingDescriptor cookingDescriptor = DataLoader.loadCooking(
                Paths.get("examples/cooking_coffee.txt"),
                Paths.get("examples/cooking_coffee_input.txt")
        );
        CookingByRefutation.findRecipes(cookingDescriptor.getClauses(), cookingDescriptor.getUserCommands());

    }


}
