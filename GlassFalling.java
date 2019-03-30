/**
 * Glass Falling
 */
public class GlassFalling {
   int findMax(int x, int y) {
      if(x>=y)
         return x;
      else 
         return y;
   }

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
    // Fill in here and change the return
     //If we have one or less floors to drop from, there is only one trial. 
     //If we only have one pane of glass, we have to test all the floors to get an accurate result.
     if(floors <= 1 || sheets ==1) {
        return floors;
     }
     int min= floors; //worst case of the minimum number of trial is trying each floor
     int result;
     for(int i=1; i<=floors; i++ ) {
        //on each floor there are two possibilities: glass breaks, so we check all the floors below, or glass survives so we check all the floors above
        //we don't know which possibility will occur
        //so we must find the max of the two possibilities, since the worst case will be the possibility with the most trials
        //so minimum number of trials will be the possibility that requires the most trails
        //we add 1 because the floor were testing on counts as one trial
        result = findMax (glassFallingRecur((i-1), (sheets-1)), glassFallingRecur((floors-i), (sheets))) + 1;
        //update the overall minimum number of trials by comparing the current minimum of trials of the current i-th floor to the overall minimum 
        if(result < min)
           min=result;
     }
    return min;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int floors, int sheets) {
    // Fill in here and change the return
    int result;

    return 0;
  }  
  
  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
    // Fill in here and change the return
     int [][] memoArr=new int[floors + 1][sheets+1]; 
     int result;
     //if there is only one glass, then we need to test on every floor
     for(int i=1; i<= floors; i++) {
        memoArr[i][1]=i;
     }
     //if there is only 1 floor, minimum number of trials is 1
     for(int i=1; i<sheets; i++) {
        memoArr[1][i]=1;
     }
     for(int i=2; i<=floors; i++) {
       for(int j=2; j<=sheets; j++) {
          memoArr[i][j]=floors;//Initialize the minimum number of trial for the sub problem (i,j) to the number of floors
          //Finds the minimum number of trials for the subproblem (i,j)
          for(int k=1; k<=i; k++ ) {
             result = findMax (memoArr[k-1][j-1], memoArr[i-k][j]) + 1;//find worst case between the subproblem where egg breaks and where the egg doesn't break
             //if the current minimum number of trials at floor k is less than the current minimum for our subproblem, update the subproblem
             if(result < memoArr[i][j])
                memoArr[i][j]=result;
          }
       }
     }
     return memoArr[floors][sheets];
  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Recur = gf.glassFallingRecur(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Recur + " " + minTrials2Bottom);
  }
}

