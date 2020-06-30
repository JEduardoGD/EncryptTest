package digital.sepiente.tests.encrypttest.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class Reader {
	
	public void read(String archivo) {
		StringBuilder salida = new StringBuilder();
		String cadena;
		String SALTO = "\r\n";
        FileInputStream archivoX = null;
        InputStreamReader fr = null;
        BufferedReader b = null;
		archivo = StringUtil.cleanString(archivo);
        File f = new File(archivo);
        if(!(f.exists() && f.canRead())) {
            System.out.println("Ocurrio un error con el archivo \"{}\"");
            return;
        }
        try {
            archivoX = new FileInputStream(archivo);
            fr = new InputStreamReader(archivoX, Charset.defaultCharset());
            b = new BufferedReader(fr);
            while((cadena = b.readLine())!=null) {
                salida.append(cadena);
                salida.append(SALTO);
            }
            if(b != null) {
                b.close();
            }
            if(fr != null) {
                fr.close();
            }
            if(archivoX != null) {
                archivoX.close();
            }
        } catch(Exception e) {
        } finally {
            try {
                if (b != null) {
                    b.close();
                }
                if (fr != null) {
                    fr.close();
                }
                if (archivoX != null) {
                    archivoX.close();
                }
            } catch (Exception e) {
            }
        }
        salida.append(SALTO);
        System.out.println(salida);
	}
}
