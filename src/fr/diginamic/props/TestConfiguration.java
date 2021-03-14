package fr.diginamic.props;

import java.util.ResourceBundle;

public class TestConfiguration {

	public static void main(String[] args) {
		ResourceBundle conf = ResourceBundle.getBundle("conf");
		String nom = conf.getString("nom");

		String prenom = conf.getString("prenom");

		System.out.println(nom);
		System.out.println(prenom);

	}
}
