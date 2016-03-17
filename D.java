package main;

public class D {
	public static void d(Object obj){
		System.out.println(obj);
	}
	
	public static void d(String str, Object obj){
		System.out.println(str +"->"+ obj);
	}
	


    public static void printRow(Object[] row) {
        for (Object i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }

    public static void pArr() {
        Object[][] twoDm= Board.getBoard();
     

        for(Object[] row : twoDm) {
            printRow(row);
        }
    }
	
}
