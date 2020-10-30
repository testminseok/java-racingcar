/**
 * 클래스 이름: ViewTest
 * 버전 정보: 0.1
 * 날짜: 2020.10.30
 * 저작권 주의: 없음
 */

package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("View 클래스 테스트")
class ViewTest {

    View view;

    @BeforeEach
    void setUp() {
        Memento memento = Mockito.mock(Memento.class);
        this.view = new View(memento);
    }

    @ParameterizedTest
    @DisplayName("입력된 carPosition 만큼 -가 출력되어야 한다.")
    @CsvSource(value = {"1:-", "2:--", "3:---", "4:----", "5:-----", "6:------", "7:-------"}, delimiter = ':')
    void convertCarPositions(int carPosition, String expectedResult) {
        String result = this.view.convertCarPosition(carPosition);
        assertThat(result)
                .isEqualTo(expectedResult);

    }
}
