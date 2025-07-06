package main.java.com.yourcompany.vehiclepayment.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConfig {
    private static final String DB_URL = "jdbc:sqlite:vehicle_payment.db";
    private static final String DRIVER_CLASS = "org.sqlite.JDBC";
    
    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC driver not found", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("foreign_keys", "true");
        return DriverManager.getConnection(DB_URL, props);
    }
    
    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Create payments table
            String createPaymentsTable = """
                CREATE TABLE IF NOT EXISTS payments (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    vehicle_id VARCHAR(50) NOT NULL,
                    amount DECIMAL(10,2) NOT NULL,
                    currency VARCHAR(3) NOT NULL,
                    payment_method VARCHAR(20) NOT NULL,
                    status VARCHAR(20) NOT NULL,
                    transaction_id VARCHAR(100) UNIQUE,
                    description TEXT,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
                )
                """;
            
            // Create payment_details table for card information
            String createPaymentDetailsTable = """
                CREATE TABLE IF NOT EXISTS payment_details (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    payment_id INTEGER NOT NULL,
                    card_number_masked VARCHAR(20),
                    card_holder_name VARCHAR(100),
                    expiry_date VARCHAR(5),
                    payment_reference VARCHAR(100),
                    processor_response TEXT,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (payment_id) REFERENCES payments(id) ON DELETE CASCADE
                )
                """;
            
            // Create indexes for better performance
            String createIndexes = """
                CREATE INDEX IF NOT EXISTS idx_payments_vehicle_id ON payments(vehicle_id);
                CREATE INDEX IF NOT EXISTS idx_payments_status ON payments(status);
                CREATE INDEX IF NOT EXISTS idx_payments_created_at ON payments(created_at);
                CREATE INDEX IF NOT EXISTS idx_payment_details_payment_id ON payment_details(payment_id);
                """;
            
            stmt.execute(createPaymentsTable);
            stmt.execute(createPaymentDetailsTable);
            stmt.execute(createIndexes);
            
            System.out.println("Database initialized successfully!");
            
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }
} 