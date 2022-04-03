package com.icai.practicas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.icai.practicas.model.DNI;
import org.junit.jupiter.api.Test;

public class DNITest {

    @Test
    public void testDNI(){
        DNI dniValid = new DNI("02783086V");
        DNI dniNotValid = new DNI("123456789");

        boolean dniValidTested = dniValid.validar();
        boolean dniNotValidTested = dniNotValid.validar();

        assertEquals(true,dniValidTested);
        assertEquals(false,dniNotValidTested);
    }
    
}
