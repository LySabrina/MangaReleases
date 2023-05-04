package com.example.releases.services;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Scanner;

public class BookSearch {
    public static void main(String[] args){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://sevenseasentertainment.com/");
        WebElement e = driver.findElement(By.id("s"));
        e.sendKeys("A Cat from Our World and the Forgotten Witch");
        e.sendKeys(Keys.ENTER);
//        Scanner s = new Scanner(System.in);
//        while(s.hasNextLine()){
//            String input = s.nextLine();
//
//        }
    }
}
