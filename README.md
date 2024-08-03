# Asset and Liability Management Application

Welcome to the Asset and Liability Management Application! This application facilitates the management of assets, liabilities, and financial statements effectively. It is developed using Java 21, Spring Boot, Axon Framework, MySQL, and Axon Server, utilizing the CQRS (Command Query Responsibility Segregation) and Event Sourcing architecture patterns.

## Features

- **Asset Management**: Track and manage various types of assets dynamically.
- **Liability Management**: Efficiently handle and monitor liabilities over time.
- **Financial Statement Management**: Generate and maintain balance sheets and financial statements.
- **Automated Database Setup**: The database is created automatically upon application startup.
- **Event Sourcing**: Captures all changes as events for reliable audit trails.
- **CQRS**: Segregates command and query operations for improved scalability and performance.

## Prerequisites

Before running the application, ensure the following:

- **Java 21**: Installed on your system.
- **Axon Server**: Must be running before starting the application.
- **MySQL**: Ensure MySQL is installed and accessible.
- **Axon Console Credentials**: Modify the Axon Console credentials in the `application.properties` file.

## Installation and Setup

1. **Clone the repository**:
   ```sh
   git clone https://github.com/BrodyGaudel/accounting.git
   cd asset-liability-management
   ```

2. **Configure Database and Axon Server**:
    - Ensure MySQL is running and accessible.
    - Start Axon Server before launching the application.
    - Modify Axon Console credentials in `application.properties`:
      ```properties
      axon.axonserver.server=localhost:8024
      axon.axonserver.token=your-axon-console-token
      ```

3. **Run the Application**:
    - Use Maven or Gradle to build and run the application:
      ```sh
      mvn clean install
      mvn spring-boot:run
      ```
      or
      ```sh
      gradle clean build
      gradle bootRun
      ```

4. **Access the Application**:
    - Once the application is running, access it through the configured endpoints.

## Usage

The application provides REST APIs to manage assets, liabilities, and financial statements. Detailed API documentation can be found at `[your-api-documentation-url]`.

## Contributing

Contributions are welcome! Fork the repository, make improvements, and submit a pull request.

## Author

- **Brody Gaudel MOUNANGA BOUKA**

---

Thank you for choosing the Asset and Liability Management Application. Manage your finances with ease!