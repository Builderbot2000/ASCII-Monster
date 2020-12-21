package com.kevintang;

import com.kevintang.ui.Driver;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            Driver.getInstance().run(null, null, null);
            String in = scanner.nextLine();
            if (in.equals("Q")) break;
        }

        scanner.close();
    }
}
