package Controller;

import Exceptions.NotEnoughMoneyException;
import Model.Animals.Domestics.Cow;
import Model.Animals.Domestics.Hen;
import Model.Animals.Domestics.Sheep;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Mission;
import Model.Placement.Direction;
import Model.Placement.Position;
import Utils.Utils;
import View.AnimalViewer;
import View.Animations.BuzzAnimation;
import View.GamePlayView;

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


}
