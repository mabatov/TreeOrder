package theory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class parseJSON {
    public static void main(String[] args) throws IOException, ParseException {


        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите тип (users или groups)");
        String type = r.readLine();
        String attr = r.readLine();

        int c;
        URL myUrl = new URL("ссылочка" +type+ "/" +attr);
        URLConnection myUrlCon = myUrl.openConnection();

        // Получить дату
        long d = myUrlCon.getDate();
        if(d == 0)
            System.out.println("Сведения о дате отсутствуют.");
        else
            System.out.println("Дата: " + new Date(d));

        // Получить тип содержимого
        System.out.println("Тип содержимого: " + myUrlCon.getContentType());

        // Получить дату срока действия ресурса
        d = myUrlCon.getExpiration();
        if(d == 0)
            System.out.println("Сведения о сроке действия отсутствуют.");
        else
            System.out.println("Срок действия истекает: " + new Date(d));

        // Получить дату последней модификации
        d = myUrlCon.getLastModified();
        if(d == 0)
            System.out.println("Сведения о дате последней модификации отсутствуют.");
        else
            System.out.println("Дата последней модификации: " + new Date(d));

        // Получить длину содержимого
        long length = myUrlCon.getContentLengthLong();
        if(length == -1)
            System.out.println("Длина содержимого недоступна");
        else
            System.out.println("Длина содержимого: " + length);

        if(length != 0) {
            System.out.println("=== Содержимое ===");
            InputStream input = myUrlCon.getInputStream();
            while(((c = input.read()) != -1)) {
                System.out.print((char) c);
            }
            input.close();
        } else {
            System.out.println("Содержимое недоступно.");
        }
    }
}
