package LeserSchreiber.RaceCondition;

public class FileTable {
    String[] ft = {"read","write","read-write"};
    InodeTable it;
    Datei datei;
    public FileTable(InodeTable it){
        this.it = it;
    }
    public void set(int id){

    }
}
