package com.icai.practicas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.icai.practicas.model.Telefono;
import org.junit.jupiter.api.Test;


public class TelefonoTest {

    @Test
    public void testTelefono(){
        Telefono telefonoEsp = new Telefono("618068118");
        Telefono telefonoInt = new Telefono("+44784597843");
        Telefono telefonoNotValid = new Telefono("12345678987654321");

        boolean telefonoEspTested = telefonoEsp.validar();
        assertEquals(true, telefonoEspTested);

        boolean telefonoIntTested = telefonoInt.validar();
        assertEquals(true, telefonoIntTested);

        boolean telefonoNotValidTested = telefonoNotValid.validar();
        assertEquals(false, telefonoNotValidTested);
    }
    
}
