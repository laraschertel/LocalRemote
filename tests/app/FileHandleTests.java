package app;

import org.junit.Test;

import java.io.*;
import java.nio.file.NoSuchFileException;

public class FileHandleTests {
    static final int PORT = 555;
    static final String HOSTNAME = "localhost";
    static final String FILENAME = "filename.txt";
    static final String FILENAME2 = "filename2.txt";
    private OutputStream os;
    

    @Test
    public void goodCreateLocalFile() throws Exception {

        InputStream is = new ByteArrayInputStream("test create local".getBytes());

        Remote remote = new RemoteImpl();

        remote.createFile("melisa3.txt", is);


    }


    @Test(expected = Exception.class)
    public void badCreateLocalFile() throws Exception {
        InputStream is = new ByteArrayInputStream("test data".getBytes());

        Remote remote = new RemoteImpl();

        remote.createFile(null, is);

    }

    @Test(expected = Exception.class)
    public void badRemoveLocalFile() throws Exception {

        InputStream is = new ByteArrayInputStream("test data".getBytes());

        Local local = new LocalImpl();

        local.createFile("file3.txt", is);

        local.removeFile(null);

    }

    @Test(expected = Exception.class)
    public void badRemoveLocalFile2() throws Exception {

        InputStream is = new ByteArrayInputStream("test data".getBytes());

        Local local = new LocalImpl();

        local.createFile("file4.txt", is);

        Remote remote = new RemoteImpl();

        remote.removeFile("file4.txt");

    }

    @Test
    public void goodCreateRemoteFile() throws Exception {

        InputStream is = new ByteArrayInputStream("test create in remote".getBytes());

        Remote remote = new RemoteImpl();

        Local local = new LocalImpl();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        local.createRemoteFile(FILENAME2, is,  baos);

        // simulated network
        byte[] serializedBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedBytes);

        remote.callCommandReceivedFromLocal(bais);

    }

     @Test
    public void goodRemoveRemoteFile() throws Exception {

        Remote remote = new RemoteImpl();

        Local local = new LocalImpl();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        local.removeRemoteFile("melisa3.txt", baos);

        // simulated network
        byte[] serializedBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedBytes);

        remote.callCommandReceivedFromLocal(bais);

    }

    @Test (expected = Exception.class)
    public void badCreateRemoteFile() throws Exception {

        InputStream is = new ByteArrayInputStream("test data".getBytes());

        Remote remote = new RemoteImpl();

        Local local = new LocalImpl();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        local.createRemoteFile(null, is,  baos);

        // simulated network
        byte[] serializedBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedBytes);

        remote.callCommandReceivedFromLocal(bais);

    }

    @Test (expected = NoSuchFileException.class)
    public void badRemoveRemoteFile() throws Exception {

        Remote remote = new RemoteImpl();

        Local local = new LocalImpl();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        local.removeRemoteFile("fileTest.txt", baos);

        // simulated network
        byte[] serializedBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedBytes);

        remote.callCommandReceivedFromLocal(bais);

    }
}