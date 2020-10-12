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
    int length = (int) text.length() / 2 + text.length() % 2;

    for (int i = 0; i < length - 1; i++){
      if (text.charAt(2 * i) == text.charAt(2 * i + 1)){
        text = text.substring(0, 2 * i + 1) + "X" + text.substring(2 * i + 1);
        length = (int) text.length() / 2 + text.length() % 2;
      }
    }

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
        if (first == key[i][j]){
          row = i;
          firstColumn = j;
        }
        if (second == key[i][j]){
          secondColumn = j;
        }
      }
    }
    String ans = "";
    if (row == 4){
      row = -1;
    }
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
        if (first == key[i][j]){
          column = j;
          firstRow = i;
        }
        if (second == key[i][j]){
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

  // regular encode
  // 3. If the letters are different, replace them with the letters on the same row, but in the column of the other letter
  public static String regularEncode(String letterPair, String [][] key){
    String first = "" + letterPair.charAt(0);
    String second = "" + letterPair.charAt(1);
    int firstRow = 0;
    int firstColumn = 0;
    int secondRow = 0;
    int secondColumn = 0;

    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        if (first == key[i][j]){
          firstRow = i;
          firstColumn = j;
        }
        if (second == key[i][j]){
          secondRow = i;
          secondColumn = j;
        }
      }
    }

    String ans = key[firstRow][secondColumn] + key[secondRow][firstColumn];
    return ans;
  }

  public static void main(String [] args){
    String text = args[0];
    String keytext = args[1];

    // position the keytext into an array
    String[][] key = new String[5][5];
    int index = 0;
    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        key[i][j] = "" + keytext.charAt(index);
        index++;
      }
    }

    /* Test for encodePairs
    String [] encodePairsTest = encodePairs(text);
    for (int i = 0; i < encodePairsTest.length; i++){
      System.out.println(encodePairsTest[i]);
    }
    */
  }
}
