import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Testa {
  public static void main(String[]args) throws FileNotFoundException {
    try  {
      PrintWriter out = new PrintWriter("a.txt");
      out.println("x");

      out.close();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    System.setOut(new PrintStream(new File("a.txt")));
    System.out.println("a");

  }
}
