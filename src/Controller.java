import java.io.FileNotFoundException;

public class Controller {

    private Parser parser;
    
    
    
    public void launch() {
        
        parser = new Parser();
        
        String fileName = "example/def.h";
        try {
            this.parser.parse(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
}
