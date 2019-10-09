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
			System.out.println("I am trying to take " + rand + " unit/s from semaphore!");
			String response = semaphore.P(rand);
	    	System.out.println(response);
			TimeUnit.SECONDS.sleep(1);
			System.out.println("I am trying to return " + rand + " unit/s from semaphore!");
			response = semaphore.V(rand);
			System.out.println(response);
		}
	}
}
