package org.genanik.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class nfcTools {

    public String getUid(){
        terminal ter = new terminal();
        String rawText = ter.runCommand("nfc-anticol");
        String[] textList = rawText.split("\n");
        return textList[textList.length-3].split(":")[1].replaceAll(" ", "");
    }

    public List<List<List<String>>> readDump(String dumpPath) throws IOException {
        readBytes bytesTools = new readBytes();
        String orgText = bytesTools.readByteFile(dumpPath);

        List<String> block = new ArrayList<>();
        List<List<String>> sectors = new ArrayList<>();
        List<List<List<String>>> card = new ArrayList<>();

        for (int locate = 0; locate < 2048; ) {
            for (int j = 0; j < 4; j++) {
                block.add(orgText.substring(locate, locate+6*2));
                locate += 6*2;
                block.add(orgText.substring(locate, locate+4*2));
                locate += 4*2;
                block.add(orgText.substring(locate, locate+6*2));
                locate += 6*2;
                sectors.add(block);
                block = new ArrayList<>();
            }
            card.add(sectors);
            sectors = new ArrayList<>();
        }
        return card;
    }

    public List<List<List<String>>> formatDump(String dumpStr) throws IOException {
        String orgText = dumpStr;
        List<String> block = new ArrayList<>();
        List<List<String>> sectors = new ArrayList<>();
        List<List<List<String>>> card = new ArrayList<>();

        for (int locate = 0; locate < 2048; ) {
            for (int j = 0; j < 4; j++) {
                block.add(orgText.substring(locate, locate+6*2));
                locate += 6*2;
                block.add(orgText.substring(locate, locate+4*2));
                locate += 4*2;
                block.add(orgText.substring(locate, locate+6*2));
                locate += 6*2;
                sectors.add(block);
                block = new ArrayList<>();
            }
            card.add(sectors);
            sectors = new ArrayList<>();
        }
        return card;
    }

}
