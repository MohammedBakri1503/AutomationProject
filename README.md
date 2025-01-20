# AutomationProject

Here's the updated **README.md** formatted for GitHub:

---

# README

## 1. Introduction
This project is designed to test the functionality of the **DataSource API** and the **UI** of the application. The primary focus is to ensure correctness, reliability, and usability of features like managing data sources, creating dashboards, visualizations, and handling errors in data. The tests are tailored for application version **11.4.0**, released on **5th December 2024**.

---

## 2. Testing Strategy and Objectives
### **API Testing Objectives**
- Validate API endpoints for:
    - Adding, updating, deleting, and retrieving data sources.
- Ensure proper error handling for invalid inputs or non-existent resources.
- Confirm performance metrics such as response times and reliability.

### **UI Testing Objectives**
- Assess usability features like:
    - Dashboard creation.
    - Visualization creation.
    - Error handling in data.
- Ensure the interface is intuitive, responsive, and free of usability bottlenecks.

---

## 3. Setting Up the Testing Environment
Follow these steps to set up the testing environment:

1. **Install Java Development Kit (JDK):**
    - Ensure JDK version 23 or higher is installed.
    - Download from [AdoptOpenJDK](https://adoptium.net/).

2. **Install Maven:**
    - Follow the installation guide at [Maven Installation](https://maven.apache.org/install.html).

3. **Install Chrome and ChromeDriver:**
    - Ensure **Google Chrome (v131.0)** and the matching **ChromeDriver** are installed for UI testing.

4. **Install Dependencies:**
    - Navigate to the project directory and run:
      ```bash
      mvn clean install
      ```

5. **Set Up Local Environment:**
    - Ensure the application is running locally.
    - Confirm API endpoints are accessible at `http://localhost:<port>`.

---

## 4. Steps to Execute Tests

### **API Tests**
1. Navigate to the project directory.
2. Run the following command to execute API tests:
   ```bash
    mvn  test -Dtest=**/testAPI/*

   ```

### **UI Tests**
1. Navigate to the project directory.
2. Run the following command to execute UI tests:
   ```bash
   mvn test 
   ```

---

## 5. Summary of Test Plan

### **API Features Tested**
- Add Data Source
- Update Data Source Using UID
- Delete Data Source
- Get Data Source

### **UI Features Tested**
- Dashboard Creation
- Visualization Creation
- Error Modification in Data
- Usability and Responsiveness

---

## 6. Troubleshooting
- If tests fail, ensure the application is running locally and all dependencies are installed.
- Verify the `pom.xml` file is correctly configured.
- Use the `-e` flag with Maven for detailed error logs:
  ```bash
  mvn test -e
  ```

---

