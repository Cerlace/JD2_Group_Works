package itacademy.service.impl;

import itacademy.dto.EngineDto;
import itacademy.service.api.EngineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static itacademy.TestConstants.TEST_ENGINE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class EngineServiceImplTest {
    @Autowired
    private EngineService engineService;



    @Test
    void saveEngine() {
        EngineDto savedEngine = engineService.saveOrUpdateEngine(TEST_ENGINE);
        assertNotNull(savedEngine.getId());
        assertEquals(TEST_ENGINE.getModel(), savedEngine.getModel());
    }

    @Test
    void updateEngine() {
        TEST_ENGINE.setId(1L);
        EngineDto savedEngine = engineService.saveOrUpdateEngine(TEST_ENGINE);
        assertEquals(1L, savedEngine.getId());
        assertEquals(TEST_ENGINE.getModel(), savedEngine.getModel());
        TEST_ENGINE.setId(null);
    }

    @Test
    void deleteEngine() {
        engineService.deleteEngine(1L);
        assertNull(engineService.getEngine(1L));
    }

    @Test
    void getEngine() {
        assertNotNull(engineService.getEngine(1L));
        assertNull(engineService.getEngine(10L));
    }

    /*@Test
    void getAllEngines() {
        Page<EngineDto> page2 = engineService.getAllEngines(0,2);
        assertEquals(3, page2.getTotalElements());
        assertEquals(2, page2.getTotalPages());
        assertEquals(0, page2.getNumber());
        assertEquals(2, page2.getNumberOfElements());
    }*/

    @Test
    void getEnginesByHorsePower() {
        engineService.getEnginesByHorsePower(500, 0,3)
                .forEach(engineDto ->
                        assertTrue(engineDto.getHorsePower() > 500));
    }
}