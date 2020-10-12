/***************************
* PLAYFAIR CIPHER
*
* Kaylee Yin
* Cybersecurity, Period 5
***************************/

public class PlayfairCipher{

  /*==================================ENCODE==================================*/
  // works to create pairs of letters for encode
  public static String[] encodePairs(String text){
    // deals with double letters
    /*int length = (int) text.length() / 2 + text.length() % 2;

    for (int i = 0; i < length - 1; i++){
      if (text.charAt(2 * i) == text.charAt(2 * i + 1)){
        text = text.substring(0, 2 * i + 1) + "X" + text.substring(2 * i + 1);
        length = (int) text.length() / 2 + text.length() % 2;
      }
    }*/
    for (int i = 0; i < text.length() - 1; i += 2){
      if (text.charAt(i) == text.charAt(i+1)){
        text = text.substring(0, i + 1) + "X" + text.substring(i + 1);
      }
    }

    int size = text.length();
    if (size % 2 != 0){
      size++;
      text += "Z";
    }

    String [] pairs = new String[size / 2];
    int index = 0;
    for (int i = 0; i < size / 2; i++){
      pairs[i] = text.substring(index, index + 2);
      index += 2;
    }


    /*if (text.charAt(2 * (text.length() - 2)) == text.charAt(2 * (text.length() - 2) + 1)){
      text = text.substring(2 * (text.length() - 2) + 1) + "X" + text.charAt(text.length() - 1);
    }*/

    /*int length = text.length();
    int a = 0;
    while (a < length){
    //for (int i = 0; i < length-1; i++){
      if (text.charAt(a) == text.charAt(a+1)){
        text = text.substring(0, a) + "X" + text.substring(a);
        length = text.length();
      }
    }*/
    // separate the letters in the word into pairs
    //int textLen = text.length(); // length of text
    //int len = text.length(); // used for the length of array

    /*if (textLen % 2 == 0){ // all letters are put into a pair
      len = textLen / 2;
    }
    else{
      len = (textLen + 1) / 2;
      text += "X"; // odd number of letters -> X at the end
    }*/

    /*String [] pairs = new String[len];
    int j = 0;
    for (int i = 0; i < text.length(); i += 2){
      pairs[j] = "" + text.charAt(i) + text.charAt(i+1);
      j++;
    }*/
    /* ORIGINAL
    // separate the letters in the word into pairs
    int textLen = text.length(); // length of text
    int len = 0; // used for the length of array

    if (textLen % 2 == 0){ // all letters are put into a pair
      len = textLen / 2;
    }
    else{
      len = (textLen + 1) / 2;
      text += "X"; // odd number of letters -> X at the end
    }

    String [] pairs = new String[len];
    int j = 0;
    for (int i = 0; i < text.length(); i += 2){
      pairs[j] = "" + text.charAt(i) + text.charAt(i+1);
      j++;
    }*/
    return pairs;
  }

  public static String verticalEncode(String letterPair, String[][] key){
    String first = "" + letterPair.charAt(0);
    String second = "" + letterPair.charAt(1);
    int row = 0;
    int firstColumn = 0;
    int secondColumn = 0;

    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        if (first.equals(key[i][j])){
          row = i;
          firstColumn = j;
        }
        if (second.equals(key[i][j])){
          secondColumn = j;
        }
      }
    }

    if (row == 4){
      row = -1;
    }

    String ans = "";
    ans = key[row + 1][firstColumn] + key[row + 1][secondColumn];
    return ans;
  }

  public static String horizontalEncode(String letterPair, String[][] key){
    String first = "" + letterPair.charAt(0);
    String second = "" + letterPair.charAt(1);
    int column = 0;
    int firstRow = 0;
    int secondRow = 0;

    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        if (first.equals(key[i][j])){
          column = j;
          firstRow = i;
        }
        if (second.equals(key[i][j])){
          secondRow = i;
        }
      }
    }
    String ans = "";
    if (column == 4){
      column = -1;
    }

    ans = key[firstRow][column + 1] + key[secondRow][column + 1];
    return ans;
  }

  public static String regular(String letterPair, String [][] key){
    String first = "" + letterPair.charAt(0);
    String second = "" + letterPair.charAt(1);
    int firstRow = 0;
    int firstColumn = 0;
    int secondRow = 0;
    int secondColumn = 0;

    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        if (first.equals(key[i][j])){
          firstRow = i;
          firstColumn = j;
        }
        if (second.equals(key[i][j])){
          secondRow = i;
          secondColumn = j;
        }
      }
    }

    String ans = key[firstRow][secondColumn] + key[secondRow][firstColumn];
    return ans;
  }

  public static String encodeMethod(String letterPair, String [][] key){
    String first = "" + letterPair.charAt(0);
    String second = "" + letterPair.charAt(1);
    int firstRow = 0;
    int firstColumn = 0;
    int secondRow = 0;
    int secondColumn = 0;

    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        if (first.equals(key[i][j])){
          firstRow = i;
          firstColumn = j;
        }
        if (second.equals(key[i][j])){
          secondRow = i;
          secondColumn = j;
        }
      }
    }

    if (firstRow == secondRow) return verticalEncode(letterPair, key);
    else if (firstColumn == secondColumn) return horizontalEncode(letterPair, key);
    else return regular(letterPair, key);
  }

  /*==================================DECODE==================================*/
  public static String [] decodePairs(String text){
    int textLength = text.length();
    String [] pairs = new String[textLength / 2];

    int index = 0;
    for (int i = 0; i < textLength; i+=2){
      pairs[index] = "" + text.charAt(i) + text.charAt(i+1);
    }

    return pairs;
  }


  public static String verticalDecode(String letterPair, String[][] key){
    String first = "" + letterPair.charAt(0);
    String second = "" + letterPair.charAt(1);
    int row = 0;
    int firstColumn = 0;
    int secondColumn = 0;

    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        if (first.equals(key[i][j])){
          row = i;
          firstColumn = j;
        }
        if (second.equals(key[i][j])){
          secondColumn = j;
        }
      }
    }

    if (row == 0){
      row = 5;
    }

    String ans = "";
    ans = key[row - 1][firstColumn] + key[row - 1][secondColumn];
    return ans;
  }

  public static String horizontalDecode(String letterPair, String[][] key){
    String first = "" + letterPair.charAt(0);
    String second = "" + letterPair.charAt(1);
    int column = 0;
    int firstRow = 0;
    int secondRow = 0;

    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        if (first.equals(key[i][j])){
          column = j;
          firstRow = i;
        }
        if (second.equals(key[i][j])){
          secondRow = i;
        }
      }
    }
    String ans = "";
    if (column == 0){
      column = 5;
    }

    ans = key[firstRow][column - 1] + key[secondRow][column - 1];
    return ans;
  }

  public static String decodeMethod(String letterPair, String [][] key){
    String first = "" + letterPair.charAt(0);
    String second = "" + letterPair.charAt(1);
    int firstRow = 0;
    int firstColumn = 0;
    int secondRow = 0;
    int secondColumn = 0;

    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        if (first.equals(key[i][j])){
          firstRow = i;
          firstColumn = j;
        }
        if (second.equals(key[i][j])){
          secondRow = i;
          secondColumn = j;
        }
      }
    }

    if (firstRow == secondRow) return verticalDecode(letterPair, key);
    else if (firstColumn == secondColumn) return horizontalDecode(letterPair, key);
    else return regular(letterPair, key);
  }

  public static void main(String [] args){
    String text = args[0];
    String keytext = args[1];

    text = text.toUpperCase();
    String newText = "";
    for (int i = 0; i < text.length(); i++){
      String chr = "" + text.charAt(i);
      if (!chr.equals("J")){
        newText += chr;
      }
      else{
        newText += "I";
      }
    }

    // position the keytext into an array
    String[][] key = new String[5][5];
    int index = 0;
    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        key[i][j] = "" + keytext.charAt(index);
        index++;
      }
    }

    // encodeMethod Test -- GOOD
    String [] encodePairsTest = encodePairs(newText);
    for (int i = 0; i < encodePairsTest.length; i++){
        System.out.print(encodeMethod(encodePairsTest[i], key));
    }
    System.out.println();

    /* check if pairs are proper
    String [] encodePairss = encodePairs(text);
    for (int i = 0; i < encodePairss.length; i++){
      System.out.println(encodePairss[i]);
    }
    System.out.println();*/

    // decodeMethod Test

    /*String [] decodePairsTest = encodePairs(text);
    for (int i = 0; i < decodePairsTest.length; i++){
      System.out.print(decodeMethod(decodePairsTest[i], key));
    }
    System.out.println();*/
  }
}
