import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client implements Runnable {
	static BufferedReader in = null;
	static PrintWriter out = null;
	static BufferedReader inu;
	static String fuser, fserver;
	static Client cl;
	String clName = "";

	private void Go() throws IOException {
		Scanner sc = new Scanner(System.in);
		inu = new BufferedReader(new InputStreamReader(System.in));

		String addres = "";
		System.out.println("Добро пожаловать в клиенты");

		InetAddress IP = InetAddress.getLocalHost();
		System.out.println("IP адрес вашей системы (не сервера) - " + IP.getHostAddress());

		Socket fromserver = null;
		System.out.println("Введите номер порта (по умолчанию 8080)");
		int portServ = sc.nextInt();
		System.out.println("Введите IP адрес (127.0.0.1 если локально)");
		addres = inu.readLine();
		
		System.out.println("Введите ваше имя");
		clName = inu.readLine();

		System.out.println("Подключение к ... " + addres);

		fromserver = new Socket(addres, portServ);
		in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
		out = new PrintWriter(fromserver.getOutputStream(), true);
		
		new Thread(cl).start();
		System.out.println("Успешно. Приятного общения");
		while ((fserver = in.readLine()) != null) {
			System.out.println(fserver);

		}

		out.close();
		in.close();
		inu.close();
		fromserver.close();
	}

	public static void main(String[] args) throws IOException {
		cl = new Client();
		cl.Go();
	}

	@Override
	public void run() {
		inu = new BufferedReader(new InputStreamReader(System.in));
		String a;
		//System.out.println("ПОТООК ПОШОООООООЛ");
		try {
			while ((fuser = inu.readLine()) != null) {

				out.println(clName + " " + fuser);
				//System.out.println(fuser);
				//System.out.println("ПОТООК ИДЕЕЕЕЕЕЕЕЕЕЕЕЕЕт");
			}
		} catch (IOException e) {
			System.out.println("Общение скончалось(((");
			//e.printStackTrace();
		}
	}
}