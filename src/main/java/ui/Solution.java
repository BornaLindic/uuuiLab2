package ui;

import ui.algorithms.RefutationResolution;
import ui.data.ClausesDescriptor;
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

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
