# 🔍 DailyFinance RestAssured Test Suite
This project contains automated tests for the [DailyFinance](https://dailyfinance.roadtocareer.net/) application.  
The tests are implemented using **RestAssured** and **TestNG** to validate key user flows and functionalities through API calls.


## 🧪 Project Overview

The automation suite covers the following scenarios:

1. User registration and verification that the confirmation email is received.
2. Negative tests for the password reset link.
3. Password reset request with a valid registered email.
4. Retrieving and processing the password reset email to set a new password.
5. Logging in with the updated password to verify successful login.
6. Adding two items (one with all fields, one with only mandatory fields) and asserting their presence in the item list.
7. Updating user email in the profile.
8. Logging out and logging in with updated email, verifying successful login with new email and failure with old email.
9. Admin login with credentials securely passed from the terminal.
10. Searching the updated email in the admin dashboard and asserting it is visible.
11. Registering multiple users using data from a CSV file.
12. Logging in as admin, scraping all users from the user table, and saving the data into a text file.

---

## ⚙️ Tech Stack

- **JDK 17+**
- **RestAssured** for API testing
- **TestNG** as the test framework
- **Gradle** for build and dependency management
- **Allure** for test reporting

---

## ⚙️ Prerequisites

- Java JDK 17 or higher installed
- Gradle installed or use the Gradle wrapper included
- Internet access to interact with the DailyFinance site and Gmail API
- Gmail account credentials set securely for email verification steps

---

---

## 📸 Screenshots & Recordings

Visuals from test executions are provided below to help understand the test flows and results:

### ✅ Allure Report Screenshots

- Overview:
  <img width="1235" height="588" alt="Image" src="https://github.com/user-attachments/assets/774f452d-9836-4312-9492-17ae3d25aef9" />

- Behaviors: 
  <img width="1204" height="602" alt="Image" src="https://github.com/user-attachments/assets/26e2c3b8-ecd2-4059-9da7-fe1c3392faa8" />

---

### 🎥 Automation Video
  https://github.com/user-attachments/assets/8f713161-846f-493c-aed8-054d834bde14


---

## 📄 Test Case Documentation

All test cases and steps are documented in the following shared document:

🔗 [Test Case Sheet (Google Docs)](https://docs.google.com/spreadsheets/d/1cGDdnG9a1kg3Pv6aT9gY-MJrW710SDwd/edit?usp=sharing&ouid=105412337074703854140&rtpof=true&sd=true)  

---

## 🚀 Run Instructions

```bash
# Run test with Allure report
gradle clean test

# Generate Allure report
allure generate allure-results --clean -output
allure serve allure-results

# Pass admin credentials securely
-Pusername=admin@test.com -Ppassword=admin123
```

---

