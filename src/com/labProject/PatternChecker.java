package com.labProject;
import java.util.regex.*;
interface VerifyPattern{
    boolean check(String s);
}
public class PatternChecker {
    VerifyPattern isFloat(){
        VerifyPattern verifier = (String s)->{
        Pattern p = Pattern.compile("[0-9]+[.]?[0-9]+");
        Matcher m = p.matcher(s);
        return m.matches();};
        return verifier;
    }
    VerifyPattern isInt(){
        VerifyPattern verifier = (String s)->{
            Pattern p = Pattern.compile("[0-9]+");
            Matcher m = p.matcher(s);
            return m.matches();};
        return verifier;
    }
    VerifyPattern mno(){
        VerifyPattern verifier = (String s)->{
            Pattern p = Pattern.compile("(91|0)?[7-9][0-9]{9}");
            Matcher m = p.matcher(s);
            return m.matches();};
        return verifier;
    }

    VerifyPattern ItemID(){
        VerifyPattern verifier = (String s)->{
            Pattern p = Pattern.compile("IT[0-9]+");
            Matcher m = p.matcher(s);
            return m.matches();};
        return verifier;
    }
    VerifyPattern ConsumerID(){
        VerifyPattern verifier = (String s)->{
            Pattern p = Pattern.compile("CONS[0-9]+");
            Matcher m = p.matcher(s);
            return m.matches();};
        return verifier;
    }
    VerifyPattern ProducerID(){
        VerifyPattern verifier = (String s)->{
            Pattern p = Pattern.compile("PROD[0-9]+");
            Matcher m = p.matcher(s);
            return m.matches();};
        return verifier;
    }
    VerifyPattern password(){
        VerifyPattern verifier = (String s)->{
            Pattern p = Pattern.compile("[A-Za-z0-9@#!&]{8,}");
            Matcher m = p.matcher(s);
            return m.matches();};
        return verifier;
    }
}
