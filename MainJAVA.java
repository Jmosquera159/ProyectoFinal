import javax.swing.*;
import java.awt.event.*;

import java.awt.Image;
import java.awt.Toolkit;

public class MainJAVA extends JFrame implements ActionListener{

    private final JLabel text1;
    private final JLabel text2;
    private final JLabel text3;
    private final JButton boton;

    public MainJAVA() {

        setLayout(null);

        boton = new JButton("Iniciar conteo");
        boton.setBounds(125, 10, 180, 30);
        add(boton);
        boton.addActionListener(this);

        text1 = new JLabel("Atendidos en 10 horas: ");
        text1.setBounds(10, 50, 300, 30);
        add(text1);

        text2 = new JLabel("En cola después de 10 horas: ");
        text2.setBounds(10, 90, 300, 30);
        add(text2);

        text3 = new JLabel("Minuto de llegada de la persona que llegó despues de 10 horas: ");
        text3.setBounds(10, 130, 600, 30);
        add(text3);
    }

    public void actionPerformed(final ActionEvent e) {

        if (e.getSource() == boton) {

            simulacion();

        }
    }

    public void simulacion() {

        int estado = 0;
        int llegada = 2 + (int) (Math.random() * 2);
        int salida = -1;
        int cantAtendidas = 0;

        final ProyectoFinalJAVA cola = new ProyectoFinalJAVA();

        for (int minuto = 0; minuto < 600; minuto++) {

            if (llegada == minuto) {

                if (estado == 0) {
                    estado = 1;
                    salida = minuto + 2 + (int) (Math.random() * 3);
                } else {
                    cola.insertar(minuto);
                }

                llegada = minuto + 2 + (int) (Math.random() * 2);

            }

            if (salida == minuto) {

                estado = 0;
                cantAtendidas++;

                if (!cola.vacia()) {
                    cola.extraer();
                    estado = 1;
                    salida = minuto + 2 + (int) (Math.random() * 3);
                }
            }
        }

        boton.setText("Reiniciar Conteo");

        text1.setText("Atendidos en 10 horas:  " + String.valueOf(cantAtendidas) + " personas");
        text2.setText("En cola después de 10 horas:  " + String.valueOf(cola.cantidad()) + " personas");
        text3.setText("Minuto de llegada de persona que llegó después de 10 horas:  " + String.valueOf(cola.extraer())
                + " minutos");

    }

    public static void main(final String[] ar) {
        MainJAVA ventana = new MainJAVA();
        ventana.setBounds(700,450,500,210);
        ventana.setVisible(true);
        ventana.setTitle("Proyecto final estructura de datos grupo 5");
        Image im = Toolkit.getDefaultToolkit().getImage("logoingsistemas.jpg");
        ventana.setIconImage(im);

    }
}