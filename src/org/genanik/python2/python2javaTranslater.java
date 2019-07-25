package org.genanik.python2;

import org.genanik.util.terminal;

public class python2javaTranslater {

    private terminal ter = new terminal();

    public String p2j(String pythonFilePath, String args){
        return ter.runCommand("python " + pythonFilePath + " " + args);
    }
}
