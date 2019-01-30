package Controller;

import Exceptions.NotEnoughMoneyException;
import Exceptions.NotFoundException;
import Model.Animals.Animal;
import Model.Animals.Domestics.Cow;
import Model.Animals.Domestics.Hen;
import Model.Animals.Domestics.Sheep;
import Model.Animals.Predator;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Mission;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Products.Product;
import Utils.Utils;
import View.AnimalViewer;
import View.Animations.AnimalAnimation;
import View.Animations.BuzzAnimation;
import View.GamePlayView;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class AnimalController {

    public static void buyAnimal(String animalName, GamePlayView gamePlayView) {
        Direction direction = Utils.getRandomDirection();
        Position position = Utils.getRandomPosition();
        Mission mission = gamePlayView.getMission();
        AnimalViewer animalViewer = null;
        switch (animalName.toLowerCase()) {
            case "cow":
                try {
                    mission.spendMoney(Cow.getBuyCost());
                } catch (NotEnoughMoneyException e) {
                    BuzzAnimation.play(gamePlayView.getMoneyLabel());
                    return;
                }
                Cow cow = new Cow(mission.getMap(), direction, position);
                mission.getMap().addToMap(cow);
                animalViewer = new AnimalViewer(cow, gamePlayView);
                cow.setAnimalViewer(animalViewer);
                break;

            case "hen":
                try {
                    mission.spendMoney(Hen.getBuyCost());
                } catch (NotEnoughMoneyException e) {
                    BuzzAnimation.play(gamePlayView.getMoneyLabel());
                    return;
                }
                Hen hen = new Hen(mission.getMap(), direction, position);
                mission.getMap().addToMap(hen);
                animalViewer = new AnimalViewer(hen, gamePlayView);
                hen.setAnimalViewer(animalViewer);
                break;

            case "sheep":
                try {
                    mission.spendMoney(Sheep.getBuyCost());
                } catch (NotEnoughMoneyException e) {
                    BuzzAnimation.play(gamePlayView.getMoneyLabel());
                    return;
                }
                Sheep sheep = new Sheep(mission.getMap(), direction, position);
                mission.getMap().addToMap(sheep);
                animalViewer = new AnimalViewer(sheep, gamePlayView);
                sheep.setAnimalViewer(animalViewer);
                break;

            case "cat":
                try {
                    mission.spendMoney(Cat.getBuyCost());
                } catch (NotEnoughMoneyException e) {
                    BuzzAnimation.play(gamePlayView.getMoneyLabel());
                    return;
                }
                Cat cat = new Cat(mission, direction, position);
                mission.getMap().addToMap(cat);
                animalViewer = new AnimalViewer(cat, gamePlayView);
                cat.setAnimalViewer(animalViewer);
                break;

            case "dog":
                try {
                    mission.spendMoney(Dog.getBuyCost());
                } catch (NotEnoughMoneyException e) {
                    BuzzAnimation.play(gamePlayView.getMoneyLabel());
                    return;
                }
                Dog dog = new Dog(mission.getMap(), direction, position);
                mission.getMap().addToMap(dog);
                animalViewer = new AnimalViewer(dog, gamePlayView);
                dog.setAnimalViewer(animalViewer);
                break;
        }
    }

    public static void produceProduct(Animal animal, Product product) {
        AnimalViewer animalViewer = animal.getAnimalViewer();
        Group root = animalViewer.getGamePlayView().getRoot();
        String url = "File:Textures\\Product\\"+product.getName()+".png";
        Image image = new Image(url);

    }

    public static void dogBattle(Dog dog, ArrayList<Predator> predators) {
        AnimalViewer dogViewer = dog.getAnimalViewer();
        GamePlayView gamePlayView = dogViewer.getGamePlayView();
        Map map = dog.getMap();
        Position position = dog.getPosition();
        int row = position.getRow();
        int column = position.getColumn();

        gamePlayView.getRoot().getChildren().remove(dogViewer.getImageView());
        for (Predator predator : predators) {
            gamePlayView.getRoot().getChildren().remove(predator.getAnimalViewer().getImageView());
            try {
                map.discardAnimal(predator);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        AnimalAnimation.battle(dogViewer,gamePlayView.getCellCenterX(row,column),
                gamePlayView.getCellCenterY(row,column));
    }
}
