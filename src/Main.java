
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

class Picture extends Thread {

    public void run() {
        try {
            Scanner scan = new Scanner(System.in);

            System.out.println("Введите ссылку на картинку");
            String input1 = scan.nextLine().trim();

            URL picture2 = new URL(input1);
            URLConnection connection2 = picture2.openConnection();
            

            String contentType = connection2.getContentType();
            String extension = "";

            if (contentType != null) {
                if (contentType.contains("jpeg")) {
                    extension = ".jpg";
                } else if (contentType.contains("png")) {
                    extension = ".png";
                } else if (contentType.contains("webp")) {
                    extension = ".webp";
                } else if (contentType.contains("gif")) {
                    extension = ".gif";
                }
            }

            if (extension.isEmpty()) {//пустой
                throw new Exception(" не удалось определить формат картинки");
            }

            String fileName = "picture2" + extension;
            File file = new File("C:/Users/Привет/" + fileName);

            try (InputStream input = connection2.getInputStream()) {
                Files.copy(input, file.toPath());
            }

            System.out.println("картинка скачана");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class SoundPlayer extends Thread {

    public void run() {
        try (InputStream input = new FileInputStream("C:/Users/Привет/Ashbury_Heights_-_Penance_80299166.mp3")) {//не получилось с сайта скачать т.к. сервер не предоставил ответ за определенное время

            Player player = new Player(input);
            System.out.println("Начинается воспроизведение");
            player.play();
            System.out.println("Воспроизведение завершено");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Main {

    public static void main(String[] args) {

        Picture picture = new Picture();
        SoundPlayer sound = new SoundPlayer();

        picture.start();
        sound.start();
    }
}









