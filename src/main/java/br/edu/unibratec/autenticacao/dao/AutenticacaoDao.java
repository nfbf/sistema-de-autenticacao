package br.edu.unibratec.autenticacao.dao;

import org.hibernate.query.Query;

import java.sql.SQLException;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.edu.unibratec.autenticacao.model.Pessoa;
import br.edu.unibratec.autenticacao.model.Usuario;
import br.edu.unibratec.autenticacao.util.Messages;

public class AutenticacaoDao {

	private SessionFactory sessionFactory;

	public AutenticacaoDao() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	@SuppressWarnings("rawtypes")
	public Usuario autenticarUsuario(String id) throws SQLException {
		Usuario usuario = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			if (id.indexOf('@') == Messages.NOT_FOUND) {
				usuario = session.get(Usuario.class, id);
			} else {
				String hql = "FROM Pessoa P WHERE P.email = :pessoa_email";
				Query query = session.createQuery(hql);
				query.setParameter("pessoa_email", id);
				Pessoa pessoa = (Pessoa) query.uniqueResult();
				usuario = pessoa.getUsuario();
			}
			return usuario;
		} finally {
			session.close();
		}
	}

	public String resertarSenha(Usuario usuario) throws SQLException {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			if (usuario.getLogin().indexOf('@') != Messages.NOT_FOUND) {
				String hql = "FROM Pessoa as P WHERE P.email= :pessoa_email ";
				Query query = session.createQuery(hql);
				query.setParameter("pessoa_email", usuario.getLogin());
				Pessoa pessoa = (Pessoa) query.uniqueResult();
				if (pessoa != null) {
					usuario.setLogin(pessoa.getUsuario().getLogin());
					usuario.setPerfil(pessoa.getUsuario().getPerfil());
					usuario.setSituacao(pessoa.getUsuario().getSituacao());
					usuario.setStatus(pessoa.getUsuario().getStatus());
					session.clear();
					session.saveOrUpdate(usuario);
					session.getTransaction().commit();

					return Messages.SUCCESS_PASSWORD_CHANGED;

				} else {

					return Messages.USER_NOT_FOUND;

				}

			} else {
				Usuario novoUsuario = session.get(Usuario.class, usuario.getLogin());
				novoUsuario.setSenha(usuario.getSenha());
				session.saveOrUpdate(novoUsuario);
				session.getTransaction().commit();
				return Messages.SUCCESS_PASSWORD_CHANGED;
			}
		} finally {
			session.close();

		}

	}

	public String bloquearUsuario(String login, String senha)   throws SQLException{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Usuario usuario = session.get(Usuario.class, login);
			usuario.setSituacao(Messages.CONDITION_BLOCKED);
			session.saveOrUpdate(usuario);
			session.getTransaction().commit();
			return Messages.USERNAME_BLOQUEADO;

		} finally {
			session.close();
		}
	}

}