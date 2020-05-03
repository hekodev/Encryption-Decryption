import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public Main() {
    }

    public static String encrypt(String input, int key) {
        String output = "";

        for(int i = 0; i < input.length(); ++i) {
            output = output + (char)(input.charAt(i) + key);
        }

        return output;
    }

    public static String decrypt(String input, int key) {
        String output = "";

        for(int i = 0; i < input.length(); ++i) {
            output = output + (char)(input.charAt(i) - key);
        }

        return output;
    }

    public static String selectMode(String mode, String data, int key) {
        if (mode.equals("enc")) {
            return encrypt(data, key);
        } else if (mode.equals("dec")) {
            return decrypt(data, key);
        } else {
            System.out.println("Unknown operation.");
            return "";
        }
    }

    public static String dataIN(String readingPath) {
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

    public static void start(String[] args) {
        String mode = "enc";
        int key = 0;
        String data = "";
        String inFileName = "";
        String outFileName = "";

        for(int i = 0; i < args.length; ++i) {
            if (args[i].equals("-mode")) {
                mode = args[i + 1];
            } else if (args[i].equals("-key")) {
                key = Integer.parseInt(args[i + 1]);
            } else if (args[i].equals("-data")) {
                data = args[i + 1];
            } else if (args[i].equals("-in")) {
                System.out.println(args[i + 1]);
                inFileName = "C:\\Users\\hekod\\Desktop\\INTELLIJ-TEST\\" + args[i + 1];
            } else if (args[i].equals("-out")) {
                System.out.println(args[i + 1]);
                outFileName = "C:\\Users\\hekod\\Desktop\\INTELLIJ-TEST\\" + args[i + 1];
            }
        }

        if (outFileName.equals("")) {
            if (data.equals("") && !inFileName.equals("")) {
                System.out.println(selectMode(mode, dataIN(inFileName), key));
            } else {
                System.out.println(selectMode(mode, data, key));
            }
        } else if (data.equals("") && !inFileName.equals("")) {
            write(selectMode(mode, dataIN(inFileName), key), outFileName);
        } else {
            write(selectMode(mode, data, key), outFileName);
        }

    }

    public static void main(String[] args) {
        start(args);
    }
}