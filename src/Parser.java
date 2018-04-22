import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Parser {
    
    Map<String, VariableMapping> varMap;
    Map<String, String> defMap;
    
    public Parser() {
        this.varMap = new HashMap<>();
        this.defMap = new HashMap<>();
    }
    
    public void parse(String fileName) throws FileNotFoundException {
        
        String scope = null;
        
        File file = new File(fileName);
        

        Scanner sc = new Scanner(file);
        
        while (sc.hasNextLine()) {
            
            String line = sc.nextLine();
            
            if (line.contains("typedef")) {
                String[] strings = line.split(" ");
                scope = strings[1];
                if (scope.charAt(scope.length() - 1) == '{') {
                    scope = scope.substring(0, scope.length() - 1);
                }
            } else if (line.contains("=")) {
               parsePlainDeclaration(line, varMap, scope);
            } else if (line.equals("}")) {
                scope = null;
            } else if (line.contains("#define")) {
                // parse definition
            }
        }
 
        
    }
    
    public void parseDefinition(String string) {
        
    }
    
    public void parsePlainDeclaration(String string, Map<String, VariableMapping> map, String scope) {
        
        String[] strings = string.split(" ");
        List<String> stringList = new ArrayList<>(Arrays.asList(strings));
        
        String varType = null;
        String varKey = null;
        String initValue = null;
        String varName = null;
        Map<String, String> valueMap = null;
        
        if (stringList.contains("=")) {
            int indexEqual = stringList.indexOf("=");
            varType = stringList.get(indexEqual - 2);
            varName = stringList.get(indexEqual - 1);
            initValue = stringList.get(indexEqual + 1);
            if (initValue.charAt(initValue.length() - 1) == ';') {
                initValue = initValue.substring(0, initValue.length() - 1);
            }
            
            if (stringList.contains("/*")) {
                int indexComment = stringList.indexOf("/*");
                varKey = stringList.get(indexComment + 1);
            }
        }
        
        VariableMapping vMap = new VariableMapping(scope, varType, varKey, varName, initValue, valueMap);
        
        System.out.println(vMap);
        
        map.put(varKey, vMap);        
    }
    
}
