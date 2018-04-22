import java.util.Map;

public class VariableMapping {

    private String scope;
    private String varType;
    private String key;
    private String varName;
    private String initValue;
    private Map<String, String> valueMap; // valueName, value
    
    public VariableMapping(String scope, String varType, String key, String varName, String initValue, Map<String, String> valueMap) {
        super();
        this.scope = scope;
        this.key = key;
        this.varName = varName;
        this.initValue = initValue;
        this.valueMap = valueMap;
    }

    public String getScope() {
        return scope;
    }

    public String getKey() {
        return key;
    }

    public String getVarName() {
        return varName;
    }

    public String getInitValue() {
        return initValue;
    }

    public String getVarType() {
        return varType;
    }

    public Map<String, String> getValueMap() {
        return valueMap;
    }
    
    @Override
    public String toString() {
        return "Scope: " + this.scope + ", key: " + this.key + ", varName: " + this.varName + ", initValue: " + this.initValue;
    }
    
}
