package patientintake;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

    @Test
    public void allowEntryOfAnAppoitment() {
        // Configuração
        ClinicCalendar calendar = new ClinicCalendar();

        // Execução
        calendar.addAppointment(
                "Jim", "Weaver", "avery", "09/01/2018 2:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();

        // Verificação
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
    }

}