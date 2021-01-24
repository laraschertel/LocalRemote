package tcp;

import java.io.IOException;

public interface Server {

    /**
     *
     * @param port listen on the port waiting for a connection
     * @return a connection
     * @throws IOException
     */
    Connection acceptConnection(int port) throws IOException;
}
