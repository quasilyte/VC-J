package vc.lang.impl;

import vc.lang.types.*;
import vc.lang.runtime.*;
import vc.lang.impl.deck.BuiltinDeck;

public class Interpreter extends EvaluationContext {
    public Interpreter(String input) {
	tokenizer.resetWith(input);
    }
    
    public void eval() throws Exception {
	while (tokenizer.hasTokens()) {
	    // Interpreter is passed as EvaluationContext
	    tokenizer.nextToken().eval(this);
	}
	
	System.out.println(stack.toString());
    }
    
    @Override
    public Tokenizer getTokenizer() {
	return tokenizer;
    }

    @Override
    public DataStack getDataStack() {
	return stack;
    }

    @Override
    public BuiltinDeck getBuiltinDeck() {
	return builtins;
    }

    @Override
    public ExecExceptionBuilder exception(String msg) {
	String contextInfo = String.format(
	    "line: %d", tokenizer.line()
	);
	
	return new ExecExceptionBuilder(contextInfo, msg);
    }
}

