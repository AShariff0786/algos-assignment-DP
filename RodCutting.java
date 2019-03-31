/**
 * Rod cutting problem described in Chapter 15 of textbook
 */
public class RodCutting {
   int findMax(int x, int y) {
      if(x>=y) {
         return x;
      }
      else
         return y;
   }

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
    if (rodLength == 0) {
       return 0;
    }
    int max=-1;
    for(int i=0; i<rodLength; i++) {
       max= findMax(max, lengthPrices[i] + rodCuttingRecur((rodLength-(i+1)), lengthPrices));
    }
    return max;
  }

   public int rodCuttingMemoized(int rodLength, int[] lengthPrices) {
     int [] memoArr= new int[lengthPrices.length + 1];
     for (int i=0; i<= lengthPrices.length; i++) {
        memoArr[i]=-1;
     }
     return rodCuttingMemoized(rodLength, lengthPrices, memoArr);
  }
  
  //Memoized helper function
  public int rodCuttingMemoized(int rodLength, int[] lengthPrices, int[] memoArr) {
     if (memoArr[rodLength] >= 0) {
        return memoArr[rodLength];
     }
     if(rodLength == 0) {
        return 0;
     }
     else {
        int max=-1;
        for(int i=0; i< rodLength; i++) {
           max= findMax(max, lengthPrices[i] + rodCuttingMemoized((rodLength-(i+1)), lengthPrices, memoArr));
        }
        memoArr[rodLength] = max;
        return max;
     }
  }
   
  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
    int[] tempArr = new int[lengthPrices.length+1];
    tempArr[0]=0; //if the rodLength is 0, then this will return the price as 0
    int max;
    for(int i=1; i<=rodLength; i++) {
       max=-1;
       for(int j=0; j<i; j++) {
          max= findMax(max, lengthPrices[j] + tempArr[i-(j+1)]);
       }
       tempArr[i]= max;
    }
    return tempArr[rodLength];
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
