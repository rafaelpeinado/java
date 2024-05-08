package patientintake;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

    @Test
    void allowEntryOfAnAppoitment() {
        // Configuração
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());

        // Execução
        calendar.addAppointment(
                "Jim", "Weaver", "avery", "09/01/2018 2:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();

        // Verificação
        assertNotNull(appointments);
        assertEquals(1, appointments.size());

        PatientAppointment enteredAppt = appointments.get(0);
        assertEquals("Jim", enteredAppt.getPatientFirstName());
        assertEquals("Weaver", enteredAppt.getPatientLastName());
        assertSame(Doctor.avery, enteredAppt.getDoctor());
        assertEquals(
                "9/1/2018 02:00 PM",
                enteredAppt.getAppointmentDateTime()
                        .format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
    }

    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment(
                "Jim", "Weaver", "avery", "09/01/2018 2:00 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnCurrentDaysAppointments() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment(
                "Jim",
                "Weaver",
                "avery",
                "05/07/2024 2:00 pm"
        );
        calendar.addAppointment(
                "Jim",
                "Weaver",
                "avery",
                "05/07/2024 3:00 pm"
        );
        calendar.addAppointment(
                "Jim",
                "Weaver",
                "avery",
                "09/01/2018 2:00 pm"
        );
        assertEquals(2, calendar.getTodayAppointments().size());
        // assertEquals(calendar.getTodayAppointments(), calendar.getAppointments());
        // assertIterableEquals(calendar.getTodayAppointments(), calendar.getAppointments());
    }

}