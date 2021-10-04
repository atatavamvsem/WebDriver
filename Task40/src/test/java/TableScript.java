import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utils.Employee;

import java.util.ArrayList;
import java.util.List;

public class TableScript {
    private static final int AGE = 50;
    private static final int SALARY = 600000;

    private static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");

        Select objSelect = new Select(driver.findElement(By.xpath("//select[@name='example_length']")));
        objSelect.selectByValue("10");

        List<Employee> selectedEmployees = getEmployeesByAgeAndSalary(AGE, SALARY);

        for (Employee employee : selectedEmployees) {
            System.out.println(employee.toString());
        }

        driver.quit();
    }

    private static List<Employee> getEmployeesByAgeAndSalary(int age, int salary) {
        List<Employee> employees = new ArrayList<>();
        List<WebElement> pages = driver.findElements(By.xpath("//div[@id='example_paginate']/span/a"));

        for (int k = 1; k <= pages.size(); k++) {
            driver.findElement(By.xpath(String.format("//div[@id='example_paginate']/span/a[%d]", k))).click();
            List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

            rows.stream().filter(item -> (Integer.parseInt(item.findElement(By.xpath(".//td[4]")).getText()) > age) &&
                            (Integer.parseInt(item.findElement(By.xpath(".//td[6]")).getText().replaceAll("[\\$\\,\\/y]", "")) <= salary))
                    .forEach(number -> employees.add(new Employee(number.findElement(By.xpath(".//td[1]")).getText(),
                            number.findElement(By.xpath(".//td[2]")).getText(),
                            number.findElement(By.xpath(".//td[3]")).getText())));
        }

        return employees;
    }
}
