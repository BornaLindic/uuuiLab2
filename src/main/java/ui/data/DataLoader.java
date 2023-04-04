package ui.data;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class DataLoader {

    public static ClausesDescriptor loadClauses(Path source) throws IOException {
        Scanner sc = new Scanner(source);
        String line;

        List<Clause> clauses = new ArrayList<>();
        String goalClause = "";

        int index = 1;
        while (sc.hasNextLine() && !(line = sc.nextLine().toLowerCase()).isEmpty()) {
            if (line.startsWith("#")) continue;
            if (!sc.hasNextLine()) goalClause = line;

            clauses.add(new Clause(
                    new HashSet<>(List.of(line.split("\sv\s"))),
                    index++)
            );
        }

        return new ClausesDescriptor(clauses, goalClause);
    }


    public static CookingDescriptor loadCooking(Path sourceClauses, Path sourceUserCommands) throws IOException {
        ClausesDescriptor clausesDescriptor = loadClauses(sourceClauses);
        List<String> userCommands = new ArrayList<>();

        Scanner sc = new Scanner(sourceUserCommands);
        String line = "";

        while (sc.hasNextLine() && !(line = sc.nextLine().toLowerCase()).isEmpty()) {
            if (line.startsWith("#")) continue;
            userCommands.add(line);
        }

        return new CookingDescriptor(clausesDescriptor.getClauses(), userCommands);
    }

}
