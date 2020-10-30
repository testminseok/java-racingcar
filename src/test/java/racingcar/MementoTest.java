/**
 * 클래스 이름: MementoTest
 * 버전 정보: 0.1
 * 날짜: 2020.10.30
 * 저작권 주의: 없음
 */

package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Memento 클래스의 테스트")
class MementoTest {

    @ParameterizedTest
    @DisplayName("생성자의 carNum 인자와 똑같이 carNum 이 나와야 한다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void getCarNum(int carNum) {
        int tryNum = 5;
        Memento memento = new Memento(carNum, tryNum);
        assertThat(memento.getCarNum())
                .isEqualTo(carNum);
    }

    @ParameterizedTest
    @DisplayName("생성자의 tryNum 인자와 똑같이 maxTry 가 나와야 한다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void getMaxTry(int tryNum) {
        int carNum = 5;
        Memento memento = new Memento(carNum, tryNum);
        assertThat(memento.getMaxTry())
                .isEqualTo(tryNum);
    }

    @ParameterizedTest
    @DisplayName("increaseCurrTry 를 한 만큼 currTry 가 증가해야 한다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void increaseCurrTry(int loop) {
        int carNum = 3;
        int tryNum = 5;
        Memento memento = new Memento(carNum, tryNum);
        int oldCurrTry = memento.getCurrTry();
        for (int i = 0; i < loop; i++) {
            memento.increaseCurrTry();
        }
        assertThat(memento.getCurrTry())
                .isEqualTo(oldCurrTry + loop);
    }

    @ParameterizedTest
    @DisplayName(" 헤당 index 의 car 만 4이상의 경우에 전진해야 한다.")
    @CsvSource(value = {"0:0:1", "1:1:1", "2:2:1", "3:3:1", "4:4:2", "5:5:2", "6:6:2", "7:7:2", "8:8:2", "9:9:2"}, delimiter = ':')
    void moveCar(int carIdx, int randomNum, int movedPosition) {
        int carNum = 10;
        int tryNum = 5;
        Memento memento = new Memento(carNum, tryNum);
        memento.moveCar(carIdx, randomNum);

        int[] expectedPositions = new int[carNum];
        int unMovedPosition = 1;
        Arrays.fill(expectedPositions, unMovedPosition);
        expectedPositions[carIdx] = movedPosition;

        assertThat(memento.getCarPositions())
                .isEqualTo(expectedPositions);
    }
}
