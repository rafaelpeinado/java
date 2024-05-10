package patientintake;


import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

    private ClinicCalendar calendar;

    @BeforeAll
    static void testClassSetup() {
        System.out.println("Before all...");
    }

    @BeforeEach
    void init() {
        System.out.println("Before each...");
        calendar = new ClinicCalendar(LocalDate.of(2018, 8, 26));
    }

    @Test
        // @Disabled
    void allowEntryOfAnAppoitment() {
        System.out.println("entry of appointment...");
        // Execução
        calendar.addAppointment(
                "Jim", "Weaver", "avery", "09/01/2018 2:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();

        // Verificação
        assertNotNull(appointments);
        assertEquals(1, appointments.size());

        PatientAppointment enteredAppt = appointments.get(0);
        assertAll(
                () -> assertEquals("Jim", enteredAppt.getPatientFirstName()),
                () -> assertEquals("Weaver", enteredAppt.getPatientLastName()),
                () -> assertSame(Doctor.avery, enteredAppt.getDoctor()),
                () -> assertEquals(
                        "9/1/2018 02:00 PM",
                        enteredAppt.getAppointmentDateTime()
                                .format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")))
        );
    }

//    @Test
//    void allowEntryOfAnAppoitment() {
//        System.out.println("entry of appointment...");
//        // Execução
//        calendar.addAppointment(
//                "Jim", "Weaver", "avery", "09/01/2018 2:00 pm");
//        List<PatientAppointment> appointments = calendar.getAppointments();
//
//        // Verificação
//        assertNotNull(appointments);
//        assertEquals(1, appointments.size());
//
//        PatientAppointment enteredAppt = appointments.get(0);
//        assertAll(
//                () -> assertEquals("Jims", enteredAppt.getPatientFirstName()),
//                () -> assertEquals("Weavers", enteredAppt.getPatientLastName()),
//                () -> assertSame(Doctor.avery, enteredAppt.getDoctor()),
//                () -> assertEquals(
//                        "9/1/2018 02:00 PM",
//                        enteredAppt.getAppointmentDateTime()
//                                .format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")))
//        );
//    }

    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments() {
        System.out.println("has appts...");
        calendar.addAppointment(
                "Jim", "Weaver", "avery", "09/01/2018 2:00 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
        System.out.println("no appts...");
        assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnCurrentDaysAppointments() {
        System.out.println("current days appts...");
        calendar.addAppointment(
                "Jim",
                "Weaver",
                "avery",
                "08/26/2018 2:00 pm"
        );
        calendar.addAppointment(
                "Jim",
                "Weaver",
                "avery",
                "08/26/2018 3:00 pm"
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

    @AfterEach
    void tearDownEachTest() {
        System.out.println("After each...");
    }

    @AfterAll
    static void testDownTestClass() {
        System.out.println("After all...");
    }


}