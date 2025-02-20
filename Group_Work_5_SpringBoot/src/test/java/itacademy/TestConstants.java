package itacademy;

import itacademy.dto.CarDto;
import itacademy.dto.EngineDto;

public class TestConstants {
    public static final EngineDto TEST_ENGINE = EngineDto.builder()
            .model("BMW Engine")
            .horsePower(400)
            .build();
    public static final CarDto TEST_CAR = CarDto.builder()
            .brand("BMW")
            .model("X5")
            .engine(EngineDto.builder()
                    .id(1L)
                    .build())
            .build();
}
