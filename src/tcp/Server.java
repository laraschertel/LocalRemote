package tcp;

import java.io.IOException;

public interface Server {

    /**
     *
     * @param port listen on the port waiting for a connection
     * @return a connection
     * @throws IOException if there is any I/O problems
     */
    Connection acceptConnection(int port) throws IOException;
}
