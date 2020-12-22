package com.kevintang.ui.menus;

public class CreateWorldMenu extends Menu{

    public CreateWorldMenu() {
        super("Create New World", "Choose world size:");
        options.add("[1] 40x40");
        options.add("[2] 80x80");
        options.add("[3] 100x100");
        options.add("[4] 120x120");
        options.add("[5] 200x200");
        options.add("[B/b] Back to main menu");
    }

    @Override
    public void listen(String in) {
        switch (in) {
            case "1" -> {}
            case "2" -> {}
            case "3" -> {}
            case "4" -> {}
            case "5" -> {}
            default -> {}
        }
    }
}
