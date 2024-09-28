package com.divisa.ProyectoMaven;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import okhttp3.*;


public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
       
            	
                ventana frame = new JFrame("Convertidor de Divisas API: "
                		+ "api.apilayer.com/exchangerates_data");
                cys);

                JLabel etiqueta = new JLabel("De");
                JComboBox<String> comboboxFrom;
                String [ ] currencys = {"USD - Dollar", "EUR - Euro","MXN - "
                		+ "Peso Mexicano","CAD - Dolar Canadiense","GBP - Libra Esterlina",
                		"JPY - Yen","CNH - Yuan","SEK - Corona Sueca"};
                comboMonedas = new JComboBox<>(curren
                JLabel etiquetaA = new JLabel("a:");
                JComboBox<String> comboboxTo;
                comboMoneda = new JComboBox<>(currencys);
                JLabel Monto = new JLabel("Monto:");
                
               
                JTextField campoMonto = new JTextField(10);
              
                JButton convertidor = new JButton("Convertir");
                

                JPanel panel = new JPanel();
                panel.add(etiqueta);
                panel.add(comboMoneda);
                panel.add(etiquetaA);
                panel.add(comboMoneda);
                panel.add(Monto);
                panel.add(campoMonto);
                panel.add(convertidor);
              
                frame.setLocationRelativeTo(null);
                //frame.setBounds(400, 200, 400, 200);
                //panel.setBounds(400,200,400,200);
                frame.getContentPane().add(panel);
                frame.pack();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
               
                
                convertidor.addActionListener(new ActionListener() {
         	                	
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	Icon icono = new ImageIcon("src/images/icon.png");
                       
                        String from = moneda.getSelectedItem().toString();
                        String fromLen = from.toString().substring(0,3);
                        String to = comboboxTo.getSelectedItem().toString();
                        String toLen = to.toString().substring(0,3);
                        String amount = amountTextField.getText();
                        if (monto.isEmpty()) {
                        	String errorMontoVacio = "Favor de escribir un monto";
                        	JOptionPane.showMessageDialog(frame, errorMontoVacio, "Error", JOptionPane.INFORMATION_MESSAGE);
							
						} else {
						       OkHttpClient cliente = new OkHttpClient().newBuilder().build();

		                        Request solicitud = new Request.Builder()
		                                .url("https://api.apilayer.com/exchangerates_data/convert?to=" + toLen + "&from=" + fromLen + "&amount=" + amount)
		                                .addHeader("apikey", "8jBsTJmf2LfpE4FEmanEluoHaFAUMOX4")
		                                .method("GET", null)
		                                .build();
		                        
		                     
		                        try {
		                        	String amount2 = Monto.getText();
		                        	Response respuesta = client.newCall(request).execute();
		                            String responseBody = respuesta.body().string();
		                            ObjectMapper objectMapper = new ObjectMapper();
		                            JsonNode jsonNode = objectMapper.readTree(responseBody);
		                            String resultado = jsonNode.get("result").asText();
		                            String mensaje = "El monto convertido de " + amount2 + " " +fromLen  + " es de " + resultado
                                            + " "+ toLen;
		                            SwingUtilities.invokeLater(new Runnable() {
		                                public void run() {
		                                    JOptionPane.showMessageDialog(frame, mensaje, "Resultado de la Conversion", JOptionPane.INFORMATION_MESSAGE, icono);
		                                    //JLabel resultLabel = new JLabel(result);
		                                    //panel.add(resultLabel);
		                                    //resultLabel.setBounds(100,200, 300,400);
		                                }
		                            });
		                        } catch (IOException ex) {
		                            JOptionPane.showMessageDialog(frame, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		                        }
						}
                 
                    }
                });
            }
        });
    }
}
