import java.util.Map;

public class VariableMapping {

    private String scope;
    private String key;
    private String varName;
    private String initValue;
    private Map<String, String> valueMap; // valueName, value
    
    public VariableMapping(String scope, String key, String varName, String initValue, Map<String, String> valueMap) {
        super();
        this.scope = scope;
        this.key = key;
        this.varName = varName;
        this.initValue = initValue;
        this.valueMap = valueMap;
    }
    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }
    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }
    /**
     * @return the varName
     */
    public String getVarName() {
        return varName;
    }
    /**
     * @return the initValue
     */
    public String getInitValue() {
        return initValue;
    }
    /**
     * @return the valueName
     */
    public Map<String, String> getValueMap() {
        return valueMap;
    }
    
    @Override
    public String toString() {
        return "Scope: " + this.scope + ", key: " + this.key + ", varName: " + this.varName + ", initValue: " + this.initValue;
    }
    
}
