package br.edu.unibratec.autenticacao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import br.edu.unibratec.autenticacao.model.Pessoa;
import br.edu.unibratec.autenticacao.model.Usuario;
import br.edu.unibratec.autenticacao.util.Messages;

public class UsuarioDao {

	private SessionFactory sessionFactory;

	public UsuarioDao() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	@SuppressWarnings("finally")
	public String inserir(Usuario user) throws SQLException {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(user);
			session.getTransaction().commit();
		} finally {
			session.close();
			return Messages.SUCESSO_CADASTRO_USUARIO;
		}
	}

	@SuppressWarnings("finally")
	public String deletar(String login) throws SQLException {
		String retorno = "";
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Usuario usuario = session.get(Usuario.class, login);
			if (usuario == null) {
				return retorno = Messages.USER_NOT_FOUND;
			}
			session.delete(usuario);
			session.getTransaction().commit();
			retorno = Messages.SUCESS_DELETE_USERNAME;
		} finally {
			session.close();
			return retorno;
		}
	}

	public void atualizar(Usuario usuario) throws  SQLException {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(usuario);
			session.getTransaction().commit();
		} finally {
			session.close();

		}
	}

	public Usuario buscar(String username) throws  SQLException {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Usuario usuario;
		try {

			if (username.indexOf('@') == Messages.NOT_FOUND) {
				usuario = session.get(Usuario.class, username);
			} else {
				String hql = "FROM Pessoa P WHERE P.email = :pessoa_email";
				Query query = session.createQuery(hql);
				query.setParameter("pessoa_email", username);
				Pessoa pessoa = (Pessoa) query.uniqueResult();
				usuario = pessoa.getUsuario();
			}
			return usuario;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listar(int opcao) throws  SQLException {
		Session session = sessionFactory.openSession();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String hql = "";
		Query query = null;
		session.beginTransaction();

		try {
			switch (opcao) {

			case 1:
				/* Listagem de todos os usuários */
				usuarios = (List<Usuario>) session.createQuery("from Usuario").list();
				break;

			case 2:
				/* Listagem de usuários com perfil de Administrador */
				hql = "FROM Usuario WHERE perfil= :Administrador";
				query = session.createQuery(hql);
				query.setParameter("Administrador", "Administrador");
				usuarios = (List<Usuario>) query.list();
				
				break;
			case 3:
				/* Listagem de usuários com status Inativo */
				hql = "FROM Usuario WHERE status= :Inativo";
				query = session.createQuery(hql);
				query.setParameter("Inativo", "Inativo");
				usuarios = (List<Usuario>) query.list();
	
				break;
			case 4:
				/* Listagem de usuários com situação de Bloqueio */
				hql = "FROM Usuario WHERE situacao= :Bloqueado";
				query = session.createQuery(hql);
				query.setParameter("Bloqueado", "Bloqueado");
				usuarios = (List<Usuario>) query.list();
				
				break;
			default:
				break;
			} 

			return usuarios;
		} finally {
			session.close();
		}
	}

}
