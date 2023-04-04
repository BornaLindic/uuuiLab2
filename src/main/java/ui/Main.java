package ui;

import ui.data.DataLoader;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println(DataLoader.loadClauses(Paths.get("examples/resolution_small_example_3.txt")));

    }


}
