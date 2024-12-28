# How to Run 

This guide explains how to execute the `adss2023_v03.jar` Java program and utilize its functionality based on the provided arguments.

---

## **Instructions to Get Started**

**JAR File:**
   - download the `adss2023_v03.jar` file directly from the repository.
   - The JAR file is located in `SuperLee/release`.

   **OR**

**Clone the Repository or Download the JAR File:**
   - Clone the repository to access the full project:
     ```bash
     git clone https://github.com/your-repository-url.git
     ```

**Documentation:**
   - There is a full description available in `Docs/Readme.pdf`.

---

## **Execution Instructions**

### **Command Syntax**
Run the JAR file using the following syntax:
```bash
java -jar adss2023_v03.jar <mode> [role]
```

### **Parameters**
- `<mode>` (Required): Defines the program interface type. Acceptable values:
  - `SuperLiMainGUI`: Launches the `StoreManager` GUI.
  - `SuperLiMainCLI`: Launches the `StoreManager` CLI.
  - `GUI`: Launches a GUI based on the specified role.
  - `CLI`: Launches a CLI based on the specified role.
- `[role]` (Optional): Defines the user role when using `GUI` or `CLI` modes. Acceptable values:
  - `HRManager`
  - `ShipManager`
  - `Worker`
  - `StoreManager`

### **Examples**

#### **Run with GUI Interface (Store Manager)**
```bash
java -jar adss2023_v03.jar SuperLiMainGUI
```

#### **Run with CLI Interface (Store Manager)**
```bash
java -jar adss2023_v03.jar SuperLiMainCLI
```

#### **Run with GUI Interface for a Specific Role**
```bash
java -jar adss2023_v03.jar GUI HRManager
```

#### **Run with CLI Interface for a Specific Role**
```bash
java -jar adss2023_v03.jar CLI ShipManager
```

---
