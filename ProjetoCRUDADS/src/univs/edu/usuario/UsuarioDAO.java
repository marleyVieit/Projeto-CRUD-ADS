package univs.edu.usuario;

import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.hibernate.criterion.Projections.id;
import org.hibernate.criterion.Restrictions;
import univs.edu.util.HibernateUtil;

public class UsuarioDAO {

    private Session sessao;
    private Transaction transacao;

    public void salvar(Usuario usuario) {
        sessao = HibernateUtil.
                getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        if (usuario.getIdUsuario() == 0) {

            sessao.save(usuario);
            JOptionPane.showMessageDialog(null, "Usuario cadastrado");
        } else {

            sessao.update(usuario);
            JOptionPane.showMessageDialog(null, "Usuario editado");

        }

        transacao.commit();
        sessao.close();
    }

    public void excluir(Usuario usuario) {
        sessao = HibernateUtil.
                getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        sessao.delete(usuario);
        transacao.commit();
        sessao.close();
    }

    public Usuario pesquisar(int id) {
        sessao = HibernateUtil.
                getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        Usuario usuario = (Usuario) sessao.createCriteria(Usuario.class).add(Restrictions.eq("idUsuario", id)).uniqueResult();
        return usuario;

    }

    public List<Usuario> pesquisar() {
        sessao = HibernateUtil.
                getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        List<Usuario> usuarios = sessao.createCriteria(Usuario.class).list();
        return usuarios;
    }

}
