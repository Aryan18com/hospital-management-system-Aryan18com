package HospitalManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HospitalManagementSystem extends JFrame {
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;

    private JTextArea outputArea;

    public HospitalManagementSystem() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();

        // Sample data
        doctors.add(new Doctor("Dr. Smith", "Cardiology"));
        doctors.add(new Doctor("Dr. Johnson", "Neurology"));

        // GUI setup
        setTitle("Hospital Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton addPatientButton = new JButton("Add Patient");
        JButton bookAppointmentButton = new JButton("Book Appointment");
        JButton viewDoctorsButton = new JButton("View Doctors");

        panel.add(addPatientButton);
        panel.add(bookAppointmentButton);
        panel.add(viewDoctorsButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Button actions
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });

        bookAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookAppointment();
            }
        });

        viewDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDoctors();
            }
        });
    }

    private void addPatient() {
        String name = JOptionPane.showInputDialog("Enter Patient Name:");
        String ageStr = JOptionPane.showInputDialog("Enter Patient Age:");
        int age = Integer.parseInt(ageStr);

        Patient patient = new Patient(name, age);
        patients.add(patient);
        outputArea.append("Added Patient: " + patient + "\n");
    }

    private void bookAppointment() {
        String patientName = JOptionPane.showInputDialog("Enter Patient Name for Appointment:");
        Patient patient = null;

        for (Patient p : patients) {
            if (p.getName().equals(patientName)) {
                patient = p;
                break;
            }
        }

        if (patient == null) {
            outputArea.append("Patient not found!\n");
            return;
        }

        String[] doctorNames = new String[doctors.size()];
        for (int i = 0; i < doctors.size(); i++) {
            doctorNames[i] = doctors.get(i).toString();
        }

        String selectedDoctor = (String) JOptionPane.showInputDialog(
                this,
                "Select Doctor",
                "Book Appointment",
                JOptionPane.QUESTION_MESSAGE,
                null,
                doctorNames,
                doctorNames[0]);

        if (selectedDoctor != null) {
            outputArea.append("Appointment booked for " + patient + " with " + selectedDoctor + "\n");
        }
    }

    private void viewDoctors() {
        outputArea.append("Doctors:\n");
        for (Doctor doctor : doctors) {
            outputArea.append(doctor + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HospitalManagementSystem().setVisible(true);
            }
        });
    }
}
