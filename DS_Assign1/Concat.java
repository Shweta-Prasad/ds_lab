import java.rmi.*;

public interface Concat extends Remote {
    public String concat (String x, String y) throws RemoteException;
}

// Terminal 1
// javac Concat.java ConcatRemote.java MyServer.java MyClient.java

// javac *.java
// rmic ConcatRemote
// rmiregistry

// Terminal 2
// java MyServer

// Terminal 3
// java MyClient