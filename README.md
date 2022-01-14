# DNA-Based Data Encryption Using Vigenere Cipher Implemented in JAVA
The DNA-based cryptography is a new and very promising direction in cryptography research, DNA can be used in cryptography for storing and transmitting the
information.<br><p align="center">
![diagram](https://user-images.githubusercontent.com/86023602/149540315-9750648a-0836-4300-9878-7ba912368084.png)
</p>

## Encryption
#### Step 1: Transfom the text to binary
Each character of the text will be converted to 8-bit binary. But the lenght of the text must be multiple by 3, Because it will be converted to DNA Bases and next to DNA Codons.
This part of code will check that, and add character '**X**' to fill the void:
 ```java
while((msg.length() % 3) != 0) {
            msg += "X";
}
```
> E.g. "Hi" will be "HiX" then will be 01001000 01101001 01011000

#### Step 2: Transform the binary to DNA-Bases
In this step, every couple of two bit will be converted to the one of the DNA Bases(A, C, G, T).
- 00 will be base **A**.
- 01 will be the base **C**.
- 10 will be the base **G**.
- 11 will be the base **T**.

#### Step 3: Transform the DNA-Codons to Amino-Acids
Every DNA Codons will be one of the Amio Acids in the following table with their index. Amino Acids table wil be implemented as<code>HashMap&lt;String, ArrayList&lt;String&gt;&gt;</code>.<br><p align="center">
![tablefinal](https://user-images.githubusercontent.com/86023602/149537586-2484dadf-9a67-410c-b970-09be7e732321.png)
</p>
The index will be used for the decryption, it is the index of the DNA Codon in the table, but it will be converted to letter to be used next in Vigenere cipher. E.g. 0 wil be 'A', 1 wil be 'B'...
This part of code explain how to get the Amino Acid letter code with the index from the HashMap:

```java
for (String key : AminoAcidTable.keySet()) {
    for(int j = 0; j < AminoAcidTable.get(key).size(); j++) {
	if(AminoAcidTable.get(key).get(j).equals(dnaCodons))
  	    aminoacidMsg += key + (char) (j + 'A');
    }
}
```
The STOP Codons will be converted to character '**Z**'.

#### Step 4: Encrypt with Vigenere-Cipher
Vigenere Cipher is a method of encrypting alphabetic text. It uses a simple form of polyalphabetic substitution.
The easy implementation could be to visualize Vigenere algebraically by converting [A-Z] into numbers [0–25].
> Ei = (Pi + Ki) mod 26

The plaintext(**P**) and key(**K**)
This part of code implements the equation:
```java
for(int i = 0; i < msg.length(); i++) 
	encryptedMsg += (char) (((msg.charAt(i) + key.charAt(i % key.length())) % 26) + 'A');
```

## Decryption
#### Step 1: Decrypt with Vigenere-Cipher
The equation bellow explain how to decrypt from ciphertext to plantext using Vigenere Cipher.
> Ei = (Pi - Ki + 26) mod 26

The plaintext(**P**) and key(**K**)

#### Step 2: Transform from Amino-Acid to DNA-Codons
Now it's easy to get the DNA Codos simply by calling<code>AminoAcidTable.get(**Key**)</code>, it will return an<code>ArrayList&lt;String&gt;</code>, then we can easly find the DNA Codon using the index after converting them from letter to number('**A**' will be 0, '**B**' wil be 1....) .

This part of code implements that:
```java
for(int i = 0; i < msg.length(); i += 2) {
	dnaMsg += AminoAcidTable.get(msg.substring(i, i+1)).get((int) (msg.charAt(i+1) - 'A'));
}
```

#### Step 3: Transform from DNA-Codons to Binary
Each DNA Base will be converted to their binary code as followsز
- The base **A** will be 00.
- The base **C** will be 01.
- The base **G** will be 10.
- The base **T** will be 11.

#### Step 4: Transform from Binary to PlainText
The final step is to converting every 8-bit to character.
