package com.kkolodziej.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
    public String P(int val) throws RemoteException, InterruptedException;
    public String V(int val) throws RemoteException, InterruptedException;
}
