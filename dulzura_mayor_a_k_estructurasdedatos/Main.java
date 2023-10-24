import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cGalletas = sc.nextInt();
        int dulzura = sc.nextInt();
        PriorityQueue galletas = new PriorityQueue<Integer>();

        for (int i = 0; i < cGalletas; i++){
            int temp = sc.nextInt();
            galletas.add(temp);
        }

        int cuenta = 0;

        while (galletas.size() >= 2) {
            int temp = 0;
            temp += (int) galletas.poll();
            temp += 2 * (int) galletas.poll();
            galletas.add(temp);

            cuenta++;

            if ( (int) galletas.peek() > dulzura){
                System.out.print(cuenta);
                sc.close();
                return;
            }
        }

        System.out.print( (int) galletas.peek() >= dulzura ? cuenta : -1 );
        sc.close();
        return;
    }
}