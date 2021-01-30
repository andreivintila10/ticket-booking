package ticket.booking;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DBConnectionFactoryTest {

    Connection connection = null;

    @BeforeEach
    void createConnection() {
        connection = DBConnectionFactory.getConnection();
    }

    @Test
    void testConnection() {
        assertTrue(connection != null);
    }

    @AfterEach
    void closeConnection() {
        try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }

}
