//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static String readLineFromFile() throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.FILE_NAME)));
        String line = reader.readLine();
        reader.close();
        return line;
    }

    public static void writeLineToFile(String content, boolean append) throws Exception {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME, append));
        writer.write(content);
        writer.close();
    }

    public static void main(String[] args) {
        // Check if the number of arguments is valid
        if (args.length != 1) {
            System.out.println(Constants.INVALID_ARG_COUNT_MESSAGE);
            return;
        }

        // Check arguments
        if (args[0].equals(Constants.LIST_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                for (String employee : readLineFromFile().split(Constants.DELIMITER)) {
                    System.out.println(employee);
                }
            } catch (Exception exception) {
            }
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].equals(Constants.SHOW_RANDOM_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String line = readLineFromFile();
                System.out.println(line);
                String[] employees = line.split(Constants.DELIMITER);
                System.out.println(employees[new Random().nextInt(employees.length)]);
            } catch (Exception exception) {
            }
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].contains(Constants.ADD_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                writeLineToFile(Constants.DELIMITER + " " + args[0].substring(1), true);
            } catch (Exception exception) {
            }
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].contains(Constants.SEARCH_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String[] employees = readLineFromFile().split(Constants.DELIMITER);
                String searchName = args[0].substring(1);
                for (String employee : employees) {
                    if (employee.trim().equals(searchName)) {
                        System.out.println(Constants.FOUND_MESSAGE);
                        System.out.println(Constants.LOADED_MESSAGE);
                        return;
                    }
                }
                System.out.println(Constants.NOT_FOUND_MESSAGE);
            } catch (Exception exception) {
            }
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].contains(Constants.COUNT_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String line = readLineFromFile();
                String[] words = line.trim().split("\\s+");
                System.out.println(words.length + " word(s) found " + line.length());
            } catch (Exception exception) {
            }
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].contains(Constants.UPDATE_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String[] employees = readLineFromFile().split(Constants.DELIMITER);
                String targetEmployee = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(targetEmployee)) {
                        employees[i] = Constants.UPDATED_TEXT;
                    }
                }
                writeLineToFile(String.join(Constants.DELIMITER, employees), false);
            } catch (Exception exception) {
            }
            System.out.println(Constants.UPDATED_MESSAGE);
        } else if (args[0].contains(Constants.DELETE_ARG)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                List<String> employeeList = new ArrayList<>(Arrays.asList(readLineFromFile().split(Constants.DELIMITER)));
                employeeList.remove(args[0].substring(1));
                writeLineToFile(String.join(Constants.DELIMITER, employeeList), false);
            } catch (Exception exception) {
            }
            System.out.println(Constants.DELETED_MESSAGE);
        }
    }
}