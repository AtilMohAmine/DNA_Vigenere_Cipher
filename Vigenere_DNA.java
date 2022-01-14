package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Vigenere_DNA {

    private HashMap<String, ArrayList<String>> AminoAcidTable;

    Vigenere_DNA() {
        AminoAcidTable = new HashMap<>();

        AminoAcidTable.put("A", new ArrayList<String>() {
            {
                add("GCT");
                add("GCC");
                add("GCA");
                add("GCG");
            }
        });
        AminoAcidTable.put("R", new ArrayList<String>() {
            {
                add("CGT");
                add("CGC");
                add("CGA");
                add("CGG");
                add("AGA");
                add("AGG");
            }
        });
        AminoAcidTable.put("N", new ArrayList<String>() {
            {
                add("AAT");
                add("AAC");
            }
        });
        AminoAcidTable.put("D", new ArrayList<String>() {
            {
                add("GAT");
                add("GAC");
            }
        });
        AminoAcidTable.put("C", new ArrayList<String>() {
            {
                add("TGT");
                add("TGC");
            }
        });
        AminoAcidTable.put("Q", new ArrayList<String>() {
            {
                add("CAA");
                add("CAG");
            }
        });
        AminoAcidTable.put("E", new ArrayList<String>() {
            {
                add("GAA");
                add("GAG");
            }
        });
        AminoAcidTable.put("G", new ArrayList<String>() {
            {
                add("GGT");
                add("GGC");
                add("GGA");
                add("GGG");
            }
        });
        AminoAcidTable.put("H", new ArrayList<String>() {
            {
                add("CAT");
                add("CAC");
            }
        });
        AminoAcidTable.put("M", new ArrayList<String>() {
            {
                add("ATG");
            }
        });
        AminoAcidTable.put("I", new ArrayList<String>() {
            {
                add("ATT");
                add("ATC");
                add("ATA");
            }
        });
        AminoAcidTable.put("L", new ArrayList<String>() {
            {
                add("CTT");
                add("CTC");
                add("CTA");
                add("CTG");
                add("TTA");
                add("TTG");
            }
        });
        AminoAcidTable.put("K", new ArrayList<String>() {
            {
                add("AAA");
                add("AAG");
            }
        });
        AminoAcidTable.put("F", new ArrayList<String>() {
            {
                add("TTT");
                add("TTC");
            }
        });
        AminoAcidTable.put("P", new ArrayList<String>() {
            {
                add("CCT");
                add("CCC");
                add("CCA");
                add("CCG");
            }
        });
        AminoAcidTable.put("S", new ArrayList<String>() {
            {
                add("TCT");
                add("TCC");
                add("TCA");
                add("TCG");
                add("AGT");
                add("AGC");
            }
        });
        AminoAcidTable.put("T", new ArrayList<String>() {
            {
                add("ACT");
                add("ACC");
                add("ACA");
                add("ACG");
            }
        });
        AminoAcidTable.put("W", new ArrayList<String>() {
            {
                add("TGG");
            }
        });
        AminoAcidTable.put("Y", new ArrayList<String>() {
            {
                add("TAT");
                add("TAC");
            }
        });
        AminoAcidTable.put("V", new ArrayList<String>() {
            {
                add("GTT");
                add("GTC");
                add("GTA");
                add("GTG");
            }
        });
        AminoAcidTable.put("Z", new ArrayList<String>() {
            {
                add("TAA");
                add("TGA");
                add("TAG");
            }
        });
    }

    private String TexttoBinary(String msg) {
        String binaryMsg = "";
        while((msg.length() % 3) != 0) {
            msg += "X";
        }
        for(char c : msg.toCharArray()) {
            binaryMsg += String.format("%8s", Integer.toBinaryString((int)c)).replaceAll(" ", "0");
        }
        return binaryMsg;
    }

    private String BinarytoText(String msg) {
        String textMsg = "";
        for(int i = 0; i < msg.length(); i += 8) {
            int charCode = Integer.parseInt(msg.substring(i, i+8), 2);
            textMsg += new Character((char) charCode).toString();
        }
        return textMsg;
    }

    private String BinarytoDNA(String msg) {
        String dnaMsg = "";
        for(int i = 0; i < msg.length(); i+= 2) {
            String base = String.format("%c%c", msg.charAt(i), msg.charAt(i+1));
            switch(base) {
                case "00":
                    dnaMsg += "A";
                    break;
                case "01":
                    dnaMsg += "C";
                    break;
                case "10":
                    dnaMsg += "G";
                    break;
                case "11":
                    dnaMsg += "T";
                    break;
            }
        }
        return dnaMsg;
    }

    private String DNAtoBinary(String msg) {
        String binaryMsg = "";
        for(char base : msg.toCharArray()) {
            switch (base) {
                case 'A':
                    binaryMsg += "00";
                    break;
                case 'C':
                    binaryMsg += "01";
                    break;
                case 'G':
                    binaryMsg += "10";
                    break;
                case 'T':
                    binaryMsg += "11";
                    break;
            }
        }
        return binaryMsg;
    }

    private String DNAtoAminoAcid(String msg) {
        String aminoacidMsg = "";
        for(int i = 0; i < msg.length(); i += 3) {
            String dnaCodons = String.format("%c%c%c", msg.charAt(i), msg.charAt(i+1), msg.charAt(i+2));

            for (String key : AminoAcidTable.keySet()) {
                for(int j = 0; j < AminoAcidTable.get(key).size(); j++) {
                    if(AminoAcidTable.get(key).get(j).equals(dnaCodons))
                        aminoacidMsg += key + (char) (j + 'A');
                }
            }
        }
        return aminoacidMsg;
    }

    private String AminoAcidtoDNA(String msg) {
        String dnaMsg = "";
        for(int i = 0; i < msg.length(); i += 2) {
            dnaMsg += AminoAcidTable.get(msg.substring(i, i+1)).get((int) (msg.charAt(i+1) - 'A'));
        }
        return  dnaMsg;
    }

    private String vigenereEncryption(String msg, String key) {
        String encryptedMsg = "";
        key = key.toUpperCase();
        for(int i = 0; i < msg.length(); i++) {
            encryptedMsg += (char) (((msg.charAt(i) + key.charAt(i % key.length())) % 26) + 'A');
        }
        return encryptedMsg;
    }
    
    private String vigenereDecryption(String msg, String key) {
        String decryptedMsg = "";
        key = key.toUpperCase();
        for(int i = 0; i < msg.length(); i++) {
            decryptedMsg += (char) (((msg.charAt(i) - key.charAt(i % key.length()) + 26) % 26) + 'A');
        }
        return decryptedMsg;
    }

    private String encrypt(String msg, String key) {
        String encryptedMsg = msg;
        System.out.printf("\nStarting encryption message '%s'", msg);
        System.out.printf("\nConverting to binary: %s", (encryptedMsg = TexttoBinary(encryptedMsg)));
        System.out.printf("\nConverting to DNA: %s", (encryptedMsg = BinarytoDNA(encryptedMsg)));
        System.out.printf("\nConverting to Amino-Acid: %s", (encryptedMsg = DNAtoAminoAcid(encryptedMsg)));
        System.out.printf("\nEncrypting with Vigenere Cipher using key '%s': %s", key, (encryptedMsg = vigenereEncryption(encryptedMsg, key)));
        System.out.printf("\nFinal Ciphertext: %s", encryptedMsg);
        return encryptedMsg;
    }

    private String decrypt(String msg, String key) {
        String decryptedMsg = msg;
        System.out.printf("\nStarting decryption message '%s'", msg);
        System.out.printf("\nDecrypting with Vigenere Cipher using key '%s': %s", key, (decryptedMsg = vigenereDecryption(decryptedMsg, key)));
        System.out.printf("\nConverting to DNA: %s", (decryptedMsg = AminoAcidtoDNA(decryptedMsg)));
        System.out.printf("\nConverting to binary: %s", (decryptedMsg = DNAtoBinary(decryptedMsg)));
        System.out.printf("\nConverting to text: %s", (decryptedMsg = BinarytoText(decryptedMsg)));
        System.out.printf("\nFinal text: %s", decryptedMsg);
        return decryptedMsg;
    }

    public static void main(String[] args) {
        Vigenere_DNA VDNA = new Vigenere_DNA();
        String cipherText = VDNA.encrypt("AmiNe23", "aB");
        VDNA.decrypt(cipherText, "aB");
    }
}
