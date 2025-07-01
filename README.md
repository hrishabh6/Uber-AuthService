
# Spring Security JWT Authentication Microservice

This project implements a secure REST API backend using **Spring Boot**, **Spring Security**, and **JSON Web Tokens (JWT)** for authentication and authorization. It provides a foundational microservice for user registration, login, and access to protected resources.

## 🚀 Project Overview

This service offers core authentication functionalities:

* **User Registration (`/signup`):** Allows new users to create accounts. Passwords are securely hashed using **BCrypt**.
* **User Login (`/signin`):** Authenticates users via email and password, issuing an **HTTP-only JWT cookie** upon successful login.
* **Token Validation (`/validate`):** A protected endpoint demonstrating how to secure resources using JWT. Access requires a valid JWT.

The architecture is designed for **stateless authentication**, making it suitable for distributed systems and single-page applications (SPAs) or mobile clients.

## ⚙️ Technologies Used

* **Spring Boot:** Framework for building standalone, production-ready Spring applications.
* **Spring Security:** Powerful and customizable authentication and access-control framework.
* **JWT (JSON Web Tokens):** For secure, stateless authentication.
* **BCrypt:** Strong password hashing algorithm.
* **Spring Data JPA:** For data persistence (assuming a database behind `PassengerRepository`).

-----

## 🔑 Major Classes & Their Roles

| Class/Interface             | Role & Purpose                                                                                                                                                                                                                                                                                                  |
| :-------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `SpringSecurity.java`       | **Core Security Configuration:** Defines the Spring Security filter chain, sets up URL authorization rules, configures the `AuthenticationProvider` (using `UserDetailsService` and `PasswordEncoder`), and integrates the custom `JwtAuthFilter`.                                                      |
| `AuthController.java`       | **Authentication Endpoints:** Handles `/signup` (user registration), `/signin` (user login, JWT issuance), and `/validate` (protected resource access). It orchestrates authentication via `AuthenticationManager` and JWT generation via `JwtService`.                                           |
| `JwtAuthFilter.java`        | **JWT Validation Filter:** Extends `OncePerRequestFilter`. Intercepts incoming requests to extract and validate the JWT from cookies. If valid, it authenticates the user within Spring Security's `SecurityContextHolder`, allowing access to protected resources.                                       |
| `JwtService.java`           | **JWT Utility Service:** Encapsulates all JWT-related operations: creating tokens (signing), extracting claims (parsing), validating tokens (signature and expiry), and extracting user details (email).                                                                                             |
| `UserDetailsServiceImpl.java` | **Custom User Details Service:** Implements Spring Security's `UserDetailsService`. Responsible for loading user-specific data (e.g., `Passenger` entity) from the database and mapping it to Spring Security's `UserDetails` format (`AuthPassengerDetails`).                                         |
| `AuthPassengerDetails.java` | **Spring Security User Adapter:** Implements Spring Security's `UserDetails` interface. It adapts your `Passenger` entity to fit Spring Security's understanding of a user, providing username and password for authentication.                                                                        |
| `AuthService.java`          | **User Registration Logic:** Handles the business logic for creating new passenger accounts, including password encryption using `BCryptPasswordEncoder` before saving to the repository.                                                                                                                |
| `AuthenticationManager`     | **Authentication Orchestrator:** The high-level interface that directs authentication requests to the appropriate `AuthenticationProvider`. Your `AuthController` uses this to initiate the login process.                                                                                                |
| `AuthenticationProvider`    | **Authentication Logic:** Performs the actual authentication. Here, `DaoAuthenticationProvider` uses `UserDetailsService` to load user details and `BCryptPasswordEncoder` to verify credentials.                                                                                                       |
| `SecurityContextHolder`     | **Authenticated User Storage:** A static helper class that holds the `SecurityContext`, which in turn stores the `Authentication` object (representing the currently logged-in user). This allows you to access the authenticated user throughout your application.                                    |

-----

## 💡 Important Points

* **Stateless Authentication:** This project uses JWTs, meaning no session data is stored on the server side. The JWT itself contains all necessary user information.
* **HTTP-Only Cookies:** The JWT is stored in an HTTP-only cookie (`ResponseCookie.from("jwtToken", jwtToken).httpOnly(true)`). This is a crucial security measure that prevents client-side JavaScript from accessing the token, mitigating XSS (Cross-Site Scripting) attacks.
* **`JwtAuthFilter` Position:** The `JwtAuthFilter` is added *before* `UsernamePasswordAuthenticationFilter.class` in the `SecurityFilterChain`. This is essential as your custom filter handles authentication via JWT before Spring Security's default form/basic authentication would typically run.
* **Password Hashing:** Passwords are never stored in plain text. `BCryptPasswordEncoder` ensures strong, one-way hashing for security.
* **CORS Configuration:** `WebMvcConfigurer` is used to configure CORS, allowing frontend applications on different origins (e.g., `http://localhost:3000`) to communicate with this backend.
* **`shouldNotFilter` in `JwtAuthFilter`:** This method prevents the JWT filter from running for specific endpoints (like `/signin` and `/signup`) where a token might not yet exist or is not required for access.

-----

## ▶️ Getting Started

1.  **Clone the repository:**
    ```bash
    git clone [your-repo-url]
    cd [your-project-directory]
    ```
2.  **Configure `application.properties`/`application.yml`:**
    * Set `jwt.secret` (a strong, unique secret key for signing tokens).
    * Set `jwt.expiry` (token expiration time in seconds).
    * Configure your database connection properties for the `PassengerRepository`.
3.  **Build the project:**
    ```bash
    mvn clean install
    ```
    (or use Gradle if configured)
4.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
    (or run from your IDE)

-----