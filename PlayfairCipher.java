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

  // vertical and horizontal encode
  public static String vhEncode(String letterPair, String[][] key){
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
    String ans = "";
    if (firstRow == secondRow){
      if (firstRow == 4){
        firstRow = -1;
        secondRow = -1;
      }
      ans = key[firstRow + 1][firstColumn] + key[secondRow + 1][secondColumn];
    }
    if (firstColumn == secondColumn){
      if (firstColumn == 4){
        firstColumn = -1;
        secondColumn = -1;
      }
        ans = key[firstRow][firstColumn + 1] + key[secondRow][secondColumn + 1];
    }
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
