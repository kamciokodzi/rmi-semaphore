package com.kkolodziej.rmiclient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;
import java.util.Random;

import com.kkolodziej.rmiinterface.RMIInterface;

public class Client {
	private static RMIInterface semaphore;

	public static void main(String[] args)
		throws MalformedURLException, RemoteException, NotBoundException, InterruptedException {

		semaphore = (RMIInterface) Naming.lookup("//localhost/MyServer");
		Random generator = new Random();

		while (true) {
			int rand = Math.abs(generator.nextInt()) % 10 + 1;
			System.out.println("TRY TO TAKE " + rand + " UNITS");
			String response = semaphore.P(rand);
			System.out.println(response);
			System.out.println("Processing simulation...");
			TimeUnit.SECONDS.sleep(2);
			System.out.println("RETURN " + rand + " UNITS");
			response = semaphore.V(rand);
			System.out.println(response);
		}
	}
}
