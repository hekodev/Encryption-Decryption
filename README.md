# Encryption-Decryption Project



This program allows the encryption and decryption of messages and texts using two simple algorithms:

- A Shifting algorithm (shift): 
      It shifts each letter by the specified number according to its order in the alphabet in circle.
      It reads an English message and an integer number (key) from the standard input and shifts each letter by the specified number according to its order in the alphabet. 
      If it reaches the end of the alphabet, it start back at the beginning (a follows z) and in reverse for the decryption.   
      
- A Unicode-based algorithm (unicode): 
      It reads an English message and an integer number (key) from the console and replace each character by its corresponding value in the Unicode table for the encryption and back from its value from the Unicode table to its corresponding character.

The program has the ablity to read the data and write the result to a file using a command such as:

java Main -mode enc -in road_to_treasure.txt -out protected.txt -key 5 -alg shift

java Main -key 5 -alg unicode -data "\jqhtrj%yt%m~ujwxpnqq&" -mode dec

with the arguments ...

-mode : determine the program’s mode (enc - encryption, dec - decryption) 

-in/out : to specify the full name of a file to read data and to write the result

-key : to specify an integer key to modify the message

-alg : to specify the necessary algorithm (shift - shifting algorithm, unicode - unicode-based algorithm)

-data : to specify a text or ciphertext to encrypt or decrypt.


Since not all arguments have to be specified, the program reads data from -data or from a file written in the -in argument with the following rules which also work as default settings: 

*If there is no -mode, the program should work in enc mode.

*If there is no -key, the program should consider that key = 0.

*If there is no -data, and there is no -in the program should assume that the data is an empty string.

*If there is no -out argument, the program must print data to the standard output.

*If there are both -data and -in arguments, your program should prefer -data over -in.


The project was compiled using Java 14.0.1 and includes a small presentation video. 

