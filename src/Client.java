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
		System.out.println("����� ���������� � �������");

		InetAddress IP = InetAddress.getLocalHost();
		System.out.println("IP ����� ����� ������� (�� �������) - " + IP.getHostAddress());

		Socket fromserver = null;
		System.out.println("������� ����� ����� (�� ��������� 8080)");
		int portServ = sc.nextInt();
		System.out.println("������� IP ����� (127.0.0.1 ���� ��������)");
		addres = inu.readLine();
		
		System.out.println("������� ���� ���");
		clName = inu.readLine();

		System.out.println("����������� � ... " + addres);

		fromserver = new Socket(addres, portServ);
		in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
		out = new PrintWriter(fromserver.getOutputStream(), true);
		
		new Thread(cl).start();
		System.out.println("�������. ��������� �������");
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
		//System.out.println("������ �����������");
		try {
			while ((fuser = inu.readLine()) != null) {

				out.println(clName + " " + fuser);
				//System.out.println(fuser);
				//System.out.println("������ �����������������");
			}
		} catch (IOException e) {
			System.out.println("������� ����������(((");
			//e.printStackTrace();
		}
	}
}