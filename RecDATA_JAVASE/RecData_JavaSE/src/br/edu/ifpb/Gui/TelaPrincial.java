package br.edu.ifpb.Gui;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaPrincial {

    
	
	public TelaPrincial(){
		
		JFrame telaPrincial =new JFrame();
		JButton but�oLogin = new JButton();
		JButton but�oCadastrar = new JButton();
	
		but�oLogin.setText("Login");
		but�oLogin.setSize(10,10);
		but�oCadastrar.setText("Cadastrar");
		
		telaPrincial.getContentPane().add(but�oLogin);
		telaPrincial.getContentPane().add(but�oCadastrar);
		telaPrincial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaPrincial.setSize(1000,600);
		telaPrincial.setVisible(true);
		
		
		
	}
	public static void main(String[] args) {
		new TelaPrincial();
	}
}
