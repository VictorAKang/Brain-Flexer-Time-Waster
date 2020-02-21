package persistence;

import java.io.File;
import java.io.IOException;

//represents readers that interpret saved data
public interface Reader {

    //EFFECTS: reads the file and return correspondent Saveable
    public Saveable read(File file) throws IOException;
}
