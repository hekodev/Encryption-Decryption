import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


abstract class Algorithm {

    Algorithm(String operationType, int key, String data, String outFileName){
        switch (operationType) {
            case "enc":
                encrypt(key, data, outFileName);
                break;
            case "dec":
                decrypt(key, data, outFileName);
                break;
        }
    }

    abstract void encrypt(int key, String data, String outFileName);
    abstract void decrypt(int key, String data, String outFileName);
}

class ShiftAlgorithm extends Algorithm {

    ShiftAlgorithm(String operationType, int key, String data, String outFileName){
        super(operationType, key, data, outFileName);
    }

    @Override
    void encrypt(int key, String data, String outFileName) {
        StringBuilder result = new StringBuilder();
        for (char character : data.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + key) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        ProcessingData.output(result.toString(),outFileName);
    }

    @Override
    void decrypt(int key, String data, String outFileName) {
        encrypt(26 - (key % 26), data, outFileName);
    }
}

class UnicodeAlgorithm extends Algorithm {

    UnicodeAlgorithm(String operationType, int key, String data, String outFileName){
        super(operationType, key, data, outFileName);
    }

    @Override
    void encrypt(int key, String data, String outFileName) {
        String output = "";

        for(int i = 0; i < data.length(); ++i) {
            output += (char)(data.charAt(i) + key);
        }
        ProcessingData.output(output,outFileName);

    }

    @Override
    void decrypt(int key, String data, String outFileName) {
        String output = "";

        for(int i = 0; i < data.length(); ++i) {
            output += (char)(data.charAt(i) - key);
        }

        ProcessingData.output(output,outFileName);
    }
}


class AlgorithmFactory {

    public  static void startProgram(String algoType, String operationType, int key, String data, String outFileName){

        if (!operationType.equals("enc") && !operationType.equals("dec")) {
            System.out.println("Unknown operation.");
        } else {
            switch (algoType) {
                case "shift":
                    new ShiftAlgorithm(operationType, key, data, outFileName);
                    break;
                case "unicode":
                    new UnicodeAlgorithm(operationType, key, data, outFileName);
                    break;
            }
        }
    }
}

class ProcessingData {

    public static void output(String data, String outputPath){
        if(outputPath.equals("")){
            System.out.println(data);
        } else {
            ProcessingData.write(data,outputPath);
        }
    }

    public static String read(String readingPath) {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(readingPath)));
        } catch (IOException var3) {
            System.out.println("Cannot read file: " + var3.getMessage());
        }

        return content;
    }

    public static void write(String data, String path) {
        File file = new File(path);

        try {
            PrintWriter printWriter = new PrintWriter(file);

            try {
                printWriter.print(data);
            } catch (Throwable var7) {
                try {
                    printWriter.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            printWriter.close();
        } catch (IOException var8) {
            System.out.printf("An exception occurs %s", var8.getMessage());
        }

    }
}


public class Main {

    public static void main(String[] args){
        String alg = "shift";
        String mod = "enc";
        int key = 0;
        String data = "";
        String inFileName = "";
        String outFileName = "";

        for(int i = 0; i < args.length; ++i) {
            if (args[i].equals("-alg")){
                alg = args[i + 1];
            }else if (args[i].equals("-mode")) {
                mod = args[i + 1];
            } else if (args[i].equals("-key")) {
                key = Integer.parseInt(args[i + 1]);
            } else if (args[i].equals("-data")) {
                data = args[i + 1];
            } else if (args[i].equals("-in")) {
                inFileName = "C:\\Users\\hekod\\Desktop\\INTELLIJ-TEST\\" + args[i + 1];
            } else if (args[i].equals("-out")) {
                outFileName = "C:\\Users\\hekod\\Desktop\\INTELLIJ-TEST\\" + args[i + 1];
            }
        }

        if (data.equals("") && !inFileName.equals("")) {
            data = ProcessingData.read(inFileName);
        }

        AlgorithmFactory.startProgram(alg, mod, key, data, outFileName);
    }
}
