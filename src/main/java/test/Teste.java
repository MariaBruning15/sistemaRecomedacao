package test;

import dao.UsuarioDAO;
import modelo.Usuario;

public class Teste {
   public static void main(String[] args) {
         UsuarioDAO usuarioDAO = new UsuarioDAO();
         Usuario usuario = new Usuario();
         usuario.setNome("Maria");
         usuario.setEmail("email");
         usuarioDAO.inserir(usuario);
   } 
}

