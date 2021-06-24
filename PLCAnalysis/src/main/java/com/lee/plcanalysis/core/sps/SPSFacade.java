package com.lee.plcanalysis.core.sps;

import com.lee.plcanalysis.core.sps.base.Requirement;
import com.lee.plcanalysis.core.sps.model.ObserverGroup;
import com.lee.plcanalysis.core.sps.model.SPS2Obs;
import com.lee.plcanalysis.core.sps.parser.RequirementBuilder;
import com.lee.plcanalysis.core.sps.parser.RequirementGrammarLexer;
import com.lee.plcanalysis.core.sps.parser.RequirementGrammarParser;
import com.lee.plcanalysis.core.sps.parser.ThrowingErrorListener;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor
public class SPSFacade {

    private Lexer lexer;
    private Parser parser;
    private RequirementBuilder builder = null;

    public void parseFile(String filePath) throws SpsParseException, IOException {
        parse(CharStreams.fromFileName(filePath));
    }

    public void parseString(String text) throws SpsParseException{
        parse(CharStreams.fromString(text));
    }

    public void parse(CharStream inStream) throws SpsParseException{
        try{
            lexer = getLexer(inStream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            parser = getParser(tokens);
            parser.removeParseListeners();
            parser.addErrorListener(ThrowingErrorListener.INSTANCE);

            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(getParseTreeListener(), getParseTree());
        }catch (ParseCancellationException e){
            throw new SpsParseException(e.getMessage());
        }
    }

    public Lexer getLexer(CharStream inStream){
        return new RequirementGrammarLexer(inStream);
    }

    public Parser getParser(TokenStream tokens){
        return new RequirementGrammarParser(tokens);
    }

    public ParseTree getParseTree(){
        return ((RequirementGrammarParser)parser).list();
    }

    public ParseTreeListener getParseTreeListener(){
        builder = new RequirementBuilder();
        return builder;
    }

    public List<Requirement> getRequirements(){
        return builder.getContext().getReq();
    }

    public ObserverGroup getObserverGroup(List<List<String>> priorityArray){
        return new SPS2Obs().translate(getRequirements(), priorityArray);
    }
}
