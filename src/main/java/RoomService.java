import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Scanner;

public class RoomService extends Application {

    public double c_Price =25.00;
    public String[] f_menu;
    public boolean cleaning = false;
    public boolean foodservice = false;
    public int roomNumber;
    Scanner scan = new Scanner(System.in);
    public void cleaningbill(){
        System.out.println("what room is this for: ");
        roomNumber = scan.nextInt();
        System.out.println("adds"+ c_Price +"to room #"+roomNumber);
    }
    public void orderfood(){

    }
    public wich service(){
        if(cleaning == true){}

    }

}
