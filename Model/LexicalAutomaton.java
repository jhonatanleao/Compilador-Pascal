package Model;

import java.util.HashMap;
import java.util.Map;

public class LexicalAutomaton {
    private Map<LexicalState, Token> finalStates;

    public LexicalAutomaton() {
        finalStates = new HashMap<>();
        finalStates.put(LexicalState.Q1, Token.IDENTIFICADOR);
        finalStates.put(LexicalState.Q3, Token.STRING);
        finalStates.put(LexicalState.Q4, Token.INTEIRO);
        finalStates.put(LexicalState.Q7, Token.REAL);
        finalStates.put(LexicalState.BEGIN_INVALID, Token.BEGIN_INVALID);
        finalStates.put(LexicalState.INVALID_CARACTERE, Token.INVALID_CARACTERE);
    }

    private LexicalState changeState(LexicalState currentState, char entry) {
        switch (currentState) {
            case INITIAL: {
                if ((entry >= 'A' && entry <= 'Z') || (entry >= 'a' && entry <= 'z'))
                    return LexicalState.Q1;
                else if (entry == '"')
                    return LexicalState.Q2;
                else if (entry >= '0' && entry <= '9')
                    return LexicalState.Q4;
                else if (entry == '+' || entry == '-')
                    return LexicalState.Q5;
                else
                    return LexicalState.BEGIN_INVALID;
            }  

            case Q1: {
                if ((entry >= 'A' && entry <= 'Z') 
                        || (entry >= 'a' && entry <= 'z') 
                        || (entry >= '0' && entry <= '9'))
                        return LexicalState.Q1;
                else 
                    return LexicalState.INVALID_CARACTERE;
            }
            
            case Q2: {
                if (entry == '"')
                    return LexicalState.Q3;
                else
                    return LexicalState.Q2;
            }
                
            case Q4: {
                if (entry >= '0' && entry <= '9')
                    return LexicalState.Q4;
                else if (entry == '.')
                    return LexicalState.Q6;
                else 
                    return LexicalState.BEGIN_INVALID;
            }

            case Q5: {
                if (entry >= '0' && entry <= '9')
                    return LexicalState.Q4;
                else
                    return LexicalState.BEGIN_INVALID;
            }

            case Q6: {
                if (entry >= '0' && entry <= '9')
                    return LexicalState.Q7;
                else
                    return LexicalState.BEGIN_INVALID;
            }

            case Q7: {
                if (entry >= '0' && entry <= '9')
                    return LexicalState.Q7;
                else
                    return LexicalState.BEGIN_INVALID;
            }

            default:
                return LexicalState.INVALID_CARACTERE;
        }
    }

    public Token evaluate(String str) {
        LexicalState state = LexicalState.INITIAL;
        for (char c : str.toCharArray()) {
            state = changeState(state, c);
            if(state == LexicalState.INVALID_CARACTERE ||state == LexicalState.BEGIN_INVALID){
                return finalStates.getOrDefault(state, Token.INVALID_CARACTERE);
            }
        }
        return finalStates.getOrDefault(state, Token.INVALID_CARACTERE);
    }
}
