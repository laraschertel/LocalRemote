package tcp;

import java.io.IOException;

public interface Client {
    /**
     *
     * @param hostname try to connect to the hostname
     * @param port
     * @return a connection
     * @throws IOException if there is any Input/Outputproblems
     */
    Connection connect(String hostname, int port) throws IOException;
}
