# Hospital Management System (HMS)

A Spring Boot based Hospital Management System that functions as a responsive website and a Progressive Web App (PWA) for Android installation.

## Prerequisites
- Java 17 or higher
- Maven 3.6+

## Project Structure
- **Backend**: Spring Boot 3.2 (Web, JPA, Thymeleaf, H2)
- **Frontend**: HTML5, CSS3, Thymeleaf Templates
- **Database**: H2 In-Memory (data resets on restart by default)

## Running the Application

1. **Install Maven** (if not installed):
   ```bash
   sudo apt install maven
   # or
   brew install maven
   ```

2. **Run the application**:
   Navigate to the project directory and run:
   ```bash
   mvn spring-boot:run
   ```

3. **Access the App**:
   Open [http://localhost:8080](http://localhost:8080) in your browser.

## Features
- **Dashboard**: Quick access to modules.
- **Patients**: Register and list patients.
- **Doctors**: Register and list doctors.
- **Appointments**: Book appointments between patients and doctors.
- **PWA**: Installable on Android (Add to Home Screen), Offline basic support.

## PWA Installation (Android)
1. Open the website in Chrome or Firefox on Android.
2. You should see an "Add to Home Screen" usage prompt or go to the browser menu -> "Install App" / "Add to Home Screen".
3. The app will be installed with the custom icon and launch in fullscreen mode.

## Database Console
To view the H2 database console, visit [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
- JDBC URL: `jdbc:h2:file:./data/hmsdb`
- User: `sa`
- Password: `password`
