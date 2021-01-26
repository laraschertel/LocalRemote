package util;

import local.Local;
import local.LocalImpl;
import remote.Remote;
import remote.RemoteImpl;
import tcp.Connection;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class LocalRemoteExchanger {
    public static void main(String[] args) throws Exception {

        String hostname = null;
        int port = -1;
        String portString = null;

        if (args.length == 2) { // variant 1: send
            hostname = args[0];
            portString = args[1];
        } else if (args.length == 1) { // variant 2: receive
            portString = args[0];

        }
        port = Integer.parseInt(portString);

        Local local = new LocalImpl();

        InputStream is = new ByteArrayInputStream("lara file test".getBytes());

        local.createFile("laraInLocal.txt", is);

        Remote remote = new RemoteImpl();

        remote.createFile("laraInRemote.txt", is);


        Connection remoteConnection= null;
        Connection localConnection = null;

       if (hostname == null) {
            // listen on port
            remoteConnection = remote.acceptConnection(port);
        } else {
            // try to connect to hostname
           localConnection = local.connect(hostname, port);
        }

        InputStream is2 = new ByteArrayInputStream("command came from local".getBytes());

        local.createRemoteFile("LocalInRemote.txt", is2,  localConnection.getOutputStream());

        remote.receiveAndCallCommandFromLocal(remoteConnection.getInputStream());

        local.removeRemoteFile("laraInRemote.txt", localConnection.getOutputStream());

        local.copyFileToRemote("laraInLocal.txt", localConnection.getOutputStream());



    }
}
