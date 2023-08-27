import java.util.*;
import java.io.*;

public class Driver{
    static int len = 0;
    static int breadth = 0;
    public static void main(String[] args)
            throws IOException
    {
        Scanner scnr = new Scanner(System.in);
        while(true) {
            System.out.println("'*' indicates seat is taken, '.' indicates seat is available");
            File file = new File("auditorium.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            int counter = 0;

            while ((line = reader.readLine()) != null) {
                if (counter == 0) {
                    char seatNo = 'A';
                    System.out.print("  ");
                    for (int i = 0; i < line.length(); i++) {
                        System.out.print(seatNo);
                        seatNo++;
                    }
                    System.out.println();
                    len = line.length();
                }
                if (line.charAt(0) == '.' || line.charAt(0) == '*') {
                    System.out.println((counter + 1) + " " + line);
                    counter++;
                }
            }

            breadth = counter;
            String[] row = new String[breadth];
            Seat[][] seat = new Seat[breadth][len];
            int a = 0;

            for (int i = 0; i < breadth; i++) {
                for (int j = 0; j < len; j++) {
                    seat[i][j] = new Seat(); // Initialize each Seat object
                }
            }

            BufferedReader buff = new BufferedReader(new FileReader(file));

            while ((line = buff.readLine()) != null) {
                if (line.charAt(0) == '.' || line.charAt(0) == '*') {
                    row[a] = line;
                    a++;
                }
            }

            buff.close();
            reader.close();
            for (int i = 0; i < breadth; i++) {
                for (int j = 0; j < len; j++) {
                    seat[i][j].setSeat(row[i].charAt(j));
                }
            }

            System.out.println();
            System.out.println("Tickets are $12.75 each.\n");

            System.out.print("How many seats would you like to book? ");
            int totalSeat = scnr.nextInt();
            System.out.print("Enter the row number: ");
            int rowNo = scnr.nextInt();
            rowNo--;
            System.out.print("Enter the first seat number: ");
            String selection = scnr.next();

            char startingSeat = selection.charAt(0);
            int startSeat = startingSeat - 65;

            boolean select = availability(rowNo, startSeat, totalSeat, seat);
            if (select == true) {
                confirmBooking(rowNo, startSeat, totalSeat, seat);

                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < breadth; i++) {
                    String input = "";
                    for (int j = 0; j < len; j++) {
                        input = input + seat[i][j];
                    }
                    writer.write(input);
                    if (i < breadth - 1) {
                        writer.newLine();
                    }
                }
                writer.close();
                System.out.println("Congratulations!!! Your seat has been booked");
                double totalPrice = 12.75 * totalSeat;
                String formatNumber = String.format("%.2f", totalPrice);
                System.out.println("Your total cost = $" + formatNumber);
            } else {
                System.out.println("Sorry one/some of the seats are not available");
            }
            System.out.println();
            System.out.println("Would you like to book another ticket? Y/N");
            String confirmation = scnr.next();
            if(confirmation.charAt(0) == 'N'){
                break;
            }
        }
    }

    public static boolean availability(int row, int startingSeatNo, int total, Seat[][] seat){
        boolean b = true;
        if ((startingSeatNo + total) > 26){
            return false;
        }
        for(int i = startingSeatNo; i < startingSeatNo + total; i++){
            if(!seat[row][i].getSeat()){
                b = false;
                break;
            }
        }
        return b;
    }
    public static void confirmBooking(int row, int startingSeatNo, int total, Seat[][] seat){
        for(int i = startingSeatNo; i < startingSeatNo + total; i++){
            seat[row][i].setSeat('*');
        }
    }
}
