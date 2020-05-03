public class Main {
    public static String encrypt(String input, int key){
        String output = "";
        for(int i = 0; i < input.length(); i++){
            output += (char) (input.charAt(i)+key);
        }
        return output;
    }

    public static String decrypt(String input, int key){
        String output = "";
        for(int i = 0; i < input.length(); i++){
            output += (char) (input.charAt(i)-key);
        }
        return output;
    }

    public static void start(String args[]){
        String mode="enc";
        int key=0;
        String data="";

        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-mode")){
                mode = args[i+1];
            } else if(args[i].equals("-key")){
                key = Integer.parseInt(args[i+1]);
            } else if(args[i].equals("-data")){
                data = args[i+1];
            }
        }

        if(mode.equals("enc")) {
            System.out.println(encrypt(data, key));
        } else if (mode.equals("dec")){
            System.out.println(decrypt(data,key));
        } else
            System.out.println("Unknown operation.");

    }

    public static void main(String[] args) {
        start(args);
    }
}
