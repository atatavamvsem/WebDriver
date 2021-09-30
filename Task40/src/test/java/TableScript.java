import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utils.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableScript {
    private static final int AGE = 50;
    private static final int SALARY = 600000;

    private static WebDriver driver;
    private static int counter = 1;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");

        Select objSelect = new Select(driver.findElement(By.xpath("//select[@name='example_length']")));
        objSelect.selectByValue("10");

        List<Employee> selectedEmployees = selectDataWithConditions(AGE, SALARY);

        for (Employee employee : selectedEmployees) {
            System.out.println(employee.toString());
        }

        driver.quit();
    }

    private static List<Employee> selectDataWithConditions(int age, int salary) {
        List<Employee> employees = new ArrayList<>();
        Map<Integer, String[]> data = new HashMap<>();

        List<WebElement> columns = driver.findElements(By.xpath("//table/thead/tr/th"));
        List<WebElement> pages = driver.findElements(By.xpath("//div[@id='example_paginate']/span/a"));

        for (int k = 1; k <= pages.size(); k++) {
            driver.findElement(By.xpath("//div[@id='example_paginate']/span/a[" + k + "]")).click();
            List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

            for (int j = 1; j <= rows.size(); j++) {
                String[] employeeInfo = new String[columns.size()];
                for (int i = 1; i <= columns.size(); i++) {
                    WebElement cellInfo = driver.findElement(By.xpath("//table/tbody/tr[" + j + "]/td[" + i + "]"));
                    if (i == 6) {
                        employeeInfo[i - 1] = cellInfo.getText().replaceAll("[\\$\\,\\/y]", "");
                    } else {
                        employeeInfo[i - 1] = cellInfo.getText();
                    }
                }
                data.put(counter, employeeInfo);
                counter++;
            }
        }

        for (Map.Entry<Integer, String[]> entry : data.entrySet()) {
            if (Integer.parseInt(entry.getValue()[3]) > age && Integer.parseInt(entry.getValue()[5]) <= salary) {
                employees.add(new Employee(entry.getValue()[0], entry.getValue()[1], entry.getValue()[2]));
            }
        }

        return employees;
    }
}
