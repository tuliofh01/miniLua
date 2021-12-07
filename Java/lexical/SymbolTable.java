package lexical;

import java.util.Map;
import java.util.HashMap;

public class SymbolTable {

    private Map<String, TokenType> st;

    public SymbolTable() {
        st = new HashMap<String, TokenType>();

        // SYMBOLS

        // OPERATORS

        // KEYWORDS
    }

    public boolean contains(String token) {
        return st.containsKey(token);
    }

    public TokenType find(String token) {
        return this.contains(token) ? st.get(token) : TokenType.ID;
    }
}
