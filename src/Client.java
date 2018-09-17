import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * создание клиента со всеми необходимыми утилитами, точка входа в программу в
 * классе Client
 */

public class Client {
	Scanner sc = new Scanner(System.in);
	BufferedReader in = null;
	PrintWriter out = null;
	BufferedReader inu;
	String clName = "";
	String addres = "";

	// читаем смс с серв
	private class ReadMsg extends Thread {
		@Override
		public void run() {

			String str;
			try {
				while (true) {
					str = in.readLine(); // ждем сообщения с сервера
					System.out.println(str); // пишем сообщение с сервера на консоль
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// отправлеяем мсм на сервер
	public class WriteMsg extends Thread {

		@Override
		public void run() {
			while (true) {
				String userWord;
				userWord = sc.nextLine(); // сообщения с консоли
				out.println(clName + ": " + userWord);

			}
		}
	}

	private void Go() throws IOException {
		inu = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Добро подаловать в клиенты");

		InetAddress IP = InetAddress.getLocalHost();
		System.out.println("Ваш IP адрес (не сервера) - " + IP.getHostAddress());

		Socket fromserver = null;
		System.out.println("Выберите порт для подключения(по умолчанию 8080)");
		int portServ = sc.nextInt();
		System.out.println("Введите IP подключения (127.0.0.1 по умолчанию)");
		addres = inu.readLine();

		System.out.println("Введите ваше имя");
		clName = inu.readLine();

		System.out.println("Подключение... ");
		try {
			fromserver = new Socket(addres, portServ);
			in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
			out = new PrintWriter(fromserver.getOutputStream(), true);

			new WriteMsg().start();
			new ReadMsg().start();
		} catch (Exception ex) {
			System.out.println("Ошибочка вышла... ");
			fromserver.close();
			in.close();
			out.close();
			inu.close();
		}
	}

	public static void main(String[] args) throws IOException {
		Client cl = new Client();
		cl.Go();
	}
}
