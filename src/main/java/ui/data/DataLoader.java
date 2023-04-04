package ui.data;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class DataLoader {

    public static List<Clause> loadClauses(Path source) throws IOException {

        Scanner sc = new Scanner(source);
        String line;

        List<Clause> clauses = new ArrayList<>();
        int index = 1;
        while (sc.hasNextLine() && !(line = sc.nextLine().toLowerCase()).isEmpty()) {
            if (line.startsWith("#")) continue;

            clauses.add(new Clause(
                    new HashSet<>(List.of(line.split("\sv\s"))),
                    index++)
            );
        }

        return clauses;
    }

}
