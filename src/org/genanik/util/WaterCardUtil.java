package org.genanik.util;

import org.genanik.python2.python2javaTranslater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WaterCardUtil {

    public String noneKeyACard = "thissuid3A08040001A45AF50FDFEF1D0000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000fffffffffffff7878095956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000ffffffffffffff078069956605146902000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000ffffffffffffff078069956605146902";

    public String defaultKeyCard = "thissuid0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFF7878095FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFF078069FFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFF078069FFFFFFFFFFFF";

    public String getUid(){
        terminal ter = new terminal();
        String rawText = ter.runCommand("nfc-anticol");
        String[] textList = rawText.split("\n");
        rawText = textList[textList.length-3].split(":")[1].replaceAll(" ", "");//记录uid，使用rawText是为了节省内存
        System.out.println(rawText);
        return rawText;
    }

    public List<String> getKeys(String uid){
        List<String> keys = new ArrayList();
        List<String> lastByte1 = new ArrayList();
        List<String> lastByte2 = new ArrayList();
        List<String> lastByte3 = new ArrayList();

        lastByte1.add("3");
        lastByte1.add("7");
        lastByte1.add("b");
        lastByte1.add("f");
        lastByte2.add("3");
        lastByte2.add("7");
        lastByte3.add("e");
        lastByte3.add("f");

        //不默认添加KeyB 因为noneKeyACard变量已经写入了KeyB
        //keys.add("956605146902");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < lastByte1.size(); j++) {
                keys.add(98 + uid + i + lastByte1.get(j));
                //System.out.println(98 + uid + i + lastByte1.get(j));
            }
        }
        for (int i = 0; i < lastByte2.size(); i++) {
            keys.add(98 + uid + 3 + lastByte2.get(i));
            //System.out.println(98 + uid + 3 + lastByte2.get(i));
        }
        for (int i = 0; i < lastByte3.size(); i++) {
            keys.add(73 + uid + 0 + lastByte3.get(i));
            //System.out.println(73 + uid + 0 + lastByte3.get(i));
        }
        return keys;
    }

    public List<String> autoGetKeys(){
        List<String> result = getKeys(getUid());
        System.out.println(result);
        return result;
    }

    // 制作带有KeyA和KeyB的mfd文件并写入到运行目录下的keyDump.mfd
    public void makeDump(String uid) throws IOException {
        new readBytes().writeByteFile("keyDump.mfd",makeNewMFD(uid));
    }

    public List<List<List<String>>> makeNewMFDFormated(String uid) throws IOException {
        return formatDumpStr(makeNewMFD(uid));
    }

    public String makeNewMFD(String uid) {
        //初始化
        String dumpStr = noneKeyACard;
        List<String> keys = autoGetKeys();
        //写入 Key A
        for (int i = 0; i < keys.size(); i++) {
            dumpStr = dumpStr.replaceFirst("ffffffffffff", keys.get(i));
            System.out.println("injured " + keys.get(i));
            //System.err.println(dumpStr);
        }
        //写入 uid
        dumpStr = dumpStr.replace("thissuid", uid);
        System.out.println("injured uid");
        //Debug
        //System.out.println(dumpStr);

        return dumpStr;
    }

    public List<List<List<String>>> readDumpFile(String dumpPath) throws IOException {
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

    public List<List<List<String>>> formatDumpStr(String dumpStr) {
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

    public String makeAmountData(String integerData){
        return new python2javaTranslater().p2j("water.py", integerData);
    }

    public List<List<List<String>>> readFullWaterCard(String keysFilePath, String outputName) throws IOException {
        terminal ter = new terminal();
        readBytes readByte = new readBytes();

        System.out.println(ter.runCommand("nfc-mfclassic r b " + outputName + " " + keysFilePath));
        return formatDumpStr(readByte.readByteFile(outputName));
    }

}
