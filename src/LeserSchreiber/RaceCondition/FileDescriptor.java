package LeserSchreiber.RaceCondition;

import java.util.ArrayList;
import java.util.List;

public class FileDescriptor {
    FileTable fileTable;


    public FileDescriptor(FileTable fileTable) {
        this.fileTable = fileTable;
    }

    public void fd(int id){
        fileTable.set(id);
    }
}
