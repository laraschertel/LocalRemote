package local;

import exceptions.FileException;
import tcp.Client;
import tcp.Connection;
import tcp.TCPConnector;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class LocalImpl implements Local {
    private static final int CREATE = 0;
    private static final int REMOVE = 1;
    private static final int COPY = 2;
    private Connection connection;


    @Override
    public void connect(String hostname, int port) throws IOException {

        Client client = new TCPConnector();
        this.connection = client.connect(hostname, port);
    }

    @Override
    public void createFile(String filename, InputStream is) throws Exception, FileException {


        if(filename == null){
            throw new FileException("File name cannot be null");
        }
        String path = "local/"+filename;
        File file = new File(path);

        if(file.exists()){
            throw new FileException("File already exists and cannot be overwriten");
        }

            byte[] readBuffer = new byte[100];
            try {
                is.read(readBuffer);
            } catch (IOException ex) {
                System.err.println("couldn't read data - fatal");
                System.exit(0);
            }

            try {
                OutputStream os = new FileOutputStream(file);
                try {
                    os.write(readBuffer);
                } catch (IOException ex) {
                    System.err.println("couldn’t write data - fatal");
                    System.exit(0);
                }
            } catch (FileNotFoundException ex) {
                System.err.println("Could not open file - fatal");
                System.exit(0);
            }
    }


    @Override
    public void removeFile(String filename) throws IOException {

        String path = "local/"+filename;

        if(new File(filename).isFile()){
            Files.delete(Paths.get(path));
            System.out.println("File was deleted");
        } else {
            throw new NoSuchFileException("file does not exist");
        }
    }

    @Override
    public void createRemoteFile(String filename, InputStream is, OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);

        byte[] readBuffer = new byte[100];
        try {
            is.read(readBuffer);
        } catch (IOException ex) {
            System.err.println("couldn't read data - fatal");
            System.exit(0);
        }
        try{
            dos.writeInt(CREATE);
            dos.writeUTF(filename);
            dos.write(readBuffer);
        }catch (IOException e){
            System.err.println("could not remove remote file");
        }

    }

    @Override
    public void removeRemoteFile(String filename, OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);

        try{
            dos.writeInt(REMOVE);
            dos.writeUTF(filename);
        }catch (IOException e){
            System.err.println("could not remove remote file");
        }

    }

    @Override
    public void copyFileToRemote(String filename, OutputStream os) throws IOException, FileException {
        DataOutputStream dos = new DataOutputStream(os);

        String path = "local/" + filename;
        File f = new File(path);
        if(!f.exists()){
            throw new FileException("file cannot be copied because file doens't exists");
        }

        FileInputStream fis = new FileInputStream(path);
        int read = 0;
        try {
            dos.writeInt(COPY);
            dos.writeUTF(filename);
            do {
                read = fis.read();
                if (read != -1) {
                    os.write(read);
                }
            } while (read != -1);

        } catch (IOException e) {
            System.err.println("could not copy file to remote");
        }
    }

}
