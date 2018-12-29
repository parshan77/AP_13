package Controller;

import Exceptions.ExitMissionException;
import Exceptions.MissionCompletedException;
import Exceptions.NotFoundException;
import Exceptions.WrongPasswordException;
import Model.Identity.Account;
import Model.Identity.Game;
import Model.LevelRequirementsChecker;
import Model.Mission;
import Model.MissionRunner;
import Model.Workshops.CustomWorkshop;
import com.gilecode.yagson.YaGson;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Formatter;
import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*Game game = Game.getInstance();
        Account account1 = game.signUp("Guest", "123456");

        LevelRequirementsChecker lrc = new LevelRequirementsChecker(0, 3, 0,
                0, 0, 0, 0, 3, 0,
                0, 0, 0, 0);

        Mission mission1 = new Mission(5000, "sampleMission", lrc, null);
        account1.addMission(mission1);
        try {
            save(game, "C:\\Users\\parshan\\Desktop\\FarmFrenzy\\SaveGame\\SavedGame\\game.json", Game.class);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Game game = null;
        try {
            load(game, "C:\\Users\\parshan\\Desktop\\FarmFrenzy\\SaveGame\\SavedGame\\game.json", Game.class);
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
        System.out.println("enter : (select missionname) or (create missionname path)");
        System.out.println("available missions :");

        displayeSavedMissions(account);

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
                    // TODO: 12/29/2018 az file bayad bekhunim
                    LevelRequirementsChecker lrc = new LevelRequirementsChecker(0, 3, 0,
                            0, 0, 0, 0, 3, 0,
                            0, 0, 0, 0);

                    mission = new Mission(5000, startMission[1], lrc, null);

                    System.out.println("Do you want to add custom workshop?");
                    System.out.println("enter : (y path) or (n)");
                    String[] customws = scanner.nextLine().split(" ");
                    switch (customws[0].toLowerCase()) {
                        case "y":
                            // TODO: 12/29/2018 custom workshop ro az file begirim bejaye sakhtan
                            CustomWorkshop customWorkshop = new CustomWorkshop("akbarMaker", new String[]{"Wool"},
                                    "Cloth", mission);
                            mission.setCustomWorkshop(customWorkshop);
                            break;
                        case "n":
                            break;
                    }
                    break;
            }
        }
        return mission;
    }

    private static void displayeSavedMissions(Account account) {
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
        System.out.println("enter : (login username password) or (signup username password)");
        System.out.println("Registered Accounts : ");

        displaySavedAccounts(game);

        String[] accountInput;

        Account account = null;
        while (account == null) {
            accountInput = scanner.nextLine().split(" ");
            switch (accountInput[0].toLowerCase()) {
                case "login":
                    try {
                        account = game.login(accountInput[1], accountInput[2]);
                    } catch (WrongPasswordException e) {
                        System.out.println("Wrong Password");
                    } catch (NotFoundException e) {
                        System.out.println("This username doesn't exist");
                    }
                    break;

                case "signup":
                    account = game.signUp(accountInput[1], accountInput[2]);
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

    private static void save(Object object, String pathTofile, Type objectType) throws IOException {
        File file = new File(pathTofile);
        try (Formatter formatter = new Formatter(file)) {
            YaGson yaGson = new YaGson();
            String objectJson = yaGson.toJson(object, objectType);
            formatter.format(objectJson);
        }
    }

    private static void load(Object object, String pathToFile, Type objectType) throws FileNotFoundException {
        File file = new File(pathToFile);
        try(Scanner scanner = new Scanner(file)) {
            YaGson yaGson = new YaGson();
            String objectJson = scanner.nextLine();
            object = yaGson.fromJson(objectJson, objectType);
        }
    }
}
