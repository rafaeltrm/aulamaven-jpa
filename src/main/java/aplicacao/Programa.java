package aplicacao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Pessoa p1 = new Pessoa(null, "Teste1", "teste1@gmail.com");
		Pessoa p2 = new Pessoa(null, "Teste2", "teste2@gmail.com");
		Pessoa p3 = new Pessoa(null, "Teste3", "teste3@gmail.com");

		// Necessario para criar um EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database-jpa");
		EntityManager em = emf.createEntityManager(); // Instancia conexao com banco de dados e acesso a dados

		/*Adiciona pessoa no banco de dados*/
		em.getTransaction().begin(); // inicia transacao
		em.persist(p1);
		em.persist(p2);// adiciona objeto no banco de dados
		em.persist(p3);
		em.getTransaction().commit(); // finaliza transacao
		
		
		/*Busca pessoa no banco de dados pelo ID*/
		
		Pessoa p = em.find(Pessoa.class, 2);
		System.out.println(p);
		
		
		/*Apaga pessoa do banco de dados*/
		Pessoa pessoa = em.find(Pessoa.class, 2);
		
		em.getTransaction().begin();
		em.remove(pessoa);
		em.getTransaction().commit();
		
		System.out.println("Ok!");
		em.close();
		emf.close();
	}

}
