package com.example.ispw.cli_view;

import com.example.ispw.cli_graphic_controller.PostUpdateCLIController;
import com.example.ispw.session.Session;
import com.example.ispw.utilities.Printer;

import java.util.Scanner;

public class PostUpdateViewCLI {

    private final PostUpdateCLIController postUpdateCLIController;

    public PostUpdateViewCLI(PostUpdateCLIController postUpdateCLIController) {
        this.postUpdateCLIController = postUpdateCLIController;
    }

    public void publishPost() {
        Scanner reader = new Scanner(System.in);
        Printer.print("*****   NEW UPDATE INFO   *****");
        Printer.print("Insert the requested information: ");
        Printer.print("\nTitle: ");
        String title = reader.nextLine();
        Printer.print("\nDescription: ");
        String description = reader.nextLine();
        Printer.print("\nWant to add an image? (y/n)");
        String choice = reader.nextLine();
        String image;
        if (choice.equals("y")) {
            Printer.print("\nImage path: ");
            image = reader.nextLine();
        } else {
            image = "null";
        }
        String author = Session.getCurrentSession().getSeriesOffAccountBean().getUsername();
        postUpdateCLIController.updateTimeline(author, title, description, image);
    }
}
