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
    for (int i = 0; i < text.length() - 1; i += 2){
      if (text.charAt(i) == text.charAt(i+1)){
        text = text.substring(0, i + 1) + "X" + text.substring(i + 1);
      }
    }

    // decides if the number of letters is even or odd; odd -> add Z at end
    int size = text.length();
    if (size % 2 != 0){
      size++;
      text += "Z";
    }

    // create an array holding the pairs of letters
    String [] pairs = new String[size / 2];
    int index = 0;
    for (int i = 0; i < size / 2; i++){
      pairs[i] = text.substring(index, index + 2); // ensure only pairs of letters
      index += 2;
    }

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

    if (row == 4){ // wrap around if the row is the last row
      row = -1;
    }

    String ans = "";
    ans = key[row + 1][firstColumn] + key[row + 1][secondColumn]; // row changes
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
    if (column == 4){ // wrap around if the column is the last column
      column = -1;
    }

    ans = key[firstRow][column + 1] + key[secondRow][column + 1]; // column changes
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

    String ans = key[firstRow][secondColumn] + key[secondRow][firstColumn]; // columns switch
    return ans;
  }

  public static String encode(String letterPair, String [][] key){
    String first = "" + letterPair.charAt(0);
    String second = "" + letterPair.charAt(1);
    int firstRow = 0;
    int firstColumn = 0;
    int secondRow = 0;
    int secondColumn = 0;

    // determine if the rows or columns are equal to each other to choose method
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
      index++;
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
    ans = key[row - 1][firstColumn] + key[row - 1][secondColumn]; // row changes
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

    ans = key[firstRow][column - 1] + key[secondRow][column - 1]; // column changes
    return ans;
  }

  public static String decode(String letterPair, String [][] key){
    String first = "" + letterPair.charAt(0);
    String second = "" + letterPair.charAt(1);
    int firstRow = 0;
    int firstColumn = 0;
    int secondRow = 0;
    int secondColumn = 0;

    // determine if the rows or columns are equal to each other to choose method
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
    String method = args[0];
    String text = args[1];
    String keytext = args[2];

    text = text.toUpperCase();
    String newText = "";

    for (int i = 0; i < text.length(); i++){
      String chr = "" + text.charAt(i); // current character
      if (!chr.equals("J")){
        newText += chr;
      }
      else{
        newText += "I"; // if character is "J", becomes "I"
      }
    }

    // position the keytext into an array
    String[][] key = new String[5][5]; // array of 5x5
    int index = 0; // keep track of number of each letter in the word
    for (int i = 0; i < 5; i++){ // 5 rows
      for (int j = 0; j < 5; j++){ // 5 columns
        key[i][j] = "" + keytext.charAt(index);
        index++;
      }
    }

    if (method.equals("encode")){
      String [] encodedPairs = encodePairs(newText);
      for (int i = 0; i < encodedPairs.length; i++){
        System.out.print(encode(encodedPairs[i], key));
      }
      System.out.println();
    }
    else if (method.equals("decode")){
      String [] decodedPairs = decodePairs(newText);
      for (int i = 0; i < decodedPairs.length; i++){
        if (decodedPairs[i] != null){
          System.out.print(decode(decodedPairs[i], key));
        }
      }
      System.out.println();
    }
    else{
      System.out.println("Choose one of the valid options: encode or decode");
    }

    /* // Check if the letters are properly divided into pairs
    String [] encodePairss = encodePairs(text);
    for (int i = 0; i < encodePairss.length; i++){
      System.out.println(encodePairss[i]);
    }
    System.out.println();*/

    /*// encodeMethod Test
    String [] encodePairsTest = encodePairs(newText);
    for (int i = 0; i < encodePairsTest.length; i++){
      System.out.print(encodeMethod(encodePairsTest[i], key));
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
