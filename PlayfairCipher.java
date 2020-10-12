/***************************
* PLAYFAIR CIPHER
*
* Kaylee Yin
* Cybersecurity, Period 5
***************************/

public class PlayfairCipher{

  // ENCODE
  /*
    1. If the letters are on the same row, use the letters below them to replace them.
    2. If the letters are on the same column, use the letters to their right to replace them.
    3. If the letters are different, replace them with the letters on the same row, but in the column of the other letter
    4. If the letters are the same, insert an X between them.
    verticalEncode(letterPair)
    horizontalEncode(letterPair)
    regularEncode(letterPair)
  */

  /*public static String encode(text){

    for (int i = 0; i < text.length(); i++){
      String s = "" + text.charAt(i);
      if ()
    }
  }*/

  public static String verticalEncode(String letterPair, String[][] key){
    String first = "" + letterPair.charAt(0);
    String second = "" + letterPair.charAt(1);
    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        if (first == key[i][j]){
          int firstRow = i;
          int firstColumn = j;
        }
        if (second == key[i][j]){
          int secondRow = i;
          int secondColumn = j;
        }
      }
    }
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

    // separate into pairs
    int textLen = text.length();
    int len = 0;
    if (textLen % 2 == 0){
      len = textLen / 2;
    }
    else{
      len = (textLen + 1) / 2;
      text += "Z";
    }
    String [] pairs = new String[len];
    int j = 0;
    while (j < len){
      for (int i = 0; i < text.length(); i+=2){
        pairs[j] = "" + text.charAt(i) + text.charAt(i+1);
        j++;
      }
    }
  }

}
