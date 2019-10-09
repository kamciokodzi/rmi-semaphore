package com.kkolodziej.rmiserver;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.kkolodziej.rmiinterface.RMIInterface;

public class Server extends UnicastRemoteObject implements RMIInterface{
    private static final long serialVersionUID = 1L;
    private int ac = 10;

    protected Server() throws RemoteException {
        super();
    }

    @Override
    public synchronized String P(int val) throws RemoteException, InterruptedException{
        while (ac < val) { this.wait(); }
        ac -= val;
        return "You entered CS.";
    }

    @Override
    public synchronized String V(int val) throws RemoteException, InterruptedException{
        ac += val;
        this.notifyAll();
        return "You left CS.";
    }

    public static void main(String[] args){
        try {
            Naming.rebind("//localhost/MyServer", new Server());
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
