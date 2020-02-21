package persistence;

import java.io.PrintWriter;

//code imported from https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Represents data that can be saved to file
public interface Saveable {
    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
