package Model;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.util.ElementScanner6;

public class LexicalAutomaton {
    private Map<LexicalState, Token> finalStates;

    public LexicalAutomaton() {
        finalStates = new HashMap<>();
        finalStates.put(LexicalState.Q1, Token.IDENTIFICADOR);
        finalStates.put(LexicalState.Q3, Token.STRING);
        finalStates.put(LexicalState.Q4, Token.INTEIRO);
        finalStates.put(LexicalState.Q7, Token.REAL);
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
                    return LexicalState.INVALIDATION_STATE;
            }  

            case Q1: {
                if ((entry >= 'A' && entry <= 'Z') 
                        || (entry >= 'a' && entry <= 'z') 
                        || (entry >= '0' && entry <= '9'))
                        return LexicalState.Q1;
                else 
                    return LexicalState.INVALIDATION_STATE;
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
                    return LexicalState.INVALIDATION_STATE;
            }

            case Q5: {
                if (entry >= '0' && entry <= '9')
                    return LexicalState.Q4;
                else
                    return LexicalState.INVALIDATION_STATE;
            }

            case Q6: {
                if (entry >= '0' && entry <= '9')
                    return LexicalState.Q7;
                else
                    return LexicalState.INVALIDATION_STATE;
            }

            case Q7: {
                if (entry >= '0' && entry <= '9')
                    return LexicalState.Q7;
                else
                    return LexicalState.INVALIDATION_STATE;
            }

            default:
                return LexicalState.INVALIDATION_STATE;
        }
    }

    public Token evaluate(String str) {
        LexicalState state = LexicalState.INITIAL;
        for (char c : str.toCharArray()) {
            state = changeState(state, c);
        }
        return finalStates.getOrDefault(state, Token.INVALID);
    }
}
