import java.io.FileNotFoundException;

public class Controller {

    private Parser parser;
    
    public void launch() {
        
        parser = new Parser();
        
        String[] fileName = {"example/variables.h", "example/def.h"};
        try {
            this.parser.parse(fileName[0]);
            this.parser.parse(fileName[1]);
//            parser.printDefMap();
            parser.printMT();
            parser.generateMTFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
