package LeserSchreiber.RaceCondition;

import java.util.ArrayList;
import java.util.List;

public class InodeTable {
    List<Datei> text = new ArrayList<Datei>();

    public InodeTable(Datei datei, FileDescriptor fd) {
        text.add(fd.getAdress(),datei);
    }

    public getDatei(){

    }
}
