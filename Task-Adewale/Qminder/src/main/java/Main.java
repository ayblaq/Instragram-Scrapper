import java.io.IOException;
import java.util.Scanner;

import Service.DownloadService;
import Util.ValidationUtil;


public class Main {
    public static void main(String[] arg) throws IOException {

        DownloadService downloadService = new DownloadService();
        Scanner input = new Scanner(System.in);
        System.out.println("Input username :  ");
        String username = input.nextLine();
        ValidationUtil.validatePropertyNotNullOrEmpty(username,username);
        downloadService.downloadImages(username);
    }

}