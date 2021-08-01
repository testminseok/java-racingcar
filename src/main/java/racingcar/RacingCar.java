/*
 *   Class name : RacingCar.java
 *   Version : 0.0.1
 *   Date : 2021/07/24
 */

package racingcar;

import java.util.ArrayList;
import java.util.List;

/**
 * 해당 클래스는 Racing을 하기 위한 기능이 구현되어있는 클래스입니다.
 */
public class RacingCar {

    private final String[] carNames;   // 자동차들의 이름
    private final int moveCount; // 자동차 이동 횟수
    private List<Car> cars; // 자동차들의 객체

    public RacingCar(String[] carNames, int moveCount, int range) {
        this.carNames = carNames;
        this.moveCount = moveCount;
        this.cars = new ArrayList<Car>();
    }

    /**
     * Racing에 필요한 Car의 입력 갯수만큼 생성합니다.
     * @return List<Car>
     */
    public List<Car> createAsCarsNumberOfEnteredByUser() {
        for (int i = 0; i < this.carNames.length; i++) {
            Car car = new Car(this.carNames[i]);
            this.cars.add(car);
        }
        return this.cars;
    }

    /**
     * 준비된 Car 객채들 끼리의 경주를 시작합니다.
     */
    public List<Car> doRacingStart(List<Car> cars) {
        for (int i = 0; i < this.moveCount; i++) {
            moveAsCarUserEntered(cars);
        }
        return cars;
    }

    /**
     * 사용자가 입력한 횟수만큼 자동차들을 움직입니다.
     * @param cars
     */
    public void moveAsCarUserEntered(List<Car> cars) {
        for (Car car : cars) {
            car.moveCar(Car.RANGE);
        }

        RacingCarResultView view = new RacingCarResultView();
        view.drawCarMoving(cars);
    }

    /**
     * 가장 멀리 이동한 자동차들을 반환합니다.
     * @param cars
     * @return
     */
    public List<Car> getWinners(List<Car> cars) {
        int winnerLocation = getWinnerLocation(cars);

        List<Car> winners = new ArrayList<>();
        for (Car car : cars) {
            addWinnerCar(winnerLocation, winners, car);
        }

        return winners;
    }

    private void addWinnerCar(int winnerLocation, List<Car> winners, Car car) {
        if (car.getCurrentLocation() == winnerLocation) {
            winners.add(car);
        }
    }

    /**
     * 자동차들 중 가장 멀리간 자동차의 위치를 반환합니다.
     * @param cars
     * @return
     */
    public int getWinnerLocation(List<Car> cars) {
        int maxLocation = cars.get(0).getCurrentLocation();

        for (Car car : cars) {
            if(maxLocation < car.getCurrentLocation()) {
                maxLocation = car.getCurrentLocation();
            }
        }

        return maxLocation;
    }

    /**
     * RacingCar 실행함수
     */
    public void start() {
        List<Car> carsAfterTheRace = this.doRacingStart(this.createAsCarsNumberOfEnteredByUser());

        RacingCarResultView view = new RacingCarResultView();
        view.drawWinnerView(this.getWinners(carsAfterTheRace));
    }

    public List<Car> getCars() {
        return cars;
    }
}
