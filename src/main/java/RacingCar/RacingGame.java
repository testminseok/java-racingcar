package RacingCar;

import RacingCar.model.Car;
import RacingCar.model.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RacingGame {

    private static final int LIMIT = 10;
    private static final int THRESHOLD = 4;
    private static final int ZERO = 0;

    private RacingGame() {
    }

    private static class InnerRacingGame {
        private static final RacingGame INSTANCE = new RacingGame();
    }

    static RacingGame getInstance() {
        return InnerRacingGame.INSTANCE;
    }

    List<Result> start(List<String> names, int stage) {
        List<Car> cars = new ArrayList<>();

        for (String name : names) {
            cars.add(new Car(name));
        }

        List<Result> results = new ArrayList<>();
        for (int i = 0; i < stage; i++) {
            cars = move(cars);
            results.add(new Result(cars));
        }

        return results;
    }

    private List<Car> move(List<Car> cars) {
        return cars.stream().map(car -> {
            int count = car.getMove() + getMoveCount();
            return new Car(car.getName(), count);
        }).collect(Collectors.toList());
    }


    private static int getMoveCount() {
        int move = getRandom();
        return isMovable(move) ? move : ZERO;
    }

    private static int getRandom() {
        return new Random().nextInt(LIMIT);
    }

    static boolean isMovable(int moveCount) {
        return moveCount >= THRESHOLD;
    }
}