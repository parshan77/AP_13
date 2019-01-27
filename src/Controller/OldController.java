package Controller;

import Exceptions.ExitMissionException;
import Exceptions.MissionCompletedException;
import Exceptions.NotFoundException;
import Model.Identity.Account;
import Model.Identity.Game;
import Model.Mission;
import Model.MissionRunner;
import Model.Workshops.CustomWorkshop;
import com.gilecode.yagson.YaGson;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

public class OldController {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = null;
        try {
            game = loadGame();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        accountWhile:
        while (true) {
            Account account = launchAccount(scanner, game);
            missionWhile:
            while (true) {
                Mission mission = getMission(scanner, account);
                try {
                    System.out.println(mission.getName() + " is running");
                    new MissionRunner(mission);
                } catch (MissionCompletedException e) {
                    System.out.println("mission is completed!");
                } catch (ExitMissionException e) {
                    System.out.println("mission closed.");
                }
                System.out.println("start new mission?");
                System.out.println("enter : (y) or (logout) or (exitgame)");
                String input = scanner.nextLine();
                switch (input.toLowerCase()) {
                    case "y":
                        break;
                    case "logout":
                        break missionWhile;
                    case "exitgame":
                        break accountWhile;
                }
            }
        }
    }

    private static Mission getMission(Scanner scanner, Account account) {
        System.out.println("Select mission or create one:");
        System.out.println("enter : (select missionname) or (create filename)");
        System.out.println("available missions :");

        displaySavedMissions(account);

        String[] startMission;
        Mission mission = null;
        while (mission == null) {
            startMission = scanner.nextLine().split(" ");
            switch (startMission[0].toLowerCase()) {
                case "select":
                    try {
                        mission = account.getMission(startMission[1]);
                    } catch (NotFoundException e) {
                        System.out.println("Mission not found. enter a valid name.");
                    }
                    break;

                case "create":
                    try {
                        mission = loadMission(startMission[1]);
                    } catch (FileNotFoundException e) {
                        System.out.println("file not found.");
                    }
                    break;
            }
        }
        if (mission.getCustomWorkshop() == null) {
            System.out.println("Do you want to add custom workshop?");
            System.out.println("enter : (y filename) or (n)");
            String[] customws = scanner.nextLine().split(" ");
            if (customws[0].toLowerCase().equals("y")) {
                CustomWorkshop customWorkshop = null;
                try {
                    customWorkshop = loadCustomWorkshop(customws[1]);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if (customWorkshop != null) {
                    customWorkshop.setMission(mission);
                    mission.setCustomWorkshop(customWorkshop);
                }else {
                    System.out.println("custom workshop isn't initialized.");
                }
            }
        }
        return mission;
    }

    private static void displaySavedMissions(Account account) {
        int displayedMissions = 0;
        for (Mission mission : account.getMissions()) {
            System.out.println("    " + mission.getName());
            displayedMissions++;
        }
        if (displayedMissions == 0)
            System.out.println("-");
    }

    private static Account launchAccount(Scanner scanner, Game game) {
        System.out.println("login or sign up");
        System.out.println("enter : (login username) or (signup username)");
        System.out.println("Registered Accounts : ");

        displaySavedAccounts(game);

        String[] accountInput;

        Account account = null;
        while (account == null) {
            accountInput = scanner.nextLine().split(" ");
            switch (accountInput[0].toLowerCase()) {
                case "login":
                    try {
                        account = game.login(accountInput[1]);
                    } catch (NotFoundException e) {
                        System.out.println("This username doesn't exist");
                    }
                    break;

                case "signup":
                    account = game.signUp(accountInput[1]);
                    break;
            }
        }
        return account;
    }

    private static void displaySavedAccounts(Game game) {
        int displayedAccounts = 0;
        for (Account account : game.getAllAccounts()) {
            System.out.println("    " + account.getUsername());
            displayedAccounts++;
        }
        if (displayedAccounts == 0)
            System.out.println("-");
    }

    private static Mission loadMission(String textFileName) throws FileNotFoundException {
        File file = new File("C:\\Users\\parshan\\Desktop\\FarmFrenzy\\SaveGame\\SavedMissions\\" + textFileName);
        try (Scanner scanner = new Scanner(file)) {
            YaGson yaGson = new YaGson();
            String objectString = scanner.nextLine();
            return yaGson.fromJson(objectString, Mission.class);
        }
    }

    private static Game loadGame() throws FileNotFoundException {
        File file = new File("C:\\Users\\parshan\\Desktop\\FarmFrenzy\\SaveGame\\SavedGame\\game.txt");
        try (Scanner scanner = new Scanner(file)) {
            YaGson yaGson = new YaGson();
            String objectString = scanner.nextLine();
            return yaGson.fromJson(objectString, Game.class);
        }
    }

    private static CustomWorkshop loadCustomWorkshop(String textFileName) throws FileNotFoundException {
        File file = new File("C:\\Users\\parshan\\Desktop\\FarmFrenzy\\SaveGame\\SaveCustomWorkshop\\" + textFileName);
        try (Scanner scanner = new Scanner(file)) {
            YaGson yaGson = new YaGson();
            String objectString = scanner.nextLine();
            return yaGson.fromJson(objectString, CustomWorkshop.class);
        }
    }

    private static void saveCustomWorkshop(CustomWorkshop customWorkshop, String textFileName) throws FileNotFoundException {
        File file = new File("C:\\Users\\parshan\\Desktop\\FarmFrenzy\\SaveGame\\SaveCustomWorkshop\\" + textFileName);
        try (Formatter formatter = new Formatter(file)) {
            YaGson yaGson = new YaGson();
            String workshopString = yaGson.toJson(customWorkshop, CustomWorkshop.class);
            formatter.format(workshopString);
        }
    }

    private static void saveMission(Mission mission, String textFileName) throws IOException {
        File file = new File("C:\\Users\\parshan\\Desktop\\FarmFrenzy\\SaveGame\\SavedMissions\\" + textFileName);
        try (Formatter formatter = new Formatter(file)) {
            YaGson yaGson = new YaGson();
            String objectJson = yaGson.toJson(mission, Mission.class);
            formatter.format(objectJson);
        }
    }

    private static void saveGame(Game game, String textFileName) throws FileNotFoundException {
        File file = new File("C:\\Users\\parshan\\Desktop\\FarmFrenzy\\SaveGame\\SavedGame\\" + textFileName);
        try (Formatter formatter = new Formatter(file)) {
            YaGson yaGson = new YaGson();
            String GameString = yaGson.toJson(game, Mission.class);
            formatter.format(GameString);
        }
    }
}
