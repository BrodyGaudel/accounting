```markdown
# Asset, Liability, and Balance Sheet Management

## Description

This application is designed to manage assets, liabilities, and balance sheets. It is developed using Java 21, Spring Boot, Axon Framework, MySQL, and Axon Server for the backend. Angular is used for the frontend. The application is based on a CQRS (Command Query Responsibility Segregation) and Event Sourcing architecture.

## Features

- **Asset Management**: Track and manage company assets efficiently.
- **Liability Management**: Monitor and manage financial obligations and debts.
- **Balance Sheet**: Generate and view comprehensive balance sheets.
- **CQRS Architecture**: Separate command and query responsibilities to optimize performance and scalability.
- **Event Sourcing**: Capture all changes to application state as a sequence of events.

## Technologies Used

- **Backend**: Java 21, Spring Boot, Axon Framework, MySQL, Axon Server
- **Frontend**: Angular

## Getting Started

### Prerequisites

- Java 21
- Maven (for building Java projects)
- Axon Server : https://www.axoniq.io/download

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/BrodyGaudel/accounting.git
   cd accounting
   ```

2. **Backend Setup**:
   - Configure MySQL database settings in `application.properties`.
   - Build and run the Spring Boot application:
     ```bash
     mvn spring-boot:run
     ```


3. **Access the application**:
   - Open a web browser and go to `http://localhost:8888/accounting/swagger-ui.html` to use the application.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.


```