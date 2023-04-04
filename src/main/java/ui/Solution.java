package ui;

import ui.algorithms.CookingByRefutation;
import ui.algorithms.RefutationResolution;
import ui.data.ClausesDescriptor;
import ui.data.CookingDescriptor;
import ui.data.DataLoader;

import java.io.IOException;
import java.nio.file.Paths;

public class Solution {

	public static void main(String ... args) {

		try {
			if (args[0].equals("resolution")) {
				ClausesDescriptor clausesDescriptor = DataLoader.loadClauses(Paths.get(args[1]));

				RefutationResolution rf = new RefutationResolution();
				rf.propositionalLogicResolution(clausesDescriptor.getClauses(), clausesDescriptor.getGoalClause());
			}

			if (args[0].equals("cooking")) {
				CookingDescriptor cookingDescriptor = DataLoader.loadCooking(
						Paths.get(args[1]),
						Paths.get(args[2])
				);
				CookingByRefutation.findRecipes(cookingDescriptor.getClauses(), cookingDescriptor.getUserCommands());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
