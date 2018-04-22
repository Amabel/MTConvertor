import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

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
            } else if (line.contains("=") || line.contains(";")) {
               parsePlainDeclaration(line, varMap, scope);
            } else if (line.equals("}")) {
                scope = null;
            } else if (line.contains("#define")) {
                // parse definition
            }
        }
 
    }
 
    public void printMT() {
        for (Map.Entry<String, VariableMapping> entry : varMap.entrySet()) {
            String line = "";
            String varKey = entry.getKey();
            VariableMapping vm = entry.getValue();
            String scope = vm.getScope();
//            if (!scope.equals(null) || !scope.equals("")) {
//                line += scope + ".";
//            }
            String initValue = vm.getInitValue();
            String varName = vm.getVarName();
            line += varName + "\t" + varKey;
            System.out.println(line);
            
        }
    }
    
    public void generateMTFile() {
        
        String fileName = "output/mapping-table-" + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString().replaceAll(":", "") + ".csv";
        
        File file = new File(fileName);

        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        for (Map.Entry<String, VariableMapping> entry : varMap.entrySet()) {
            String line = "";
            String varKey = entry.getKey();
            VariableMapping vm = entry.getValue();
            String scope = vm.getScope();
//            if (!scope.equals(null) || !scope.equals("")) {
//                line += scope + ".";
//            }
            String initValue = vm.getInitValue();
            String varName = vm.getVarName();
            line += varName + "," + varKey + "\n";
            try {
                FileUtils.write(file, line, "UTF-8", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    private void parseDefinition(String string) {
        
    }
    
    private void parsePlainDeclaration(String string, Map<String, VariableMapping> map, String scope) {
        
        String[] strings = string.trim().split(" ");
        List<String> stringList = new ArrayList<>(Arrays.asList(strings));
        
        String varType = null;
        String varKey = null;
        String initValue = null;
        String varName = null;
        Map<String, String> valueMap = null;
        
        if (stringList.contains("=")) {
            int indexEqual = stringList.indexOf("=");
            if (indexEqual != 2) {
                return;
            }
            
            varType = stringList.get(indexEqual - 2);
            varName = stringList.get(indexEqual - 1);
            initValue = stringList.get(indexEqual + 1);
            if (initValue.charAt(initValue.length() - 1) == ';') {
                initValue = initValue.substring(0, initValue.length() - 1);
            }
            
        } else {
            varType = stringList.get(0);
            varName = stringList.get(1);
            if (varName.charAt(varName.length() - 1) == ';') {
                varName = varName.substring(0, varName.length() - 1);
            }
        }
        
        if (stringList.contains("/*")) {
            int indexComment = stringList.indexOf("/*");
            varKey = stringList.get(indexComment + 1);
        }
        
        VariableMapping vMap = new VariableMapping(scope, varType, varKey, varName, initValue, valueMap);
        
        map.put(varKey, vMap);     
    }
    
    
    
}
