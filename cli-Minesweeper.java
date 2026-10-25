import java.util.Scanner;
class main{

	static Game game = new Game();

	public static void main(String[] args){
		/*long start = System.nanoTime();
		long next = start;
		while(true){
			if(System.nanoTime() > next){
				board = new Cell[h][w]; 
				initBoard();
				out();
				num ++;
				next += 8_333_333;
			}
		}*/
		game.start();
	}
}

class Game{

	int w = 20;
	int h = 20;
	int dif = 40;

	int count = (h-2)*(w-2)-dif;
	int num = 0;
	boolean lost = false, won = false;
	Cell[][] board = new Cell[h][w];
	Scanner scanner = new Scanner(System.in);
	int x=1,y=1;

	String reset = "\u001B[0m";
	String red = "\u001B[31m";
	String green = "\u001B[32m";

	public Game(){}

	public void start(){
		initBoard();
		while(!lost){
			out();
			move();
		}
	}

	private void enter(int y, int x){
		//if(x==0 || y==0 || x==w-1 || y==h-1){return;}
		count--;
		board[y][x].isOpen = true;
		if(board[y][x].isBoom){
			lost = true;
		}	
		if(count == 0){
			won = true;
		}
		if(board[y][x].value == 0){
			if(board[y-1][x-1] != null && !board[y-1][x-1].isOpen){enter(y-1,x-1);}
			if(board[y-1][x] != null && !board[y-1][x].isOpen){enter(y-1,x);}
			if(board[y-1][x+1] != null && !board[y-1][x+1].isOpen){enter(y-1,x+1);}
			if(board[y][x-1] != null && !board[y][x-1].isOpen){enter(y,x-1);}
			if(board[y][x+1] != null && !board[y][x+1].isOpen){enter(y,x+1);}
			if(board[y+1][x-1] != null && !board[y+1][x-1].isOpen){enter(y+1,x-1);}
			if(board[y+1][x] != null && !board[y+1][x].isOpen){enter(y+1,x);}
			if(board[y+1][x+1] != null && !board[y+1][x+1].isOpen){enter(y+1,x+1);}
		}
	}

	private void move(){
		String input = scanner.next();
		switch(input){
			case "w": if(y>1){y--;}
					  break;
			case "s": if(y<h-2){y++;}
					  break;
			case "a": if(x>1){x--;}
					  break;
			case "d": if(x<w-2){x++;}
					  break;
			case "e": enter(y,x);
					  break;
			case "f": board[y][x].mine();
					  break;
		}
	}

	private void initBoard(){	
		int tempDif = dif;
		int y, x;
		while(tempDif > 0){
			y = (int)(Math.random()*(h-2)+1);
			x = (int)(Math.random()*(w-2)+1);
			board[y][x] = new Cell(true, 0);
			tempDif --;
		}

		int value;
		for(int iy = 1;iy < h-1;iy++){
			for(int ix = 1;ix < w-1;ix++){
				if(board[iy][ix] == null){
					value = 0;
					if(board[iy-1][ix-1] != null && board[iy-1][ix-1].isBoom()){value++;}
					if(board[iy-1][ix] != null && board[iy-1][ix].isBoom()){value++;}
					if(board[iy-1][ix+1] != null && board[iy-1][ix+1].isBoom()){value++;}
					if(board[iy][ix-1] != null && board[iy][ix-1].isBoom()){value++;}
					if(board[iy][ix+1] != null && board[iy][ix+1].isBoom()){value++;}
					if(board[iy+1][ix-1] != null && board[iy+1][ix-1].isBoom()){value++;}
					if(board[iy+1][ix] != null && board[iy+1][ix].isBoom()){value++;}
					if(board[iy+1][ix+1] != null && board[iy+1][ix+1].isBoom()){value++;}
					board[iy][ix] = new Cell(false, value);
				}
			}
		}
	}

	private void out(){
		String buffer = "";
		for(int iy = 1;iy < h-1;iy++){
			for(int ix = 1;ix < w-1;ix++){
				if(x == ix && y == iy){
					if(!board[y][x].isOpen || board[y][x].value == 0){
						buffer += "🟥"; 	
					} else {
						buffer += red + board[iy][ix] + reset; 	
					}
				} else 	buffer += board[iy][ix]; 
			}
			buffer += "\n";
		}
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println(num+"\n"+buffer);
		System.out.println(count);
		if(lost){
			System.out.println(red+"you lost!"+reset);
		} else if(won){
			System.out.println(green+"you won!"+reset);
		}
	}
	
}

class Cell{
	boolean isBoom, isOpen = false, mined =false;
	int value = 0;
	String symbol = "";
	public Cell(boolean isBoom, int value){
		if(isBoom){
			this.isBoom = true;
			symbol = "# ";
		} else {
			this.isBoom = false;
			this.value = value;
			if(value>0){
				symbol = Integer.toString(value)+" ";
			} else symbol = "⬜";
		}
	}

	public boolean isBoom(){
		return this.isBoom;
	}

	public void mine(){
		mined = !mined;
	}

	@Override
	public String toString(){
		if(!isOpen){
			if(!mined){
				return "⬛";
			} else return "🟧";
		} else return symbol;
	}
}

