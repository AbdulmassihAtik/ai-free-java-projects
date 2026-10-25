import java.util.BitSet;

class racamans{
	public static void main(String[] args){
    		boolLimit();
  	}

  public static void boolLimit(){
    int n = Integer.MAX_VALUE-2;
    boolean[] num = new boolean[n];
    num[0] = true;
    String out = "";
    int pointer = 0;
    int small = 0;
    int i = 1;
    while(i<n){
      if(pointer-i > 0 && !num[pointer-i]){
        pointer -=i;
        num[pointer] = true;
      } else {
        if(pointer+i < n && pointer+i > 0){
          pointer +=i;
          num[pointer] = true;
        } else {
          break;
        }
        if(num[small] == true){
          while(num[small] == true){
            small++;
          }
          System.out.println(i-1+" : "+small);
        }
      //out += pointer+", ";
      }
    //System.out.print(pointer+", ");
      i++;
    }
    System.out.println(i);
  }
  
}
