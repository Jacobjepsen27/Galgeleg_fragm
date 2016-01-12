package jepsen.dk.galgeleg_fragm;

import android.widget.ArrayAdapter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serialisering {

  public static ArrayList map;
  public static ArrayList retur;

  public static void gem(Object obj, String filnavn) throws IOException {
    FileOutputStream datastrøm = new FileOutputStream(filnavn);
    ObjectOutputStream objektstrøm = new ObjectOutputStream(datastrøm);
    objektstrøm.writeObject(obj);
    objektstrøm.close();
  }

  public static ArrayList hent(String filnavn) throws Exception {
    map = new ArrayList();
    FileInputStream datastrøm = new FileInputStream(filnavn);
    ObjectInputStream objektstrøm = new ObjectInputStream(datastrøm);
//    Object obj = objektstrøm.readObject();
    map = (ArrayList) objektstrøm.readObject();
    objektstrøm.close();
    for (Object s: map){
      retur.add(s);
    }
    return retur;
  }
}