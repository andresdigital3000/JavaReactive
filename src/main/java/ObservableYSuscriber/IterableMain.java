package ObservableYSuscriber;

import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IterableMain {
  public static void main(String[] args) {

    List<String> lista= new ArrayList<String>(Arrays.asList("hola","que","tal"));

    Iterator<String> i= lista.iterator();
    while(i.hasNext()) {

      System.out.println(i.next());


    }
  }

}
