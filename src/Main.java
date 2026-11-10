
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {

    static class Picture extends Thread {

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

                if (extension.isEmpty()) {
                    throw new Exception(
                            "Не удалось определить формат картинки"
                    );
                }

                String fileName = "picture2" + extension;
                File file = new File("C:/Users/Привет/" + fileName);

                try (InputStream input = connection2.getInputStream()) {
                    Files.copy(input, file.toPath());
                }

                System.out.println("Картинка скачана");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Sound extends Thread {

        public void run() {
            try {
                URL sound = new URL(
                        "https://dn710307.ca.archive.org/0/items/angora-overdrive-2005/Ashbury%20Heights%20-%20Angora%20Overdrive%20%28Demo%29/1-%20Ashbury%20Heights%20-%20Decent%20Cancer%20%28Demo%29%20%5BvskbYc90IWo%5D.mp3"
                );

                URLConnection connection3 = sound.openConnection();

                connection3.setConnectTimeout(15000);//джава ждет соединения с сайтом
                connection3.setReadTimeout(30000);//джава подключилась и ждет пока сервер пришлет данные

                File file = new File(
                        "C:/Users/Привет/Christ_Demo1.mp3"
                );

                try (InputStream input = connection3.getInputStream()) {
                    Files.copy(
                            input,
                            file.toPath());
                }

                System.out.println("Музыка скачана");

                try (InputStream input = new FileInputStream(file)) {
                    Player player = new Player(input);

                    System.out.println("Начинается воспроизведение");
                    player.play();
                    System.out.println("Воспроизведение завершено");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Picture picture = new Picture();
        Sound sound = new Sound();

        picture.start();
        sound.start();
    }
}


